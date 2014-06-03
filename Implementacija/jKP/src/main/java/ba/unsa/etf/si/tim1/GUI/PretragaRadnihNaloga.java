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
	
	private final JComboBox comboBox_1;
	private final JXDatePicker dp_1;
	
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
	
	private List<RadniNalog> radni_nalozi = new ArrayList<RadniNalog>();
	private JTextField textField;
	private JTextField textField_1;
	
	public PretragaRadnihNaloga() {
		korisnik = GlavniProzor.korisnik;
		
		this.setLayout(null);
        
        JLabel lblPretraga = new JLabel("Pretraga radnih naloga");
        lblPretraga.setBounds(39, 29, 198, 20);
        lblPretraga.setFont(new Font("Tahoma", Font.BOLD, 16));
        this.add(lblPretraga);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(39, 244, 732, 199);
        this.add(scrollPane_3);
        
       
        
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
	        			DetaljniRadniNalog novi=new DetaljniRadniNalog(korisnik,radni_nalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
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
        		int i= tabela.getSelectedRow();
        		if(korisnik.dajTipUposlenika()==TipUposlenika.privilegirani){
	        		if (i!=-1){
	        			ModificirajRadniNalog novi=new ModificirajRadniNalog(korisnik,radni_nalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
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
        btnModifikuj.setBounds(583, 454, 89, 23);
        this.add(btnModifikuj);
        
        //Storniranje radnih naloga
        
        JButton btnStorniraj = new JButton("Storniraj");
        btnStorniraj.setBounds(484, 454, 89, 23);
        this.add(btnStorniraj);
        btnStorniraj.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e){
        		int i= tabela.getSelectedRow();
        		if(korisnik.dajTipUposlenika()==TipUposlenika.privilegirani){
	        		if (i!=-1){
	        			RadniNalog r= radni_nalozi.get(i);
	        			if(r.dajStatus()==StatusRadnogNaloga.kreiran || r.dajStatus()==StatusRadnogNaloga.nezakljucen){
	        				StornirajRadniNalog novi=new StornirajRadniNalog(korisnik,radni_nalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
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
        
        JButton btnZakljui = new JButton("Zaključi");
        btnZakljui.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i= tabela.getSelectedRow();
        		if(korisnik.dajTipUposlenika()==TipUposlenika.privilegirani){
	        		if (i!=-1){
	        			RadniNalog r= radni_nalozi.get(i);
	        			if(r.dajStatus()==StatusRadnogNaloga.kreiran || r.dajStatus()==StatusRadnogNaloga.nezakljucen){
	        				ZakljuciRadniNalog novi=new ZakljuciRadniNalog(korisnik,radni_nalozi.get(i), java.awt.Dialog.ModalityType.APPLICATION_MODAL, glavni);
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
        btnZakljui.setBounds(385, 454, 89, 23);
        this.add(btnZakljui);
        
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
                	JOptionPane.showMessageDialog(null, "Za unesene podatke nije pronadjen niti jedan radni nalog!"); 
                } 
                else {
                	JOptionPane.showMessageDialog(null, "Pronadjeni rezultati");
                	upisiPodatkeUTabelu();
                }
        	}
        });
        btnTrazi_1.setBounds(318, 191, 146, 23);
        this.add(btnTrazi_1);
        
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
		
        textField = new JTextField();
        textField.setBounds(240, 70, 225, 20);
        this.add(textField);
        textField.setColumns(10);
        this.add(textField);
        
        textField_1 = new JTextField();
        textField_1.setBounds(240, 100, 225, 20);
        this.add(textField_1);
        textField_1.setColumns(10);
        this.add(textField_1);
        
		java.util.List<Zaposlenik> listaZaposlenika = HibernateZaposlenik.dajSveZaposlenike();
        Zaposlenik[] zaposlenici = listaZaposlenika.toArray(new Zaposlenik[listaZaposlenika.size()]);
		
        comboBox_1 = new JComboBox();
        comboBox_1.setBounds(240, 160, 225, 20);
        comboBox_1.setModel(new DefaultComboBoxModel<Zaposlenik>(zaposlenici));
        comboBox_1.setSelectedIndex(-1);
        this.add(comboBox_1);
        
        
        // Date Time Pic
        dp_1 = new JXDatePicker();
        dp_1.setLocation(240, 130);
        dp_1.setSize(225, 20);
        dp_1.setLocale(new java.util.Locale("hr"));
        dp_1.setFormats(new String[] {"EEEE yyyy-MM-dd"});
        this.add(dp_1);
        
       
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
