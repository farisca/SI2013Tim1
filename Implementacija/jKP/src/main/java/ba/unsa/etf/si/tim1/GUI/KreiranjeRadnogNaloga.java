package ba.unsa.etf.si.tim1.GUI;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Time;
import java.util.Date;
import java.util.Locale;
import java.nio.ByteBuffer;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

import java.io.FileOutputStream;
import java.util.Date;

import ba.unsa.etf.si.tim1.Entiteti.*;
import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;

import java.awt.Dimension;

public class KreiranjeRadnogNaloga extends JPanel {
	private static final String Style = null;
	private final JComboBox<Zaposlenik> comboBoxKreirao;
	private final JComboBox<Zaposlenik> comboBoxIzvrsilac;
	private final JTextField txtLokacija;
	private final JSpinner spinnerSati;
	private final JSpinner spinnerMinute;
	private final JComboBox<TipPosla> comboBoxTipPosla;
	private final JTextArea textAreaOpisPosla;
	private final JTextArea textAreaPotrebniMaterijal;
	private final JComboBox<StatusRadnogNaloga> comboBoxStatusNaloga;
	private final JButton btnKreiraj;
	private final JButton btnKreirajPrazanNalog;
	private final JXDatePicker datePickerPlaniraniDatumIzvrsenja;
	private final JXDatePicker datePickerDatumKreiranja;
	private final JXDatePicker datePickerDatumZavrsetkaRadova;
	
	private Zaposlenik korisnik;
	
	public KreiranjeRadnogNaloga() {
		korisnik = GlavniProzor.korisnik;
		
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500, 735));
        
        JLabel lblKreiranjeRadnogNaloga = new JLabel("Kreiranje radnog naloga");
        lblKreiranjeRadnogNaloga.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblKreiranjeRadnogNaloga.setBounds(39, 29, 229, 20);
        this.add(lblKreiranjeRadnogNaloga);
        
        JLabel lblDatumKreiranja = new JLabel("Datum kreiranja:");
        lblDatumKreiranja.setBounds(76, 112, 150, 14);
        this.add(lblDatumKreiranja);
        
        JLabel lblKreirao = new JLabel("Kreirao:");
        lblKreirao.setBounds(76, 137, 150, 14);
        this.add(lblKreirao);
        
        JLabel lblIzvrilac = new JLabel("Izvr\u0161ilac:");
        lblIzvrilac.setBounds(76, 162, 150, 14);
        this.add(lblIzvrilac);
        
        JLabel lblLokacija = new JLabel("Lokacija:");
        lblLokacija.setBounds(76, 187, 150, 14);
        this.add(lblLokacija);
        
        JLabel lblPlaniraniDatumIzvrsenja = new JLabel("Planirani datum izvr\u0161enja:");
        lblPlaniraniDatumIzvrsenja.setBounds(76, 212, 150, 14);
        this.add(lblPlaniraniDatumIzvrsenja);
        
        JLabel lblDatumZavrsetkaRadova = new JLabel("Datum zavr\u0161etka radova:");
        lblDatumZavrsetkaRadova.setBounds(76, 237, 150, 14);
        this.add(lblDatumZavrsetkaRadova);
        
        JLabel lblUtrosenoVrijeme = new JLabel("Utro\u0161eno vrijeme:");
        lblUtrosenoVrijeme.setBounds(76, 262, 150, 14);
        this.add(lblUtrosenoVrijeme);
        
        JLabel lblSati = new JLabel("sati");
        lblSati.setBounds(280, 256, 46, 20);
        add(lblSati);
        
        JLabel lblMinuta = new JLabel("minuta");
        lblMinuta.setBounds(360, 256, 46, 20);
        add(lblMinuta);
        
        JLabel lblTipPosla = new JLabel("Tip posla ili usluge:");
        lblTipPosla.setBounds(76, 287, 150, 14);
        this.add(lblTipPosla);
        
        JLabel lblOpisPosla = new JLabel("Detaljniji opis posla:");
        lblOpisPosla.setBounds(76, 323, 150, 14);
        this.add(lblOpisPosla);
        
        JLabel lblMaterijal = new JLabel("Materijal koji \u0107e biti utro\u0161en:");
        lblMaterijal.setBounds(76, 499, 200, 14);
        this.add(lblMaterijal);
        
        JLabel lblStatusRadnogNaloga = new JLabel("Status radnog naloga:");
        lblStatusRadnogNaloga.setBounds(76, 620, 150, 14);
        //this.add(lblStatusRadnogNaloga);
        
        java.util.List<Zaposlenik> listaZaposlenika = HibernateZaposlenik.dajSveZaposlenike();
        Zaposlenik[] zaposlenici = listaZaposlenika.toArray(new Zaposlenik[listaZaposlenika.size()]);
        
        comboBoxKreirao = new JComboBox<Zaposlenik>();
        //comboBoxKreirao.setEditable(true);
    	comboBoxKreirao.setEnabled(false);
    	comboBoxKreirao.setModel(new DefaultComboBoxModel<Zaposlenik>(new Zaposlenik[]{korisnik}));
        comboBoxKreirao.setBounds(236, 134, 200, 20);
        this.add(comboBoxKreirao);
        
        comboBoxIzvrsilac = new JComboBox<Zaposlenik>();
        //comboBoxIzvrsilac.setEditable(true);
        if (korisnik.dajTipUposlenika() != TipUposlenika.privilegirani) {
        	comboBoxIzvrsilac.setEnabled(false);
        	comboBoxIzvrsilac.setModel(new DefaultComboBoxModel<Zaposlenik>(new Zaposlenik[]{korisnik}));
        }
        else
        	comboBoxIzvrsilac.setModel(new DefaultComboBoxModel<Zaposlenik>(zaposlenici));
        comboBoxIzvrsilac.setBounds(236, 159, 200, 20);
        this.add(comboBoxIzvrsilac);
        
        txtLokacija = new JTextField();
        txtLokacija.setBounds(236, 184, 200, 20);
        this.add(txtLokacija);
        txtLokacija.setColumns(10);
        
        spinnerSati = new JSpinner();
        spinnerSati.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
        spinnerSati.setBounds(236, 256, 40, 20);
        add(spinnerSati);
        
        spinnerMinute = new JSpinner();
        spinnerMinute.setModel(new SpinnerNumberModel(0, 0, 59, 1));
        spinnerMinute.setBounds(316, 256, 40, 20);
        add(spinnerMinute);
        
        comboBoxTipPosla = new JComboBox<TipPosla>();
        comboBoxTipPosla.setModel(new DefaultComboBoxModel<TipPosla>(new TipPosla[] {TipPosla.WomaMasina, TipPosla.UgradnjaVodomjera, TipPosla.ZamjenaVodomjera, TipPosla.ZamjenaCijevi, TipPosla.Ostalo}));
        comboBoxTipPosla.setBounds(236, 284, 200, 20);
        this.add(comboBoxTipPosla);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_1.setBounds(76, 348, 360, 131);
        this.add(scrollPane_1);
        
        textAreaOpisPosla = new JTextArea();
        textAreaOpisPosla.setLineWrap(true);
        scrollPane_1.setViewportView(textAreaOpisPosla);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(76, 524, 358, 100);
        this.add(scrollPane_2);
        
        textAreaPotrebniMaterijal = new JTextArea();
        scrollPane_2.setViewportView(textAreaPotrebniMaterijal);
        textAreaPotrebniMaterijal.setLineWrap(true);
                
        comboBoxStatusNaloga = new JComboBox<StatusRadnogNaloga>();
        if (korisnik.dajTipUposlenika() == TipUposlenika.privilegirani)
        	comboBoxStatusNaloga.setModel(new DefaultComboBoxModel<StatusRadnogNaloga>(new StatusRadnogNaloga[] {StatusRadnogNaloga.kreiran, StatusRadnogNaloga.zakljucen, StatusRadnogNaloga.nezakljucen, StatusRadnogNaloga.storniran}));
        else
        	comboBoxStatusNaloga.setModel(new DefaultComboBoxModel<StatusRadnogNaloga>(new StatusRadnogNaloga[] {StatusRadnogNaloga.kreiran}));
        comboBoxStatusNaloga.setBounds(236, 617, 200, 20);
        //this.add(comboBoxStatusNaloga);
        
        // Datepicker
        datePickerDatumKreiranja = new JXDatePicker(new Date());
        datePickerDatumKreiranja.setLocation(236, 106);
        datePickerDatumKreiranja.setSize(200, 20);
        datePickerDatumKreiranja.setLocale(new Locale("hr", "BA"));
        datePickerDatumKreiranja.setFormats(new String[] {"EEEE dd.MM.yyyy"});
        datePickerDatumKreiranja.setEnabled(false);
        this.add(datePickerDatumKreiranja);
        
        datePickerPlaniraniDatumIzvrsenja = new JXDatePicker();
        datePickerPlaniraniDatumIzvrsenja.setLocation(236, 206);
        datePickerPlaniraniDatumIzvrsenja.setSize(200, 20);
        datePickerPlaniraniDatumIzvrsenja.setLocale(new java.util.Locale("hr"));
        datePickerPlaniraniDatumIzvrsenja.setFormats(new String[] {"EEEE dd.MM.yyyy"});
        this.add(datePickerPlaniraniDatumIzvrsenja);
                
        datePickerDatumZavrsetkaRadova = new JXDatePicker();
        datePickerDatumZavrsetkaRadova.setLocation(236, 231);
        datePickerDatumZavrsetkaRadova.setSize(200, 20);
        datePickerDatumZavrsetkaRadova.setLocale(new java.util.Locale("hr"));
        datePickerDatumZavrsetkaRadova.setFormats(new String[] {"EEEE dd.MM.yyyy"});
        this.add(datePickerDatumZavrsetkaRadova);
        
        btnKreirajPrazanNalog = new JButton("Prazan radni nalog");
        btnKreirajPrazanNalog.setBounds(286, 704, 150, 23);
        this.add(btnKreirajPrazanNalog);
        
        // Kreiraj prazan radni nalog
        btnKreirajPrazanNalog.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
        			Printanje.printajPdf("prazanRadniNalog.pdf");
        		}
        		catch(Exception exc){
        			exc.printStackTrace();
        		}
        	}
        });
        
       
        btnKreiraj = new JButton("Kreiraj");
        
        // Kreiraj radni nalog
        btnKreiraj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		kreirajRadniNalog();
        	}
        	
        	
        });

			

			
        btnKreiraj.setBounds(192, 670, 89, 23);
        this.add(btnKreiraj);
        
        JButton btnKreirajItampaj = new JButton("Kreiraj i štampaj");
        btnKreirajItampaj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RadniNalog rn = kreirajRadniNalog();
        		Printanje.sacuvajPrintajRadniNalogPdf(rn);
        	}

			
        });
        btnKreirajItampaj.setBounds(286, 670, 150, 23);
        add(btnKreirajItampaj);
        
	}
	private RadniNalog kreirajRadniNalog() {
		RadniNalog rn = null;
		try {
			Date datumKreiranja = new Date();
    		long kreirao = GlavniProzor.korisnik.getId();
    		String status = comboBoxStatusNaloga.getSelectedItem().toString();
    		String tip = comboBoxTipPosla.getSelectedItem().toString();
    		Date planiraniDatumIzvrsenja = datePickerPlaniraniDatumIzvrsenja.getDate();
    		long izvrsilac = ((Zaposlenik)comboBoxIzvrsilac.getSelectedItem()).getId();
    		String potrebniMaterijal = textAreaPotrebniMaterijal.getText();
    		String lokacija = txtLokacija.getText();
    		Date datumIzvrsenja = datePickerDatumZavrsetkaRadova.getDate();
    		Time utrosenoVrijeme = new Time((Integer)spinnerSati.getValue(), (Integer)spinnerMinute.getValue(), 0);
    		String opisPosla = textAreaOpisPosla.getText();
    		
    		if (lokacija.isEmpty())
    			throw new Exception("Niste unijeli lokaciju");
    		if (planiraniDatumIzvrsenja == null)
    			throw new Exception("Niste izabrali planiran datum izvršenja radnog naloga");
    		if (opisPosla.isEmpty())
    			throw new Exception("Niste unijeli opis posla");
    		if (potrebniMaterijal.isEmpty())
    			throw new Exception("Niste unijeli potrebni materijal");
    		
			rn = new RadniNalog(datumKreiranja, kreirao, status, tip, planiraniDatumIzvrsenja, izvrsilac, potrebniMaterijal, lokacija, datumIzvrsenja, utrosenoVrijeme, false, opisPosla);
			HibernateRadniNalog.pohraniRadniNalog(rn);
			
			JOptionPane.showMessageDialog(null, "Radni nalog je uspješno kreiran");
			
			dispose();
			
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.WARNING_MESSAGE);
		}
		return rn;
	}
	
	
	private void dispose() {
		// TODO Auto-generated method stub
        datePickerPlaniraniDatumIzvrsenja.setDate(null);
        datePickerDatumZavrsetkaRadova.setDate(null);
        txtLokacija.setText("");
		textAreaOpisPosla.setText("");
		textAreaPotrebniMaterijal.setText("");
	}
	
	 
     	
}
