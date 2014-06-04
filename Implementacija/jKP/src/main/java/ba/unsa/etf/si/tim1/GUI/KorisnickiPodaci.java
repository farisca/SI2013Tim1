package ba.unsa.etf.si.tim1.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ba.unsa.etf.si.tim1.Entiteti.*;
import ba.unsa.etf.si.tim1.Hibernate.HibernatePristupniPodaci;
import ba.unsa.etf.si.tim1.util.Validacija;

public class KorisnickiPodaci extends JPanel {
	private Zaposlenik korisnik;
	private PristupniPodaci podaci;
	
	final JPanel panelPassword;
	final JPasswordField pwdStari;
	final JPasswordField pwdNovi;
	final JPasswordField pwdPotvrdiNovi;
	
	final JButton btnPrikaziPanel;
	final JButton btnPromijeni;
	final JButton btnOdustani;
	
	public KorisnickiPodaci(Zaposlenik kor) {
		korisnik = kor;
		podaci = HibernatePristupniPodaci.dajPristupnePodatke(korisnik.getId());
		
		this.setBounds(280, 71, 800, 529);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JLabel lblKorisnickiPodaci = new JLabel("Korisni\u010Dki podaci");
        lblKorisnickiPodaci.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblKorisnickiPodaci.setBounds(39, 29, 229, 20);
        this.add(lblKorisnickiPodaci);
        
        JLabel lblKorisnickoIme = new JLabel("Korisni\u010Dko ime:");
        lblKorisnickoIme.setBounds(76, 112, 100, 14);
        this.add(lblKorisnickoIme);
        
        JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
        lblImeIPrezime.setBounds(76, 137, 100, 14);
        this.add(lblImeIPrezime);
        
        JTextField txtKorisnickoIme = new JTextField();
        txtKorisnickoIme.setEditable(false);
        txtKorisnickoIme.setText(podaci.getKorisnickoIme());
        txtKorisnickoIme.setBounds(186, 109, 150, 20);
        txtKorisnickoIme.setColumns(10);
        this.add(txtKorisnickoIme);
        
        JTextField txtImeIPrezime = new JTextField();
        txtImeIPrezime.setEditable(false);
        txtImeIPrezime.setText(korisnik.toString());
        txtImeIPrezime.setColumns(10);
        txtImeIPrezime.setBounds(186, 134, 150, 20);
        this.add(txtImeIPrezime);
        
        panelPassword = new JPanel();
        panelPassword.setBounds(39, 187, 345, 187);
        this.add(panelPassword);
        panelPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPassword.setLayout(null);
        panelPassword.setVisible(false);
        
        JLabel lblPromjenaSifre = new JLabel("Promjena \u0161ifre");
        lblPromjenaSifre.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPromjenaSifre.setBounds(37, 21, 100, 14);
        panelPassword.add(lblPromjenaSifre);
        
        JLabel lblStaraSifra = new JLabel("Stara \u0161ifra:");
        lblStaraSifra.setBounds(47, 56, 110, 14);
        panelPassword.add(lblStaraSifra);
        
        JLabel lblNovaSifra = new JLabel("Nova \u0161ifra:");
        lblNovaSifra.setBounds(47, 81, 110, 14);
        panelPassword.add(lblNovaSifra);
        
        JLabel lblPotvrdiNovuSifru = new JLabel("Potvrdi novu \u0161ifru:");
        lblPotvrdiNovuSifru.setBounds(47, 106, 110, 14);
        panelPassword.add(lblPotvrdiNovuSifru);
        
        pwdStari = new JPasswordField();
        pwdStari.setBounds(167, 53, 130, 20);
        panelPassword.add(pwdStari);
        
        pwdNovi = new JPasswordField();
        pwdNovi.setBounds(167, 78, 130, 20);
        panelPassword.add(pwdNovi);
        
        pwdPotvrdiNovi = new JPasswordField();
        pwdPotvrdiNovi.setBounds(167, 103, 130, 20);
        panelPassword.add(pwdPotvrdiNovi);
        
        btnPromijeni = new JButton("Promijeni");
        btnPromijeni.setBounds(208, 140, 89, 23);
        panelPassword.add(btnPromijeni);
        
        btnOdustani = new JButton("Odustani");
        btnOdustani.setBounds(100, 140, 89, 23);
        panelPassword.add(btnOdustani);
        
        btnPrikaziPanel = new JButton("Promijeni \u0161ifru");
        btnPrikaziPanel.setBounds(216, 180, 120, 23);
        this.add(btnPrikaziPanel);
        
        
        
        // Prikazuje panel za promjenu lozinke
        btnPrikaziPanel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		btnPrikaziPanel.setVisible(false);
        		panelPassword.setVisible(true);
        	}
        });
        
        // Mijenja lozinku
        btnPromijeni.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			promijeniSifru();
        			JOptionPane.showMessageDialog(null, "Šifra je uspješno promijenjena!");
        			panelPassword.setVisible(false);
            		btnPrikaziPanel.setVisible(true);
            		
            		dispose();
        		}
        		catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage());
        		}
        	}
        	
        	public void dispose() {
        		pwdStari.setText("");
        		pwdNovi.setText("");
        		pwdPotvrdiNovi.setText("");
        	}
        });
        
        // Odustajanje od promjene lozinke
        btnOdustani.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		panelPassword.setVisible(false);
        		btnPrikaziPanel.setVisible(true);
        	}
        	
        	public void dispose() {
        		pwdStari.setText("");
        		pwdNovi.setText("");
        		pwdPotvrdiNovi.setText("");
        	}
        });
	}
	
	void promijeniSifru() throws Exception {
		String stari = pwdStari.getText();
		String novi = pwdNovi.getText();
		String potvrda = pwdPotvrdiNovi.getText();
		
		if (!HibernatePristupniPodaci.HesirajMD5(stari).equals(podaci.getLozinka()))
			throw new Exception("Stara šifra je pogrešna!");
		Validacija.validirajPassword(novi);
		if (!novi.equals(potvrda))
			throw new Exception("Nova šifra i potvrda nove šifre se ne slažu!");
		
		podaci.setLozinka(HibernatePristupniPodaci.HesirajMD5(novi));
		HibernatePristupniPodaci.urediPristupnePodatke(podaci);
	}
}
