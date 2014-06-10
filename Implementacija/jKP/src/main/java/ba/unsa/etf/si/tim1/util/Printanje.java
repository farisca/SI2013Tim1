package ba.unsa.etf.si.tim1.util;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import ba.unsa.etf.si.tim1.Entiteti.RadniNalog;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

public class Printanje {
	
	// funkcija koja prvo sačuva proslijeđeni radni nalog i onda pozove funkciju za printanje
	public static void sacuvajPrintajRadniNalogPdf(RadniNalog rn) {
		String imeFajla = "radniNalog"+rn.getBrojRadnogNaloga()+".pdf";
		File myFile = new File(imeFajla);
		if( myFile.exists() ) myFile.delete();
		
		try {
			BaseFont bf;
			bf = BaseFont.createFont("arial.ttf", "Cp1250", BaseFont.EMBEDDED);
			Font ct = new Font(bf, 12);

			File file = new File(imeFajla);
            FileOutputStream pdfFileout = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter.getInstance(document, pdfFileout);
            document.open();
            
            
            Paragraph naslov = new Paragraph("Radni nalog br."+rn.getBrojRadnogNaloga());
		    document.add(naslov);
		    document.add(new Paragraph("   "));
		    document.add(new Paragraph("   "));
		   
		      Paragraph datumKreiranja = new Paragraph("Datum kreiranja: "+rn.getDatumKreiranja());
		      document.add(datumKreiranja);
		      Paragraph planiraniDatum = new Paragraph("Planirani datum izvršenja: "+rn.getPlaniraniDatumIzvrsenja(),ct);
		      document.add(planiraniDatum);
		      Paragraph datumIzvrsenja = new Paragraph("Datum izvršenja: "+rn.getDatumIzvrsenja(),ct);
		      document.add(datumIzvrsenja);
		      Paragraph utrosenoVrijeme = new Paragraph("Utrošeno vrijeme: "+rn.getUtrosenoVrijeme(),ct);
		      document.add(utrosenoVrijeme);
		      document.add(new Paragraph("    "));
		      
		      String kr = (HibernateZaposlenik.dajZaposlenikaPoId(rn.getKreatorRadnogNaloga())).getImeIPrezime();
		      String iz = (HibernateZaposlenik.dajZaposlenikaPoId(rn.getIzvrsilacPosla())).getImeIPrezime();
		      Paragraph kreator = new Paragraph("Kreator: "+kr,ct);
		      document.add(kreator);
		      Paragraph izvrsilac = new Paragraph("Izvršilac: "+iz,ct);
		      document.add(izvrsilac);
		      document.add(new Paragraph("   "));
		      
		      Paragraph status = new Paragraph("Status: "+rn.getStatus(),ct);
		      document.add(status);
		      Paragraph tipPosla = new Paragraph("Tip posla: "+rn.getPosao(),ct);
		      document.add(tipPosla);
		      Paragraph opisPosla = new Paragraph("Opis posla: "+rn.getOpisPosla(),ct);
		      document.add(opisPosla);
		      Paragraph materijal = new Paragraph("Potrebni materijal: "+rn.getPotrebniMaterijal(),ct);
		      document.add(materijal);
		      Paragraph lokacija = new Paragraph("Lokacija: "+rn.getLokacija(),ct);
		      document.add(lokacija);
 
            document.close();
            pdfFileout.close();
 
            System.out.println("Radni nalog je sacuvan!");
            
            // funkcija za printanje radnog naloga
            printajPdf(imeFajla);
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	
	
	// funkcija koja printa fajl čije se ime prosljeđuje kao parametar
	// nastavak .pdf je obavezan
	public static void printajPdf(String FILE) throws Exception {
		
		String substring = FILE.substring(FILE.length()-4, FILE.length());
		if(!(substring.equals(".pdf"))){
			throw new Exception("Fajl koji želite printati nije u .pdf formatu!");
		}
		if(!(new File(FILE).exists())) {
			throw new Exception("Fajl koji želite printati ne postoji!");
		
		}
		else {
			// Create a PDFFile from a File reference
			File f = new File(FILE);
			FileInputStream fis;

			try {
				fis = new FileInputStream(f);
				FileChannel fc = fis.getChannel();
				ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
				PDFFile pdfFile = new PDFFile(bb); // Create PDF Print Page
				PDFPrintPage pages = new PDFPrintPage(pdfFile);

				// Create Print Job
				PrinterJob pjob = PrinterJob.getPrinterJob();
				if(pjob.printDialog()) {
					PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
					pjob.setJobName(f.getName());
					Book book = new Book();
					book.append(pages, pf, pdfFile.getNumPages());
					pjob.setPageable(book);

					// Send print job to default printer
					pjob.print();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
	}

}
