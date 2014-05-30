package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;

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

import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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
	private final JComboBox comboKriterijPretrage;
	private final Object[][] podaci = new Object[10][10];
	private final JTable tabela = new JTable();
	private final String[] zaglavlje_tabele = {"Broj naloga", 
			"Kreirao", 
			"Izvr\u0161ilac", 
			"Status", 
			"Lokacija", 
			"Datum kreiranja", 
			"Planirani datum izvr\u0161enja", 
			"Datum zavr\u0161etka radova", 
			"Tip posla ili usluge", 
			"Utro\u0161eno vrijeme"};
	private final String[] kriteriji_pretrage = {"BrojRadnogNaloga", 
			"KreatorRadnogNaloga", 
			"IzvrsilacPosla", 
			"Status", 
			"Lokacija", 
			"DatumKreiranja", 
			"PlaniraniDatumIzvrsenja", 
			"DatumIzvrsenja"};


	private final JTextField textField;
	private final JTextField textField_2;
	private final JTextField textField_3;
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	private final JButton btnTrazi_1;
	private final JButton btnTrazi_2;
	private final JButton btnTrazi_3;
	private final JButton btnTrazi_4;
	
	private Zaposlenik korisnik;
	
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
        panelKreiranjeNaloga.setPreferredSize(new java.awt.Dimension(500, 750));
        
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
        if (korisnik.getTipUposlenika() != TipUposlenika.privilegirani) {
        	comboBoxKreirao.setEnabled(false);
        	comboBoxKreirao.setModel(new DefaultComboBoxModel<Zaposlenik>(new Zaposlenik[]{korisnik}));
        }
        else
        	comboBoxKreirao.setModel(new DefaultComboBoxModel<Zaposlenik>(zaposlenici));
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
        comboBoxStatusNaloga.setModel(new DefaultComboBoxModel<StatusRadnogNaloga>(new StatusRadnogNaloga[] {StatusRadnogNaloga.kreiran, StatusRadnogNaloga.zakljucen, StatusRadnogNaloga.nezakljucen, StatusRadnogNaloga.storniran}));
        comboBoxStatusNaloga.setBounds(236, 617, 200, 20);
        panelKreiranjeNaloga.add(comboBoxStatusNaloga);
        
        // Datepicker
        UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
        final JDatePickerImpl datePickerDatumKreiranja = new JDatePickerImpl(datePanel);
        datePickerDatumKreiranja.getJFormattedTextField().setBackground(Color.WHITE);
        datePickerDatumKreiranja.setLocation(236, 106);
        datePickerDatumKreiranja.setSize(200, 20);
        panelKreiranjeNaloga.add(datePickerDatumKreiranja);
        
        UtilDateModel model_2 = new UtilDateModel();
		JDatePanelImpl datePanel_2 = new JDatePanelImpl(model_2);
        final JDatePickerImpl datePickerPlaniraniDatumIzvrsenja = new JDatePickerImpl(datePanel_2);
        datePickerPlaniraniDatumIzvrsenja.getJFormattedTextField().setBackground(Color.WHITE);
        datePickerPlaniraniDatumIzvrsenja.setLocation(236, 206);
        datePickerPlaniraniDatumIzvrsenja.setSize(200, 20);
        panelKreiranjeNaloga.add(datePickerPlaniraniDatumIzvrsenja);
        
        UtilDateModel model_3 = new UtilDateModel();
		JDatePanelImpl datePanel_3 = new JDatePanelImpl(model_3);
        final JDatePickerImpl datePickerDatumZavrsetkaRadova = new JDatePickerImpl(datePanel_3);
        datePickerDatumZavrsetkaRadova.getJFormattedTextField().setEnabled(false);
        datePickerDatumZavrsetkaRadova.setLocation(236, 231);
        datePickerDatumZavrsetkaRadova.setSize(200, 20);
        panelKreiranjeNaloga.add(datePickerDatumZavrsetkaRadova);
        
		
        btnKreiraj = new JButton("Kreiraj");
        
        // Kreiraj radni nalog
        btnKreiraj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*JOptionPane.showMessageDialog(panel, 
						"Nije implementirano!", 
						"Potvrda", 
						JOptionPane.INFORMATION_MESSAGE);*/
        		
        		Date datumKreiranja = new Date();
        		long kreirao = GlavniProzor.korisnik.getId();
        		StatusRadnogNaloga status = (StatusRadnogNaloga)comboBoxStatusNaloga.getSelectedItem();
        		TipPosla tip = (TipPosla)comboBoxTipPosla.getSelectedItem();
        		Date planiraniDatumIzvrsenja = new Date();
        		long izvrsilac = ((Zaposlenik)comboBoxIzvrsilac.getSelectedItem()).getId();
        		String potrebniMaterijal = textAreaPotrebniMaterijal.getText();
        		String lokacija = txtLokacija.getText();
        		Date datumIzvrsenja = new Date();
        		Time utrosenoVrijeme = new Time(Integer.parseInt(txtUtrosenoVrijeme.getText()));
        		String opisPosla = textAreaOpisPosla.getText();        		
        		
        		RadniNalog rn = new RadniNalog(datumKreiranja, kreirao, status, tip, planiraniDatumIzvrsenja, izvrsilac, potrebniMaterijal, lokacija, datumIzvrsenja, utrosenoVrijeme, false, opisPosla);
        		HibernateRadniNalog.pohraniRadniNalog(rn);
        		dispose();
        	}

			private void dispose() {
				// TODO Auto-generated method stub
				datePickerDatumKreiranja.getJFormattedTextField().setText("");
		        datePickerPlaniraniDatumIzvrsenja.getJFormattedTextField().setText("");
		        datePickerDatumZavrsetkaRadova.getJFormattedTextField().setText("");
		        txtLokacija.setText("");
				textAreaOpisPosla.setText("");
				textAreaPotrebniMaterijal.setText("");
			}
        });
        btnKreiraj.setBounds(345, 700, 89, 23);
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
        
        JLabel lblKriterijPretrage = new JLabel("Kriterij pretrage:");
        lblKriterijPretrage.setBounds(76, 72, 150, 14);
        panelPretraga.add(lblKriterijPretrage);
        
        JLabel lblKljunaRije = new JLabel("Klju\u010Dna rije\u010D:");
        lblKljunaRije.setBounds(76, 97, 150, 14);
        panelPretraga.add(lblKljunaRije);
        
        comboKriterijPretrage = new JComboBox();
        comboKriterijPretrage.setModel(new DefaultComboBoxModel(new String[] {"BrojRadnogNaloga", "KreatorRadnogNaloga", "IzvrsilacPosla", "Status", "Lokacija", "DatumKreiranja", "PlaniraniDatumIzvrsenja", "DatumIzvrsenja"}));
        comboKriterijPretrage.setBounds(236, 72, 200, 20);
        panelPretraga.add(comboKriterijPretrage);
        
        final JTextField textField_1 = new JTextField();
        textField_1.setText("1");
        textField_1.setBounds(236, 94, 200, 20);
        panelPretraga.add(textField_1);
        textField_1.setColumns(10);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(76, 335, 638, 136);
        panelPretraga.add(scrollPane_3);
        
       
        
    //    tabela = new JTable();
        scrollPane_3.setViewportView(tabela);
        tabela.setModel(new DefaultTableModel(podaci, zaglavlje_tabele)); 
        
        
        
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(50);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(50);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(4).setMinWidth(50);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setMinWidth(20);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(6).setMinWidth(20);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(7).setMinWidth(20);
        tabela.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(8).setMinWidth(50);
        tabela.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(9).setMinWidth(20);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JButton btnDetaljnije = new JButton("Detaljnije");
        btnDetaljnije.setBounds(625, 488, 89, 23);
        btnDetaljnije.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(panelPretraga, "Nije implementirano!");
        	}
        });
        	
        panelPretraga.add(btnDetaljnije);
        
        JButton btnModifikuj = new JButton("Modifikuj");
        btnModifikuj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ModificiranjeRadnogNaloga();
        		tabela.setValueAt("Amina Celik", 0, 2);
        		tabela.setValueAt("05.05.2014.", 0, 5);
        		
        	}
        });
        btnModifikuj.setBounds(529, 488, 89, 23);
        panelPretraga.add(btnModifikuj);
        
        JButton btnStorniraj = new JButton("Storniraj");
        btnStorniraj.setBounds(432, 488, 89, 23);
        panelPretraga.add(btnStorniraj);
        btnStorniraj.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e){
        		
        		new StorniranjeRadnogNaloga();
        		tabela.setValueAt("Storniran", 0, 3);
        	}
        	
        });
        
        JButton btnZakljui = new JButton("Zaklju?i");
        btnZakljui.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ZakljucivanjeRadnogNaloga();
        		tabela.setValueAt("Zakljucen", 0, 3);
                
        	}
        });
        btnZakljui.setBounds(333, 488, 89, 23);
        panelPretraga.add(btnZakljui);
        
        JButton btnNoviKriterij_1 = new JButton("Novi kriterij");
        btnNoviKriterij_1.setBounds(604, 68, 110, 23);
        panelPretraga.add(btnNoviKriterij_1);
        btnNoviKriterij_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_2.setVisible(true);
        		btnTrazi_1.setVisible(false);
        	}
        });
        
        btnTrazi_1 = new JButton("Tra\u017Ei");
        btnTrazi_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		String kriterij = comboKriterijPretrage.getSelectedItem().toString();
        		
        		String unos = textField_1.getText();
        		
				List<RadniNalog> z = HibernateRadniNalog.pretraga(kriterij, unos);
                
                if (z.size()==0) { 
                	JOptionPane.showMessageDialog(panelPretraga, "Za unesene podatke nije pronadjen niti jedan radni nalog!"); 
                } 
                else {
                	JOptionPane.showMessageDialog(panelPretraga, "Pronadjeni rezultati");
                	upisiPodatkeUTabelu(z);
                }
        	}
        });
        btnTrazi_1.setBounds(604, 93, 110, 23);
        panelPretraga.add(btnTrazi_1);
		
        //--------------
    //  panel_2 = new JPanel();
        panel_2.setBounds(76, 122, 638, 60);
        panel_2.setLayout(null);
        panel_2.setVisible(false);
        panelPretraga.add(panel_2);
        
        JLabel label = new JLabel("Kriterij pretrage:");
        label.setBounds(0, 11, 150, 14);
        panel_2.add(label);
        
        JLabel label_1 = new JLabel("Ključna riječ:");
        label_1.setBounds(0, 36, 150, 14);
        panel_2.add(label_1);
        
        final JComboBox comboBox = new JComboBox();
        comboBox.setBounds(160, 8, 200, 20);
        comboBox.setModel(new DefaultComboBoxModel(kriteriji_pretrage));
        panel_2.add(comboBox);
        
        textField = new JTextField();
        textField.setText("1");
        textField.setColumns(10);
        textField.setBounds(160, 33, 200, 20);
        panel_2.add(textField);
        
        final JButton btnUkloni_2 = new JButton("Ukloni");
        btnUkloni_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_2.setVisible(false);
        		btnTrazi_1.setVisible(true);
        	}
        });
        btnUkloni_2.setBounds(366, 32, 88, 23);
        panel_2.add(btnUkloni_2);
        
        JButton btnNoviKriterij_2 = new JButton("Novi kriterij");
        btnNoviKriterij_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		panel_3.setVisible(true);
        		btnTrazi_2.setVisible(false);
        		btnUkloni_2.setVisible(false);
        	}
        });
        btnNoviKriterij_2.setBounds(528, 7, 110, 23);
        panel_2.add(btnNoviKriterij_2);
        
        btnTrazi_2 = new JButton("Traži");
        btnTrazi_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		// 1. kriterij i kljucna rijec
        		String kriterij_1 = comboKriterijPretrage.getSelectedItem().toString();
        		String unos_1 = textField_1.getText();
        		
        		// 2. kriterij i kljucna rijec
        		String kriterij_2 = comboBox.getSelectedItem().toString();
        		String unos_2 = textField.getText();
        		
				List<RadniNalog> z = HibernateRadniNalog.pretraga2(kriterij_1, unos_1, kriterij_2, unos_2);
                
                if (z.size()==0) { 
                	JOptionPane.showMessageDialog(panelPretraga, "Za unesene podatke nije pronadjen niti jedan radni nalog!"); 
                } 
                else {
                	JOptionPane.showMessageDialog(panelPretraga, "Pronadjeni rezultati");
                	upisiPodatkeUTabelu(z);
                }
        	}
        });
        btnTrazi_2.setBounds(528, 32, 110, 23);
        panel_2.add(btnTrazi_2);
        
        //panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBounds(76, 193, 638, 60);
        panel_3.setVisible(false);
        panelPretraga.add(panel_3);
        
        JLabel label_2 = new JLabel("Kriterij pretrage:");
        label_2.setBounds(0, 11, 150, 14);
        panel_3.add(label_2);
        
        JLabel label_3 = new JLabel("Ključna riječ:");
        label_3.setBounds(0, 36, 150, 14);
        panel_3.add(label_3);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setBounds(160, 8, 200, 20);
        comboBox_1.setModel(new DefaultComboBoxModel(kriteriji_pretrage));
        panel_3.add(comboBox_1);
        
        textField_2 = new JTextField();
        textField_2.setText("1");
        textField_2.setColumns(10);
        textField_2.setBounds(160, 33, 200, 20);
        panel_3.add(textField_2);
        
        final JButton btnUkloni_3 = new JButton("Ukloni");
        btnUkloni_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_3.setVisible(false);
        		btnTrazi_2.setVisible(true);
        	}
        });
        btnUkloni_3.setBounds(366, 32, 88, 23);
        panel_3.add(btnUkloni_3);
        
        JButton btnNoviKriterij_3 = new JButton("Novi kriterij");
        btnNoviKriterij_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_4.setVisible(true);
        		btnTrazi_3.setVisible(false);
        		btnUkloni_3.setVisible(false);
        	}
        });
        btnNoviKriterij_3.setBounds(528, 7, 110, 23);
        panel_3.add(btnNoviKriterij_3);
        
        btnTrazi_3 = new JButton("Traži");
        btnTrazi_3.setBounds(528, 32, 110, 23);
        panel_3.add(btnTrazi_3);
        
        //panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBounds(76, 264, 638, 60);
        panel_4.setVisible(false);
        panelPretraga.add(panel_4);
        
        JLabel label_4 = new JLabel("Kriterij pretrage:");
        label_4.setBounds(0, 11, 150, 14);
        label_4.setVisible(false);
        panel_4.add(label_4);
        
        JLabel label_5 = new JLabel("Ključna riječ:");
        label_5.setBounds(0, 36, 150, 14);
        panel_4.add(label_5);
        
        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.setBounds(160, 8, 200, 20);
        comboBox_2.setModel(new DefaultComboBoxModel(kriteriji_pretrage));
        panel_4.add(comboBox_2);
        
        textField_3 = new JTextField();
        textField_3.setText("1");
        textField_3.setColumns(10);
        textField_3.setBounds(160, 33, 200, 20);
        panel_4.add(textField_3);
        
        JButton btnUkloni_4 = new JButton("Ukloni");
        btnUkloni_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_4.setVisible(false);
        		btnTrazi_3.setVisible(true);
        	}
        });
        btnUkloni_4.setBounds(366, 32, 88, 23);
        panel_4.add(btnUkloni_4);
        
        btnTrazi_4 = new JButton("Traži");
        btnTrazi_4.setBounds(528, 32, 110, 23);
        panel_4.add(btnTrazi_4);
	}
	
	Zaposlenik[] ucitajSveZaposlenike() {
		
		return null;
	}
	
	 private void upisiPodatkeUTabelu(List<RadniNalog> lista){
     
     	for(int red=0; red<lista.size(); red++){
	        	 RadniNalog rn = lista.get(red); 
	        	 
	        	 podaci[red][0] = rn.getBrojRadnogNaloga();
JOptionPane.showMessageDialog(null,  podaci[red][0].toString() );
	        	 podaci[red][1] = rn.getKreatorRadnogNaloga();
	        	 podaci[red][2] = rn.getIzvrsilacPosla();
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
}


