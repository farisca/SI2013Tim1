package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;

import javax.swing.*;

public class GlavniProzor extends JFrame {
	private JPanel kontejner;
	protected static Zaposlenik korisnik;
	
	public GlavniProzor(Zaposlenik kor) {
		korisnik = kor;
		
		kontejner = new JPanel();
		kontejner.setBackground(Color.WHITE);
		kontejner.setLayout(null);
		
		MeniPanel meni = new MeniPanel(kontejner, korisnik);
		kontejner.add(meni);
		
		RadniNalozi radniNalozi = new RadniNalozi();
		radniNalozi.setGlavni(this);
		kontejner.add(radniNalozi);
			
		getContentPane().add(kontejner);
		
		setBounds(10,10, 1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("jKP");
	}
}
