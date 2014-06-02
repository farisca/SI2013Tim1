package ba.unsa.etf.si.tim1.GUI;

import ba.unsa.etf.si.tim1.util.HibernateUtil;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class RadniNalogDetaljnije extends JFrame {
	public RadniNalogDetaljnije(long brojnaloga) {
		Document document = new Document();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();     
			List<Object[]> lq =session.createSQLQuery("select r.brojradnognaloga, r.datumkreiranja,"
					+ "k.ime, k.prezime, r.status, r.posao, r.planiranidatumizvrsenja, i.ime,"
					+ "i. prezime, r.potrebnimaterijal, r.lokacija, r.datumizvrsenja, r.utrosenovrijeme,"
					+ "r.opisposla, r.razlogstorniranja, o.ime, o.prezime, r.razlogmodifikovanja,"
					+ "m.ime, m.prezime, r.datummodifikovanja, z.ime, z.prezime, r.dodatnikomentar from radninalog r"
					+ " zaposlenik k, zaposlenik i, zaposlenik o, zaposlenik m, zaposlenik z where "
					+ "k.id = r.kreatorradnognaloga and i.id = r.izvrsilacposla and o.id = r.osobakojastornira and"
					+ " r.osobakojamodifikuje = m.id and z.id = r.osobakojazakljucuje and r.brojradnognaloga = "+ brojnaloga).list();
			String[][] elementi = new String[lq.size()][2];
			int i = 0; int j = 0;
			PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(brojnaloga)));
	        document.open();
	        PdfPTable table = new PdfPTable(2);
	        
	        table.setWidthPercentage(95f);
	        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	        	      Font.BOLD);
	        Font ctFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	        	      Font.BOLD);
	        Paragraph parah = new Paragraph("Podaci o radnom nalogu", catFont);
	        parah.setAlignment(Element.ALIGN_CENTER);
	        document.add(parah);
	        
	        document.add(new Paragraph(" "));document.add(new Paragraph(" "));
	        table.setWidths(new float[]{(float)0.3,(float) 0.7});
	        PdfPCell cell;
	       
	        
			for(Object[] q : lq) {
				cell = new PdfPCell(new Phrase("Broj naloga"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[0]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Datum kreiranja"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[1]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Kreator radnog naloga"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[2] + " " + q[3]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Status"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[4]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Posao"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[5]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Izvršilac posla"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[6] + " " + q[7]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Potrebni materijal"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[8]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Lokacija"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[9]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Datum izvršenja"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[10]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Utrošeno vrijeme"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[11]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Opis posla"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[12]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Razlog storniranja"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[13]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Osoba koja stornira"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[14] + "  " + q[15]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Razlog modificiranja"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[16]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Osoba koja modificira"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[17] + " " + q[18]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Datum modificiranja"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[19]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Osoba koja je zakljucila"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[20] + " " + q[21]))); table.addCell(cell);
				cell = new PdfPCell(new Phrase("Komentar"));cell.setHorizontalAlignment(Element.ALIGN_CENTER); cell.setBackgroundColor(WebColors.getRGBColor("#d3d3d3"));table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(q[22]))); table.addCell(cell);
				
				i++;
			}
		
	        document.add(table);
	        document.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		final PagePanel prikazPdf = new PagePanel();
	      //prikazPdf.showPage(page);   
	        final JScrollPane a = new JScrollPane(prikazPdf);
	        a.setBounds(10, 10, 680, 680);
	        a.setVisible(true);
	        this.add(a);
		RandomAccessFile raf;
		File file = new File("");
		FileChannel channel; 
        ByteBuffer buf;
        PDFFile pdffile;
        final PDFPage page;
        try {
			raf = new RandomAccessFile(file, "r");
			channel = raf.getChannel();
			buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			pdffile = new PDFFile(buf);
			page = pdffile.getPage(1); 
	        prikazPdf.useZoomTool(true);
	        prikazPdf.showPage(page);
	        raf.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		setBounds(10,10, 700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Podaci o radnom nalogu");
	}
}
