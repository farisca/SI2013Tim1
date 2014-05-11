package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class KorisnickiPodaci extends JPanel {
	private int korisnik;
	
	public KorisnickiPodaci(int kor) {
		korisnik = kor;
		
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
        if(korisnik == 0) txtKorisnickoIme.setText("admin");
        else txtKorisnickoIme.setText("user");
        txtKorisnickoIme.setBounds(186, 109, 150, 20);
        txtKorisnickoIme.setColumns(10);
        this.add(txtKorisnickoIme);
        
        JTextField txtImeIPrezime = new JTextField();
        txtImeIPrezime.setEditable(false);
        if(korisnik == 0) txtImeIPrezime.setText("admin admin");
        else txtImeIPrezime.setText("user user");
        txtImeIPrezime.setColumns(10);
        txtImeIPrezime.setBounds(186, 134, 150, 20);
        this.add(txtImeIPrezime);
        
        final JPanel panelPassword = new JPanel(); // potraziti alternativu za ovo final
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
        
        JPasswordField pwdStari = new JPasswordField();
        pwdStari.setBounds(167, 53, 130, 20);
        panelPassword.add(pwdStari);
        
        JPasswordField pwdNovi = new JPasswordField();
        pwdNovi.setBounds(167, 78, 130, 20);
        panelPassword.add(pwdNovi);
        
        JPasswordField pwdPotvrdiNovi = new JPasswordField();
        pwdPotvrdiNovi.setBounds(167, 103, 130, 20);
        panelPassword.add(pwdPotvrdiNovi);
        
        JButton btnPromijeni = new JButton("Promijeni");
        btnPromijeni.setBounds(208, 140, 89, 23);
        panelPassword.add(btnPromijeni);
        
        final JButton btnPrikaziPanel = new JButton("Promijeni \u0161ifru"); // potraziti alternativu za ovo final
        btnPrikaziPanel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		btnPrikaziPanel.setVisible(false);
        		panelPassword.setVisible(true);
        	}
        });
        btnPrikaziPanel.setBounds(216, 180, 120, 23);
        this.add(btnPrikaziPanel);

        btnPromijeni.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panelPassword.setVisible(false);
        		btnPrikaziPanel.setVisible(true);
        	}
        });
	}
}
