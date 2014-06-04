package ba.unsa.etf.si.tim1.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.si.tim1.Entiteti.*;
import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

public class PretragaRadnihNaloga extends JPanel {
	
	private Zaposlenik korisnik;
	
	private final JComboBox<Zaposlenik> comboBoxIzvrsilac;
	private final JComboBox<TipPosla> cmbTipPosla;
	private final JXDatePicker datePickerDatumKreiranja;
	
	private Object[][] podaci = new Object[100][10];
	private final JTable tabela = new JTable();
	private final String[] zaglavljeTabele = {"Broj", 
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
	private final JButton btnTrazi;
	
	private List<RadniNalog> radniNalozi = new ArrayList<RadniNalog>();
	private JTextField txtBrojNaloga;
	private JTextField txtLokacija;
	
	public PretragaRadnihNaloga() {
		korisnik = GlavniProzor.korisnik;
		
		this.setLayout(null);
        
        JLabel lblPretraga = new JLabel("Pretraga radnih naloga");
        lblPretraga.setBounds(39, 29, 198, 20);
        lblPretraga.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.add(lblPretraga);
        
        JLabel lblBrojRadnogNaloga = new JLabel("Broj radnog naloga:");
        lblBrojRadnogNaloga.setBounds(50, 70, 188, 14);
        this.add(lblBrojRadnogNaloga);
        
        JLabel lblIzvrsilac = new JLabel("Izvrsilac:");
        lblIzvrsilac.setBounds(50, 160, 187, 14);
        this.add(lblIzvrsilac);
        
        JLabel lblLokacija_1 = new JLabel("Lokacija:");
        lblLokacija_1.setBounds(50, 100, 172, 14);
        this.add(lblLokacija_1);
        
		JLabel lblDatumKreiranja_1 = new JLabel("Datum kreiranja:");
        lblDatumKreiranja_1.setBounds(50, 130, 185, 14);
        this.add(lblDatumKreiranja_1);
        
        txtBrojNaloga = new JTextField();
        txtBrojNaloga.setBounds(240, 70, 225, 20);
        this.add(txtBrojNaloga);
        txtBrojNaloga.setColumns(10);
        this.add(txtBrojNaloga);
        
        txtLokacija = new JTextField();
        txtLokacija.setBounds(240, 100, 225, 20);
        this.add(txtLokacija);
        txtLokacija.setColumns(10);
        this.add(txtLokacija);
        
		java.util.List<Zaposlenik> listaZaposlenika = HibernateZaposlenik.dajSveZaposlenike();
        Zaposlenik[] zaposlenici = listaZaposlenika.toArray(new Zaposlenik[listaZaposlenika.size()]);
        
		
        comboBoxIzvrsilac = new JComboBox<Zaposlenik>();
        comboBoxIzvrsilac.setBounds(240, 160, 225, 20);
        if (korisnik.dajTipUposlenika() == TipUposlenika.privilegirani) {
        	comboBoxIzvrsilac.setModel(new DefaultComboBoxModel<Zaposlenik>(zaposlenici));
        	comboBoxIzvrsilac.setSelectedIndex(-1);
        }
        else {
        	comboBoxIzvrsilac.setEnabled(false);
        	comboBoxIzvrsilac.setModel(new DefaultComboBoxModel<Zaposlenik>(new Zaposlenik[]{korisnik}));
        }
        this.add(comboBoxIzvrsilac);
        
        
        // Date Time Pic
        datePickerDatumKreiranja = new JXDatePicker();
        datePickerDatumKreiranja.setLocation(240, 130);
        datePickerDatumKreiranja.setSize(225, 20);
        datePickerDatumKreiranja.setLocale(new java.util.Locale("hr"));
        datePickerDatumKreiranja.setFormats(new String[] {"EEEE yyyy-MM-dd"});
        this.add(datePickerDatumKreiranja);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(39, 278, 732, 199);
        this.add(scrollPane_3);
        
        scrollPane_3.setViewportView(tabela);
        tabela.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"Broj", "Kreirao", "Izvr\u0161ilac", "Status", "Lokacija", "Datum kreiranja", "Planirani datum izvr\u0161enja", "Datum zavr\u0161etka radova", "Tip posla ili usluge", "Utro\u0161eno vrijeme"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false, false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
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
        
        
        // Pretraga radnih naloga
        btnTrazi = new JButton("Tra\u017Ei");
        btnTrazi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		List<String> lista = new ArrayList<String>();
        		
        		if (!txtBrojNaloga.getText().equals("")) {
        			lista.add("BROJRADNOGNALOGA");
        			lista.add(txtBrojNaloga.getText());
    			}
        		if (!txtLokacija.getText().equals("")) {
        			lista.add("LOKACIJA");
        			lista.add(txtLokacija.getText());
    			}
        		if (comboBoxIzvrsilac.getSelectedIndex() != -1) {
        			lista.add("IZVRSILACPOSLA");
        			Zaposlenik z = (Zaposlenik) comboBoxIzvrsilac.getSelectedItem();
        			lista.add(String.valueOf(z.getId()));
    			}
        		
        		if (cmbTipPosla.getSelectedIndex() != -1) {
        			lista.add("POSAO");
        			lista.add(cmbTipPosla.getSelectedItem().toString());   			
        		}

        		if (datePickerDatumKreiranja.getDate() != null) {
        			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        			lista.add("DATUMKREIRANJA");
        			lista.add((String)df.format(datePickerDatumKreiranja.getDate()));
    			}
        		
        		if (lista.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Morate unijeti barem jedan kriterij za pretragu!", "Greška", JOptionPane.WARNING_MESSAGE);
        			return;
        		}
        		
        		radniNalozi = HibernateRadniNalog.pretragaPoListiKriterija(lista);
                
                if (radniNalozi == null || radniNalozi.isEmpty()) {
                	JOptionPane.showMessageDialog(null, "Za unesene podatke nije pronađen niti jedan radni nalog!"); 
                } 
                else {
                	upisiPodatkeUTabelu();
                }
        	}
        });
        btnTrazi.setBounds(319, 230, 146, 23);
        this.add(btnTrazi);
        
        //Detaljni prikaz radnog naloga
        JButton btnDetaljnije = new JButton("Detaljnije");
        btnDetaljnije.setBounds(682, 485, 89, 23);
        btnDetaljnije.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = tabela.getSelectedRow();
        		if (i != -1 && i < radniNalozi.size()) {
        			RadniNalog r = radniNalozi.get(i);
	        		if (korisnik.getId() == r.getKreatorRadnogNaloga() || korisnik.dajTipUposlenika() == TipUposlenika.privilegirani) {
	        			DetaljniRadniNalog novi = new DetaljniRadniNalog(korisnik,radniNalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        			novi.setVisible(true);
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(null, "Nemate privilegije za prikaz selektovanog radnog naloga");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Niste izabrali radni nalog iz pretrage");
        		}
        	}
        });
        	
        this.add(btnDetaljnije);
        
        // Modifikacija izabranog radnog naloga
        JButton btnModifikuj = new JButton("Modifikuj");
        btnModifikuj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = tabela.getSelectedRow();
        		if(korisnik.dajTipUposlenika() == TipUposlenika.privilegirani){
	        		if (i != -1 && i < radniNalozi.size()){
	        			ModificirajRadniNalog novi=new ModificirajRadniNalog(korisnik,radniNalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        			novi.setVisible(true);
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(null, "Niste izabrali radni nalog iz pretrage");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Nemate privilegije za modifikovanje radnih naloga");
        		}
        	}
        });
        btnModifikuj.setBounds(583, 485, 89, 23);
        this.add(btnModifikuj);
        
        //Storniranje radnih naloga
        JButton btnStorniraj = new JButton("Storniraj");
        btnStorniraj.setBounds(484, 485, 89, 23);
        this.add(btnStorniraj);
        btnStorniraj.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		int i = tabela.getSelectedRow();
        		if(korisnik.dajTipUposlenika() == TipUposlenika.privilegirani){
	        		if (i != -1 && i < radniNalozi.size()){
	        			RadniNalog r= radniNalozi.get(i);
	        			if(r.dajStatus()==StatusRadnogNaloga.kreiran || r.dajStatus()==StatusRadnogNaloga.nezakljucen){
	        				StornirajRadniNalog novi=new StornirajRadniNalog(korisnik,radniNalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        				novi.setVisible(true);
	        			}
	        			else
	        				JOptionPane.showMessageDialog(null, "Izabrani radni nalog je zaključen ili storniran.");
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(null, "Niste izabrali radni nalog iz pretrage");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Nemate privilegije za storniranje radnih naloga");
        		}
        	}
        });
        
        //Zakljucivanje radnih naloga
        JButton btnZakljuci = new JButton("Zaključi");
        btnZakljuci.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i= tabela.getSelectedRow();
        		if(korisnik.dajTipUposlenika() == TipUposlenika.privilegirani) {
	        		if (i != -1 && i < radniNalozi.size()){
	        			RadniNalog r= radniNalozi.get(i);
	        			if(r.dajStatus()==StatusRadnogNaloga.kreiran || r.dajStatus()==StatusRadnogNaloga.nezakljucen){
	        				ZakljuciRadniNalog novi=new ZakljuciRadniNalog(korisnik,radniNalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
	        				novi.setVisible(true);
	        			}
	        			else
	        				JOptionPane.showMessageDialog(null, "Izabrani radni nalog je zaključen ili storniran.");
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(null, "Niste izabrali radni nalog iz pretrage");
	        		}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Nemate privilegije za zaključivanje radnih naloga");
        		}
                
        	}
        });
        btnZakljuci.setBounds(385, 485, 89, 23);
        this.add(btnZakljuci);
        
        JLabel lblTipPoslaIli = new JLabel("Tip posla ili usluge:");
        lblTipPoslaIli.setBounds(50, 190, 187, 14);
        add(lblTipPoslaIli);
        
        cmbTipPosla = new JComboBox();
        cmbTipPosla.setBounds(240, 190, 225, 20);
        cmbTipPosla.setModel(new DefaultComboBoxModel<TipPosla> (new TipPosla[] {TipPosla.WomaMasina, TipPosla.UgradnjaVodomjera, TipPosla.ZamjenaVodomjera, TipPosla.ZamjenaCijevi, TipPosla.Ostalo}));
        cmbTipPosla.setSelectedIndex(-1);
        add(cmbTipPosla);
        
	}
	
	Zaposlenik[] ucitajSveZaposlenike() {
		return null;
	}
	
	private void upisiPodatkeUTabelu() {
		izbrisiPodatke();
		for(int red = 0; red < podaci.length && red < radniNalozi.size(); red++) {
			RadniNalog rn = radniNalozi.get(red);
			 
			podaci[red][0] = rn.getBrojRadnogNaloga();
			podaci[red][1] = HibernateZaposlenik.dajZaposlenikaPoId(rn.getKreatorRadnogNaloga()).getImeIPrezime(); 
			podaci[red][2] = HibernateZaposlenik.dajZaposlenikaPoId(rn.getIzvrsilacPosla()).getImeIPrezime();
			podaci[red][3] = rn.getStatus();
			podaci[red][4] = rn.getLokacija();
			podaci[red][5] = rn.getDatumKreiranja();
			podaci[red][6] = rn.getPlaniraniDatumIzvrsenja();
			podaci[red][7] = rn.getDatumIzvrsenja();
			podaci[red][8] = rn.getPosao();
			podaci[red][9] = rn.getUtrosenoVrijeme();
     	}
     	tabela.setModel(new DefaultTableModel(podaci, zaglavljeTabele) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false, false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
 	}
	
	private void izbrisiPodatke() {
		podaci = new Object[100][10];
	}

	public GlavniProzor getGlavni() {
		return glavni;
	}

	public void setGlavni(GlavniProzor glavni) {
		this.glavni = glavni;
	}
}
