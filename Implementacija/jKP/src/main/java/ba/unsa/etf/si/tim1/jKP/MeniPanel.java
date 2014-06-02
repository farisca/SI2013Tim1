package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MeniPanel extends JPanel {
	
	final private JPanel kontejner;
	private Zaposlenik korisnik;
	
	public MeniPanel(final JPanel kontejner, Zaposlenik kor) {
		korisnik = kor;
		
		this.kontejner = kontejner;
		
        this.setBounds(0, 0, 201, 900);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.gray));
        this.setLayout(null);
        
        ImageIcon image = new ImageIcon("logo.jpg");
        JLabel logo = new JLabel("", image, JLabel.CENTER);
        logo.setBounds(60, 30, image.getIconWidth(), image.getIconHeight());
        this.add(logo);
        
        JButton btnRadniNalozi = new JButton("Radni nalozi");
        btnRadniNalozi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		kontejner.remove(1);  // izmijeniti
        		kontejner.add(new RadniNalozi());
        		kontejner.repaint();
        	}
        });
        btnRadniNalozi.setBackground(Color.LIGHT_GRAY);
        btnRadniNalozi.setBounds(25, 145, 153, 48);
        this.add(btnRadniNalozi);
        
        JButton btnIzvjetaji = new JButton("Izvještaji");
        btnIzvjetaji.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		kontejner.remove(1);  // izmijeniti
				kontejner.add(new Izvjestaji());
        		kontejner.repaint();
        		kontejner.revalidate();
        	}
        });
        btnIzvjetaji.setBackground(Color.LIGHT_GRAY);
        btnIzvjetaji.setBounds(25, 208, 153, 48);
        if(korisnik.dajTipUposlenika() == TipUposlenika.privilegirani) this.add(btnIzvjetaji); //ako je privilegirani
        
        JButton btnKorisnickiPodaci = new JButton("Korisni\u010Dki podaci");
        btnKorisnickiPodaci.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		kontejner.remove(1);  // izmijeniti
        		kontejner.add(new KorisnickiPodaci(korisnik));
        		kontejner.repaint();
        	}
        });
        btnKorisnickiPodaci.setBackground(Color.LIGHT_GRAY);
        if(korisnik.dajTipUposlenika() == TipUposlenika.privilegirani) btnKorisnickiPodaci.setBounds(25, 268, 153, 48);
        else btnKorisnickiPodaci.setBounds(25, 208, 153, 48);
        this.add(btnKorisnickiPodaci);
        
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		kontejner.remove(1);  // izmijeniti
        		kontejner.add(new Admin());
        		kontejner.repaint();
        	}
        });
        btnAdmin.setBackground(Color.LIGHT_GRAY);
        btnAdmin.setBounds(25, 328, 153, 48);
        if(korisnik.dajTipUposlenika() == TipUposlenika.privilegirani) this.add(btnAdmin);
        
        JButton btnIzlaz = new JButton("Izlaz");
        btnIzlaz.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnIzlaz.setBackground(Color.LIGHT_GRAY);
        btnIzlaz.setBounds(25, 600, 153, 48);
        this.add(btnIzlaz);
        
	}
}
