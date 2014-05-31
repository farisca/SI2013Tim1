package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;
import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import java.util.Calendar;

import javax.swing.SpringLayout;

public class RadniNalozi extends JTabbedPane {

	
	// Varijable za kreiranje radnog naloga
	private final JPanel panelKreiranjeNaloga;
	private final JComboBox<Zaposlenik> comboBoxKreirao;
	private final JComboBox<Zaposlenik> comboBoxIzvrsilac;
	private final JTextField txtLokacija;
	private final JFormattedTextField txtUtrosenoVrijeme;
	private final JComboBox<TipPosla> comboBoxTipPosla;
	private final JTextArea textAreaOpisPosla;
	private final JTextArea textAreaPotrebniMaterijal;
	private final JComboBox<StatusRadnogNaloga> comboBoxStatusNaloga;
	private final JButton btnKreiraj;
	private final JComboBox comboBox_1;
	private final JXDatePicker dp_1;
	private final JButton btnKreirajPrazanNalog;
	
	
	private final Object[][] podaci = new Object[100][10];
	private final JTable tabela = new JTable();
	private final String[] zaglavlje_tabele = {"Broj", 
			"Kreirao", 
			"Izvr\u0161ilac", 
			"Status", 
			"Lokacija", 
			"Datum kreiranja", 
			"Planirani datum izvr\u0161enja", 
			"Datum zavr\u0161etka radova", 
			"Tip posla ili usluge", 
			"Utro\u0161eno vrijeme"};
	
	
	
	private GlavniProzor glavni;
	private final JButton btnTrazi_1;
	
	private Zaposlenik korisnik;
	
	private List<RadniNalog> radni_nalozi = new ArrayList<RadniNalog>();
	private JTextField textField;
	private JTextField textField_1;
	
	public RadniNalozi() {
		
		korisnik = GlavniProzor.korisnik;
		
		this.setBounds(280, 50, 800, 550);
        
        JScrollPane scrollPaneKreiranjeNaloga = new JScrollPane();
        scrollPaneKreiranjeNaloga.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneKreiranjeNaloga.getVerticalScrollBar().setUnitIncrement(20);
        
        // Tab za kreiranje radnih naloga
        this.addTab("Kreiranje", null, scrollPaneKreiranjeNaloga, null);
        
        panelKreiranjeNaloga = new JPanel();
        scrollPaneKreiranjeNaloga.setViewportView(panelKreiranjeNaloga);
        panelKreiranjeNaloga.setLayout(null);
        panelKreiranjeNaloga.setPreferredSize(new java.awt.Dimension(500, 710));
        
        JLabel lblKreiranjeRadnogNaloga = new JLabel("Kreiranje radnog naloga");
        lblKreiranjeRadnogNaloga.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblKreiranjeRadnogNaloga.setBounds(39, 29, 229, 20);
        panelKreiranjeNaloga.add(lblKreiranjeRadnogNaloga);
        
        JLabel lblDatumKreiranja = new JLabel("Datum kreiranja:");
        lblDatumKreiranja.setBounds(76, 112, 150, 14);
        panelKreiranjeNaloga.add(lblDatumKreiranja);
        
        JLabel lblKreirao = new JLabel("Kreirao:");
        lblKreirao.setBounds(76, 137, 150, 14);
        panelKreiranjeNaloga.add(lblKreirao);
        
        JLabel lblIzvrilac = new JLabel("Izvr\u0161ilac:");
        lblIzvrilac.setBounds(76, 162, 150, 14);
        panelKreiranjeNaloga.add(lblIzvrilac);
        
        JLabel lblLokacija = new JLabel("Lokacija:");
        lblLokacija.setBounds(76, 187, 150, 14);
        panelKreiranjeNaloga.add(lblLokacija);
        
        JLabel lblPlaniraniDatumIzvrsenja = new JLabel("Planirani datum izvr\u0161enja:");
        lblPlaniraniDatumIzvrsenja.setBounds(76, 212, 150, 14);
        panelKreiranjeNaloga.add(lblPlaniraniDatumIzvrsenja);
        
        JLabel lblDatumZavrsetkaRadova = new JLabel("Datum zavr\u0161etka radova:");
        lblDatumZavrsetkaRadova.setBounds(76, 237, 150, 14);
        panelKreiranjeNaloga.add(lblDatumZavrsetkaRadova);
        
        JLabel lblUtrosenoVrijeme = new JLabel("Utro\u0161eno vrijeme:");
        lblUtrosenoVrijeme.setBounds(76, 262, 150, 14);
        panelKreiranjeNaloga.add(lblUtrosenoVrijeme);
        
        JLabel lblTipPosla = new JLabel("Tip posla ili usluge:");
        lblTipPosla.setBounds(76, 287, 150, 14);
        panelKreiranjeNaloga.add(lblTipPosla);
        
        JLabel lblOpisPosla = new JLabel("Detaljniji opis posla:");
        lblOpisPosla.setBounds(76, 323, 150, 14);
        panelKreiranjeNaloga.add(lblOpisPosla);
        
        JLabel lblMaterijal = new JLabel("Materijal koji \u0107e biti utro\u0161en:");
        lblMaterijal.setBounds(76, 499, 200, 14);
        panelKreiranjeNaloga.add(lblMaterijal);
        
        JLabel lblStatusRadnogNaloga = new JLabel("Status radnog naloga:");
        lblStatusRadnogNaloga.setBounds(76, 620, 150, 14);
        panelKreiranjeNaloga.add(lblStatusRadnogNaloga);
        

        java.util.List<Zaposlenik> listaZaposlenika = HibernateZaposlenik.dajSveZaposlenike();
        Zaposlenik[] zaposlenici = listaZaposlenika.toArray(new Zaposlenik[listaZaposlenika.size()]);
        
        comboBoxKreirao = new JComboBox<Zaposlenik>();
        //comboBoxKreirao.setEditable(true);
    	comboBoxKreirao.setEnabled(false);
    	comboBoxKreirao.setModel(new DefaultComboBoxModel<Zaposlenik>(new Zaposlenik[]{korisnik}));
        comboBoxKreirao.setBounds(236, 134, 200, 20);
        panelKreiranjeNaloga.add(comboBoxKreirao);
        
        comboBoxIzvrsilac = new JComboBox<Zaposlenik>();
        //comboBoxIzvrsilac.setEditable(true);
        if (korisnik.getTipUposlenika() != TipUposlenika.privilegirani) {
        	comboBoxIzvrsilac.setEnabled(false);
        	comboBoxIzvrsilac.setModel(new DefaultComboBoxModel<Zaposlenik>(new Zaposlenik[]{korisnik}));
        }
        else
        	comboBoxIzvrsilac.setModel(new DefaultComboBoxModel<Zaposlenik>(zaposlenici));
        comboBoxIzvrsilac.setBounds(236, 159, 200, 20);
        panelKreiranjeNaloga.add(comboBoxIzvrsilac);
        
        txtLokacija = new JTextField();
        txtLokacija.setBounds(236, 184, 200, 20);
        panelKreiranjeNaloga.add(txtLokacija);
        txtLokacija.setColumns(10);
        
        txtUtrosenoVrijeme = new JFormattedTextField();
        //txtUtrosenoVrijeme.setEditable(false);
        //txtUtrosenoVrijeme.setEnabled(false);
        txtUtrosenoVrijeme.setBounds(236, 256, 200, 20);
        panelKreiranjeNaloga.add(txtUtrosenoVrijeme);
        
        comboBoxTipPosla = new JComboBox<TipPosla>();
        comboBoxTipPosla.setModel(new DefaultComboBoxModel<TipPosla>(new TipPosla[] {TipPosla.WomaMasina, TipPosla.UgradnjaVodomjera, TipPosla.ZamjenaVodomjera, TipPosla.ZamjenaCijevi, TipPosla.Ostalo}));
        comboBoxTipPosla.setBounds(236, 284, 200, 20);
        panelKreiranjeNaloga.add(comboBoxTipPosla);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_1.setBounds(76, 348, 360, 131);
        panelKreiranjeNaloga.add(scrollPane_1);
        
        textAreaOpisPosla = new JTextArea();
        textAreaOpisPosla.setLineWrap(true);
        scrollPane_1.setViewportView(textAreaOpisPosla);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(76, 524, 358, 70);
        panelKreiranjeNaloga.add(scrollPane_2);
        
        textAreaPotrebniMaterijal = new JTextArea();
        scrollPane_2.setViewportView(textAreaPotrebniMaterijal);
        textAreaPotrebniMaterijal.setLineWrap(true);
                
        comboBoxStatusNaloga = new JComboBox<StatusRadnogNaloga>();
        if (korisnik.getTipUposlenika() == TipUposlenika.privilegirani)
        	comboBoxStatusNaloga.setModel(new DefaultComboBoxModel<StatusRadnogNaloga>(new StatusRadnogNaloga[] {StatusRadnogNaloga.kreiran, StatusRadnogNaloga.zakljucen, StatusRadnogNaloga.nezakljucen, StatusRadnogNaloga.storniran}));
        else
        	comboBoxStatusNaloga.setModel(new DefaultComboBoxModel<StatusRadnogNaloga>(new StatusRadnogNaloga[] {StatusRadnogNaloga.kreiran}));
        comboBoxStatusNaloga.setBounds(236, 617, 200, 20);
        panelKreiranjeNaloga.add(comboBoxStatusNaloga);
        
        // Datepicker
        final JXDatePicker datePickerDatumKreiranja = new JXDatePicker(new Date());
        datePickerDatumKreiranja.setLocation(236, 106);
        datePickerDatumKreiranja.setSize(200, 20);
        datePickerDatumKreiranja.setLocale(new Locale("hr", "BA"));
        datePickerDatumKreiranja.setFormats(new String[] {"EEEE dd.MM.yyyy"});
        datePickerDatumKreiranja.setEnabled(false);
        panelKreiranjeNaloga.add(datePickerDatumKreiranja);
        
        final JXDatePicker datePickerPlaniraniDatumIzvrsenja = new JXDatePicker();
        datePickerPlaniraniDatumIzvrsenja.setLocation(236, 206);
        datePickerPlaniraniDatumIzvrsenja.setSize(200, 20);
        datePickerPlaniraniDatumIzvrsenja.setLocale(new java.util.Locale("hr"));
        datePickerPlaniraniDatumIzvrsenja.setFormats(new String[] {"EEEE dd.MM.yyyy"});
        panelKreiranjeNaloga.add(datePickerPlaniraniDatumIzvrsenja);
                
        final JXDatePicker datePickerDatumZavrsetkaRadova = new JXDatePicker();
        datePickerDatumZavrsetkaRadova.setLocation(236, 231);
        datePickerDatumZavrsetkaRadova.setSize(200, 20);
        datePickerDatumZavrsetkaRadova.setLocale(new java.util.Locale("hr"));
        datePickerDatumZavrsetkaRadova.setFormats(new String[] {"EEEE dd.MM.yyyy"});
        panelKreiranjeNaloga.add(datePickerDatumZavrsetkaRadova);
        
        btnKreirajPrazanNalog = new JButton("Prazan radni nalog");
        btnKreirajPrazanNalog.setBounds(76, 670, 150, 23);
        panelKreiranjeNaloga.add(btnKreirajPrazanNalog);
        
        // Kreiraj prazan radni nalog
        btnKreirajPrazanNalog.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });

        btnKreiraj = new JButton("Kreiraj");
        
        // Kreiraj radni nalog
        btnKreiraj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			Date datumKreiranja = new Date();
            		long kreirao = GlavniProzor.korisnik.getId();
            		StatusRadnogNaloga status = (StatusRadnogNaloga)comboBoxStatusNaloga.getSelectedItem();
            		TipPosla tip = (TipPosla)comboBoxTipPosla.getSelectedItem();
            		Date planiraniDatumIzvrsenja = datePickerPlaniraniDatumIzvrsenja.getDate();
            		long izvrsilac = ((Zaposlenik)comboBoxIzvrsilac.getSelectedItem()).getId();
            		String potrebniMaterijal = textAreaPotrebniMaterijal.getText();
            		String lokacija = txtLokacija.getText();
            		Date datumIzvrsenja = datePickerDatumZavrsetkaRadova.getDate();
            		Time utrosenoVrijeme;
            		if (!txtUtrosenoVrijeme.getText().isEmpty()) {
            			int sati = (int)(Double.parseDouble(txtUtrosenoVrijeme.getText()));
            			int minute = (int)(((Double.parseDouble(txtUtrosenoVrijeme.getText())) - sati)*60);
                		utrosenoVrijeme = new Time(sati, minute, 0);
            		}
            		else
            			utrosenoVrijeme = null;
            		String opisPosla = textAreaOpisPosla.getText();
            		
            		if (lokacija.isEmpty())
            			throw new Exception("Niste unijeli lokaciju");
            		if (opisPosla.isEmpty())
            			throw new Exception("Niste unijeli opis posla");
            		if (potrebniMaterijal.isEmpty())
            			throw new Exception("Niste unijeli potrebni materijal");
            		if (planiraniDatumIzvrsenja == null)
            			throw new Exception("Niste izabrali planiran datum izvršenja radnog naloga");
        			
        			RadniNalog rn = new RadniNalog(datumKreiranja, kreirao, status, tip, planiraniDatumIzvrsenja, izvrsilac, potrebniMaterijal, lokacija, datumIzvrsenja, utrosenoVrijeme, false, opisPosla);
        			HibernateRadniNalog.pohraniRadniNalog(rn);
        			
        			JOptionPane.showMessageDialog(null, "Radni nalog je uspješno kreiran");
        			
        			dispose();
        		}
        		catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.WARNING_MESSAGE);
        		}
        	}

			private void dispose() {
				// TODO Auto-generated method stub
		        datePickerPlaniraniDatumIzvrsenja.setDate(null);
		        datePickerDatumZavrsetkaRadova.setDate(null);
		        txtLokacija.setText("");
				textAreaOpisPosla.setText("");
				textAreaPotrebniMaterijal.setText("");
			}
        });
        btnKreiraj.setBounds(345, 670, 89, 23);
        panelKreiranjeNaloga.add(btnKreiraj);

       
        
        // Tab za pretragu
     // Tab za pretragu
        final JPanel panelPretraga = new JPanel();
        this.addTab("Pretraga", null, panelPretraga, null);
        panelPretraga.setLayout(null);
        
        JLabel lblPretraga = new JLabel("Pretraga radnih naloga");
        lblPretraga.setBounds(39, 29, 198, 20);
        lblPretraga.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelPretraga.add(lblPretraga);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(39, 244, 732, 199);
        panelPretraga.add(scrollPane_3);
        
       
        
        scrollPane_3.setViewportView(tabela);
        tabela.setModel(new DefaultTableModel(podaci, zaglavlje_tabele)); 
        
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(120);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(180);
        tabela.getColumnModel().getColumn(4).setMinWidth(180);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(160);
        tabela.getColumnModel().getColumn(5).setMinWidth(160);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(160);
        tabela.getColumnModel().getColumn(6).setMinWidth(160);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(160);
        tabela.getColumnModel().getColumn(7).setMinWidth(160);
        tabela.getColumnModel().getColumn(8).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(8).setMinWidth(120);
        tabela.getColumnModel().getColumn(9).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(9).setMinWidth(120);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        //Detaljni prikaz radnog naloga
        
        JButton btnDetaljnije = new JButton("Detaljnije");
        btnDetaljnije.setBounds(682, 454, 89, 23);
        btnDetaljnije.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i= tabela.getSelectedRow();
        		if(i!=-1){
        			RadniNalog r= radni_nalozi.get(i);
	        		if (korisnik.getId() == r.getKreatorRadnogNaloga()){
	        			DetaljniRadniNalog novi=new DetaljniRadniNalog(korisnik,radni_nalozi.get(i), Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        			novi.setVisible(true);
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(panelPretraga, "Nemate privilegije za prikaz selektovanog radnog naloga");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(panelPretraga, "Niste izabrali radni nalog iz pretrage");
        		}
        	}
        });
        	
        panelPretraga.add(btnDetaljnije);
        
        // Modifikacija izabranog radnog naloga
        
        JButton btnModifikuj = new JButton("Modifikuj");
        btnModifikuj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i= tabela.getSelectedRow();
        		if(korisnik.getTipUposlenika()==TipUposlenika.privilegirani){
	        		if (i!=-1){
	        			ModificirajRadniNalog novi=new ModificirajRadniNalog(korisnik,radni_nalozi.get(i), Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        			novi.setVisible(true);
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(panelPretraga, "Niste izabrali radni nalog iz pretrage");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(panelPretraga, "Nemate privilegije za modifikovanje radnih naloga");
        		}
        	}
        });
        btnModifikuj.setBounds(583, 454, 89, 23);
        panelPretraga.add(btnModifikuj);
        
        //Storniranje radnih naloga
        
        JButton btnStorniraj = new JButton("Storniraj");
        btnStorniraj.setBounds(484, 454, 89, 23);
        panelPretraga.add(btnStorniraj);
        btnStorniraj.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e){
        		int i= tabela.getSelectedRow();
        		if(korisnik.getTipUposlenika()==TipUposlenika.privilegirani){
	        		if (i!=-1){
	        			RadniNalog r= radni_nalozi.get(i);
	        			if(r.getStatus()==StatusRadnogNaloga.kreiran){
	        				StornirajRadniNalog novi=new StornirajRadniNalog(korisnik,radni_nalozi.get(i), Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        				novi.setVisible(true);
	        			}
	        			else
	        				JOptionPane.showMessageDialog(panelPretraga, "Izabrani radni nalog je zaključen ili storniran.");
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(panelPretraga, "Niste izabrali radni nalog iz pretrage");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(panelPretraga, "Nemate privilegije za storniranje radnih naloga");
        		}
        	}
        	
        });
        
      //Zakljucivanje radnih naloga
        
        JButton btnZakljui = new JButton("Zaključi");
        btnZakljui.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i= tabela.getSelectedRow();
        		if(korisnik.getTipUposlenika()==TipUposlenika.privilegirani){
	        		if (i!=-1){
	        			RadniNalog r= radni_nalozi.get(i);
	        			if(r.getStatus()==StatusRadnogNaloga.kreiran){
	        				ZakljuciRadniNalog novi=new ZakljuciRadniNalog(korisnik,radni_nalozi.get(i), Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        				novi.setVisible(true);
	        			}
	        			else
	        				JOptionPane.showMessageDialog(panelPretraga, "Izabrani radni nalog je zaključen ili storniran.");
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(panelPretraga, "Niste izabrali radni nalog iz pretrage");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(panelPretraga, "Nemate privilegije za zaključivanje radnih naloga");
        		}
                
        	}
        });
        btnZakljui.setBounds(385, 454, 89, 23);
        panelPretraga.add(btnZakljui);
        
        btnTrazi_1 = new JButton("Tra\u017Ei");
        btnTrazi_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		List<String> lista = new ArrayList();
        		
        		if(!textField.getText().equals("")){ lista.add("BROJRADNOGNALOGA"); lista.add(textField.getText()); }
        		if(!textField_1.getText().equals("")){ lista.add("LOKACIJA"); lista.add(textField_1.getText()); }
        		
        		if(comboBox_1.getSelectedIndex() != -1) { 
        			lista.add("IZVRSILACPOSLA"); 
        			Zaposlenik z = (Zaposlenik) comboBox_1.getSelectedItem();
        			lista.add(String.valueOf(z.getId())); 
        			}
        		
        		
        		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		
        		if(dp_1.getDate() != null) {lista.add("DATUMKREIRANJA"); lista.add( (String) df.format(dp_1.getDate()));  }
        		
        		radni_nalozi = HibernateRadniNalog.pretraga(lista);
                
                if (radni_nalozi.size()==0) { 
                	JOptionPane.showMessageDialog(panelPretraga, "Za unesene podatke nije pronadjen niti jedan radni nalog!"); 
                } 
                else {
                	JOptionPane.showMessageDialog(panelPretraga, "Pronadjeni rezultati");
                	upisiPodatkeUTabelu();
                }
        	}
        });
        btnTrazi_1.setBounds(318, 191, 146, 23);
        panelPretraga.add(btnTrazi_1);
        
        JLabel lblBrojRadnogNaloga = new JLabel("Broj radnog naloga:");
        lblBrojRadnogNaloga.setBounds(50, 70, 188, 14);
        panelPretraga.add(lblBrojRadnogNaloga);
        
        JLabel lblIzvrsilac = new JLabel("Izvrsilac:");
        lblIzvrsilac.setBounds(50, 160, 187, 14);
        panelPretraga.add(lblIzvrsilac);
        
        JLabel lblLokacija_1 = new JLabel("Lokacija:");
        lblLokacija_1.setBounds(50, 100, 172, 14);
        panelPretraga.add(lblLokacija_1);
        
		JLabel lblDatumKreiranja_1 = new JLabel("Datum kreiranja:");
        lblDatumKreiranja_1.setBounds(50, 130, 185, 14);
        panelPretraga.add(lblDatumKreiranja_1);
		
        textField = new JTextField();
        textField.setBounds(240, 70, 225, 20);
        panelPretraga.add(textField);
        textField.setColumns(10);
		panelPretraga.add(textField);
        
        textField_1 = new JTextField();
        textField_1.setBounds(240, 100, 225, 20);
        panelPretraga.add(textField_1);
        textField_1.setColumns(10);
		panelPretraga.add(textField_1);
        
        comboBox_1 = new JComboBox();
        comboBox_1.setBounds(240, 160, 225, 20);
        comboBox_1.setModel(new DefaultComboBoxModel<Zaposlenik>(zaposlenici));
        comboBox_1.setSelectedIndex(-1);
        panelPretraga.add(comboBox_1);
        
        
        // Date Time Pic
        dp_1 = new JXDatePicker();
        dp_1.setLocation(240, 130);
        dp_1.setSize(225, 20);
        dp_1.setLocale(new java.util.Locale("hr"));
        dp_1.setFormats(new String[] {"EEEE yyyy-MM-dd"});
        panelPretraga.add(dp_1);
        
       
	}
	
	Zaposlenik[] ucitajSveZaposlenike() {
		
		return null;
	}
	
	 private void upisiPodatkeUTabelu(){
     
     	for(int red=0; red<radni_nalozi.size(); red++){
	        	 RadniNalog rn = radni_nalozi.get(red); 
	        	 
	        	 podaci[red][0] = rn.getBrojRadnogNaloga();
	        	 podaci[red][1] = HibernateZaposlenik.dajZaposlenikaPoPristupnimPodacima(rn.getKreatorRadnogNaloga()).getImeIPrezime(); 
	        	 podaci[red][2] = HibernateZaposlenik.dajZaposlenikaPoPristupnimPodacima(rn.getIzvrsilacPosla()).getImeIPrezime();
	        	 podaci[red][3] = rn.getStatus();
	        	 podaci[red][4] = rn.getLokacija();
	        	 podaci[red][5] = rn.getDatumKreiranja();
	        	 podaci[red][6] = rn.getPlaniraniDatumIzvrsenja();
	        	 podaci[red][7] = rn.getDatumIzvrsenja();
	        	 podaci[red][8] = rn.getPosao();
	        	 podaci[red][9] = rn.getUtrosenoVrijeme();
     	}
	        	 
     	 tabela.setModel(new DefaultTableModel(podaci, zaglavlje_tabele)); 
	         
     	
     }

	public GlavniProzor getGlavni() {
		return glavni;
	}

	public void setGlavni(GlavniProzor glavni) {
		this.glavni = glavni;
	}
}


