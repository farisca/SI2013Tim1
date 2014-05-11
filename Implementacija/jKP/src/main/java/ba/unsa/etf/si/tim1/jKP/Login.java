package ba.unsa.etf.si.tim1.jKP;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;


public class Login extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private int korisnik;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}
	
	Login() {
		setTitle("jKP");
		setSize(300, 400);
		JPanel jp = new JPanel();
		jp.setBackground(Color.WHITE);
		jp.setBounds(0, 0, 300, 600);
		getContentPane().add(jp);
		jp.setLayout(null);
		
		ImageIcon image = new ImageIcon("logo.jpg");
        JLabel logo = new JLabel("", image, JLabel.CENTER);
        logo.setBounds(100 ,80, image.getIconWidth(), image.getIconHeight());
        jp.add(logo);
        
        JLabel lblKorisnikoIme = new JLabel("Korisni�ko ime: ");
        lblKorisnikoIme.setBounds(10, 224, 98, 14);
        lblKorisnikoIme.setHorizontalAlignment(SwingConstants.RIGHT);
        jp.add(lblKorisnikoIme);
        
        JLabel lblifra = new JLabel("�ifra: ");
        lblifra.setBounds(22, 249, 86, 14);
        lblifra.setHorizontalAlignment(SwingConstants.RIGHT);
        jp.add(lblifra);
        
        textField = new JTextField();
        textField.setBounds(112, 221, 150, 20);
        jp.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(112, 246, 150, 20);
        jp.add(passwordField);
        
        JButton btnPrijava = new JButton("Prijava");
        btnPrijava.setBackground(Color.LIGHT_GRAY);
        btnPrijava.setBounds(87, 301, 117, 25);
        btnPrijava.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(textField.getText().equals("admin") && String.valueOf(passwordField.getPassword()).equals("admin")) korisnik = 0;
        		else korisnik = 1;
        		GlavniProzor prozor = new GlavniProzor(korisnik);
        		prozor.setVisible(true);
        		setVisible(false);
        		
        	}
        });
        jp.add(btnPrijava);
        
        //pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
}
