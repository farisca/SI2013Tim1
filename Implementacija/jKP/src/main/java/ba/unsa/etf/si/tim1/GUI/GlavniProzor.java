package ba.unsa.etf.si.tim1.GUI;

import java.awt.Color;
import javax.swing.*;
import ba.unsa.etf.si.tim1.Entiteti.*;

public class GlavniProzor extends JFrame {
	private JPanel kontejner;
	protected static Zaposlenik korisnik;
	
	public GlavniProzor(Zaposlenik kor) {
		this.setResizable(false);
		korisnik = kor;
		
		kontejner = new JPanel();
		kontejner.setBackground(Color.WHITE);
		kontejner.setLayout(null);
		
		MeniPanel meni = new MeniPanel(kontejner, korisnik,this);
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
