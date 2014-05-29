package ba.unsa.etf.si.tim1.jKP;

import ba.unsa.etf.si.tim1.Hibernate.HibernatePristupniPodaci;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Login extends JFrame {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private Zaposlenik korisnik;

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}*/
	
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
        
        JLabel lblKorisnikoIme = new JLabel("Korisni\u010Dko ime: ");
        lblKorisnikoIme.setBounds(10, 224, 98, 14);
        lblKorisnikoIme.setHorizontalAlignment(SwingConstants.RIGHT);
        jp.add(lblKorisnikoIme);
        
        JLabel lblifra = new JLabel("\u0161ifra: ");
        lblifra.setBounds(22, 249, 86, 14);
        lblifra.setHorizontalAlignment(SwingConstants.RIGHT);
        jp.add(lblifra);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(112, 221, 150, 20);
        jp.add(txtUsername);
        txtUsername.setColumns(10);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(112, 246, 150, 20);
        jp.add(txtPassword);
        
        JButton btnPrijava = new JButton("Prijava");
        btnPrijava.setBackground(Color.LIGHT_GRAY);
        btnPrijava.setBounds(87, 301, 117, 25);
        btnPrijava.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (HibernatePristupniPodaci.postojiKorisnik(txtUsername.getText()))
        			JOptionPane.showMessageDialog(null, "true");
        		else
        			JOptionPane.showMessageDialog(null, "false");
        		
        		/*GlavniProzor prozor = new GlavniProzor(korisnik);
        		prozor.setVisible(true);
        		setVisible(false);*/
        		
        	}
        });
        jp.add(btnPrijava);
        
        //pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		RadniNalog rn = new RadniNalog(41, new Date(), new Zaposlenik("Faris", "cakaric", TipUposlenika.obicni, "fcakaric1", "admin"), StatusRadnogNaloga.kreiran, TipPosla.UgradnjaVodomjera, new Date(), null, null, null, null, null, null, null);
		session.save(rn);
		t.commit();*/
	}
}