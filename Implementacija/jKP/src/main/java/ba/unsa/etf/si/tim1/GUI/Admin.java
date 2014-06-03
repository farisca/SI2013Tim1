package ba.unsa.etf.si.tim1.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ba.unsa.etf.si.tim1.Entiteti.*;
import ba.unsa.etf.si.tim1.Hibernate.HibernatePristupniPodaci;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;

public class Admin extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	private JPasswordField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JComboBox textField_5;
	private Boolean nemaTaba;
	private DefaultTableModel tablemodel;
	private Object[][] data = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
	

	public Admin() {
		this.setLayout(null);
		this.setBackground(Color.white);
		final JPanel panelPretraga = new JPanel();
		final JTabbedPane tabovi = new JTabbedPane();
		String pomoc = null;
		tabovi.setSize(800, 550);
		tabovi.setLocation(110, 50);
		this.add(tabovi);
		tabovi.addTab("Pretraga", panelPretraga);
		panelPretraga.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ključna riječ:");
		lblNewLabel.setBounds(90, 33, 104, 15);
		panelPretraga.add(lblNewLabel);

		textField_4 = new JTextField();
		textField_4.setBounds(210, 31, 388, 19);
		panelPretraga.add(textField_4);
		textField_4.setColumns(10);
		
		
		Model();

		JButton btnTrai = new JButton("Traži");
		btnTrai.setBackground(Color.LIGHT_GRAY);
		btnTrai.setBounds(610, 28, 117, 25);
		panelPretraga.add(btnTrai);
		
		btnTrai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopuniTabelu();
				}
		});
		JLabel lblRezultatiPretrage = new JLabel("Rezultati pretrage:");
		lblRezultatiPretrage.setBounds(90, 121, 162, 15);
		panelPretraga.add(lblRezultatiPretrage);
		table = new JTable();
		table.setModel(tablemodel);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(171, 152, 431, 106);
		panelPretraga.add(jsp);
		pomoc = textField_4.getText();
		JButton button = new JButton("(De)aktiviraj");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Zaposlenik> lz = HibernateZaposlenik.dajZaposlenikePoKriteriju(textField_4.getText());
				Zaposlenik novi = lz.get(table.getSelectedRow());
				if(novi.getTipUposlenika().toString()=="neaktivan") {
					Object[] options = {"Obični",
		                    "Privilegirani",
		                    "Otkazujem aktivaciju!"};
					int n = JOptionPane.showOptionDialog(panelPretraga,
							"Odabarite vrstu korisnika kojeg aktivirate",
									"Upit",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null,
									options,
									options[2]);
					if(n==0) {
						novi.postaviTipUposlenika(TipUposlenika.obicni);
						HibernateZaposlenik.urediZaposlenika(novi);
					}
					else if(n==1) {
						novi.postaviTipUposlenika(TipUposlenika.privilegirani);
						HibernateZaposlenik.urediZaposlenika(novi);
					}
				}
				else {
					novi.postaviTipUposlenika(TipUposlenika.neaktivan);
					HibernateZaposlenik.urediZaposlenika(novi);
				}
				JOptionPane.showMessageDialog(panelPretraga,
						novi.getPrezime(), "Potvrda",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
				PopuniTabelu();
			}

			private void dispose() {
				// TODO Auto-generated method stub

			}
		});
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(481, 269, 117, 25);
		panelPretraga.add(button);
		nemaTaba = true;
		JButton bModifikuj = new JButton("Modifikuj");
		bModifikuj.setBackground(Color.LIGHT_GRAY);
		bModifikuj.setBounds(354, 270, 117, 25);
		panelPretraga.add(bModifikuj);
		bModifikuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final List<Zaposlenik> lz = HibernateZaposlenik.dajZaposlenikePoKriteriju(textField_4.getText());
				final Zaposlenik novi = lz.get(table.getSelectedRow());
				final JPanel panelNovi = new JPanel();
				panelNovi.setLayout(null);
				if(nemaTaba==true) {
					tabovi.addTab("Modifikuj", panelNovi);
					nemaTaba=false;
				}
				tabovi.setSelectedComponent(panelNovi);
				
				final JLabel lblImeIPrezime_1 = new JLabel("Ime i prezime:");
				lblImeIPrezime_1.setBounds(243, 181, 102, 15);
				lblImeIPrezime_1
						.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
				panelNovi.add(lblImeIPrezime_1);

				JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
				lblKorisnikoIme.setBounds(232, 212, 113, 15);
				lblKorisnikoIme
						.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
				panelNovi.add(lblKorisnikoIme);

				JLabel lblifra = new JLabel("Šifra:");
				lblifra.setBounds(301, 243, 44, 15);
				lblifra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
				panelNovi.add(lblifra);

				JLabel lblPotvrdaifre = new JLabel("Potvrda šifre:");
				lblPotvrdaifre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
				lblPotvrdaifre.setBounds(243, 274, 102, 15);
				panelNovi.add(lblPotvrdaifre);

				textField = new JTextField();
				textField.setBounds(363, 179, 196, 19);
				panelNovi.add(textField);
				textField.setColumns(10);
				textField.setText(novi.getIme()+" "+novi.getPrezime());
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(363, 210, 196, 19);
				panelNovi.add(textField_1);
				textField_1.setText(HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(novi.getPristupniPodaci()).toString());
				textField_2 = new JPasswordField();
				textField_2.setColumns(10);
				textField_2.setBounds(363, 241, 196, 19);
				panelNovi.add(textField_2);

				textField_3 = new JPasswordField();
				textField_3.setColumns(10);
				textField_3.setBounds(363, 272, 196, 19);
				panelNovi.add(textField_3);

				JButton btnSpasiti = new JButton("Spasiti");
				btnSpasiti.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String[] ime_i_prezime = textField.getText().split(" ");
							String ki = textField_1.getText();
							String pass1 = textField_2.getText();
							String pass2 = textField_3.getText();
							if (ime_i_prezime[0].length()==0 || ime_i_prezime[1].length()==0)
								throw new Exception("Niste upisali ime i prezime!");
							if (ki.length()==0)
								throw new Exception("Niste upisali korisničko ime!");
							if(pass1.length()==0 || pass2.length()==0)
								throw new Exception("Niste upisali lozinku!");
							if (!Arrays.equals(textField_2.getPassword(), textField_3.getPassword()))
								throw new Exception("Lozinke nisu iste!");
							novi.setIme(ime_i_prezime[0]);
							novi.setPrezime(ime_i_prezime[1]);
							if(textField_5.getSelectedIndex()==1)
								novi.postaviTipUposlenika(TipUposlenika.privilegirani);
							else
								novi.postaviTipUposlenika(TipUposlenika.obicni);
							PristupniPodaci p = HibernateZaposlenik.dajPristupnePodatkePoId(novi);
							p.setKorisnickoIme(ki);
							p.setLozinka(HibernatePristupniPodaci.HesirajMD5(pass1));
							HibernatePristupniPodaci.urediPristupnePodatke(p);
							HibernateZaposlenik.urediZaposlenika(novi);
							JOptionPane.showMessageDialog(panelNovi,
									"Uspjesno ste ažurirali korisnika "
											+ novi.getIme() + " "
											+ novi.getPrezime() + " koji je sada"
											+ novi.getTipUposlenika() + " uposlenik!",
									"Potvrda", JOptionPane.INFORMATION_MESSAGE);
							tabovi.remove(2);
							nemaTaba=true;
							dispose();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(panelNovi, e1.getMessage(),
									"Potvrda", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
						PopuniTabelu();
					}

					private void dispose() {
						// TODO Auto-generated method stub
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
					}
				});
				btnSpasiti.setBackground(Color.LIGHT_GRAY);
				btnSpasiti.setBounds(635, 450, 117, 25);
				panelNovi.add(btnSpasiti);

				JButton btnOtkazati = new JButton("Otkazati");
				btnOtkazati.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(panelNovi,
								"Kreiranje novog korisnika otkazano.", "Potvrda",
								JOptionPane.INFORMATION_MESSAGE);
						tabovi.remove(2);
						nemaTaba=true;
						dispose();
					}

					private void dispose() {
						// TODO Auto-generated method stub
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
					}
				});
				btnOtkazati.setBackground(Color.LIGHT_GRAY);
				btnOtkazati.setBounds(504, 450, 117, 25);
				panelNovi.add(btnOtkazati);

				JLabel label = new JLabel("Tip:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setBounds(243, 302, 102, 15);
				panelNovi.add(label);

				textField_5 = new JComboBox();
				textField_5.addItem("Obični korisnik");
				textField_5.addItem("Privilegirani korisnik");
				textField_5.setBounds(363, 300, 196, 19);
				panelNovi.add(textField_5);

				setBounds(190, 0, 1000, 700);
				setVisible(true);
				
				
			}
		});

		// glavni dio
		final JPanel panelNovi = new JPanel();
		tabovi.addTab("Novi korisnik", panelNovi);
		panelNovi.setLayout(null);

		final JLabel lblImeIPrezime_1 = new JLabel("Ime i prezime:");
		lblImeIPrezime_1.setBounds(243, 181, 102, 15);
		lblImeIPrezime_1
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblImeIPrezime_1);

		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setBounds(232, 212, 113, 15);
		lblKorisnikoIme
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblKorisnikoIme);

		JLabel lblifra = new JLabel("Šifra:");
		lblifra.setBounds(301, 243, 44, 15);
		lblifra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblifra);

		JLabel lblPotvrdaifre = new JLabel("Potvrda šifre:");
		lblPotvrdaifre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblPotvrdaifre.setBounds(243, 274, 102, 15);
		panelNovi.add(lblPotvrdaifre);

		textField = new JTextField();
		textField.setBounds(363, 179, 196, 19);
		panelNovi.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(363, 210, 196, 19);
		panelNovi.add(textField_1);

		textField_2 = new JPasswordField();
		textField_2.setColumns(10);
		textField_2.setBounds(363, 241, 196, 19);
		panelNovi.add(textField_2);

		textField_3 = new JPasswordField();
		textField_3.setColumns(10);
		textField_3.setBounds(363, 272, 196, 19);
		panelNovi.add(textField_3);

		JButton btnSpasiti = new JButton("Spasiti");
		btnSpasiti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] ime_i_prezime = textField.getText().split(" ");
					String ki = textField_1.getText();
					String pass1 = textField_2.getText();
					String pass2 = textField_3.getText();
					if (ime_i_prezime[0].length()==0 || ime_i_prezime[1].length()==0)
						throw new Exception("Niste Upisali Ime i Prezime!");
					if (ki.length()==0)
						throw new Exception("Niste Upisali Korisničko ime!");
					if(pass1.length()==0 || pass2.length()==0)
						throw new Exception("Niste upisali lozinku!");
					if (!Arrays.equals(textField_2.getPassword(), textField_3.getPassword()))
						throw new Exception("Lozinke nisu iste!");
					Zaposlenik z = new Zaposlenik(ime_i_prezime[0],ime_i_prezime[1],TipUposlenika.obicni.toString(),1);
					if(textField_5.getSelectedIndex()==1)
						z.postaviTipUposlenika(TipUposlenika.privilegirani);
					else
						z.postaviTipUposlenika(TipUposlenika.obicni);
					HibernateZaposlenik.pohraniZaposlenika(z, HibernatePristupniPodaci.spremiPodatke(ki, pass1));
					JOptionPane.showMessageDialog(panelNovi,
							"Uspjesno ste kreirali novog korisnika "
									+ z.getIme() + " "
									+ z.getPrezime() + " koji je "
									+ z.getTipUposlenika() + " uposlenik!",
							"Potvrda", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(z.getPrezime());
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(panelNovi, e1.getMessage(),
							"Potvrda", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			private void dispose() {
				// TODO Auto-generated method stub
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnSpasiti.setBackground(Color.LIGHT_GRAY);
		btnSpasiti.setBounds(635, 450, 117, 25);
		panelNovi.add(btnSpasiti);

		JButton btnOtkazati = new JButton("Otkazati");
		btnOtkazati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelNovi,
						"Kreiranje novog korisnika otkazano.", "Potvrda",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnOtkazati.setBackground(Color.LIGHT_GRAY);
		btnOtkazati.setBounds(504, 450, 117, 25);
		panelNovi.add(btnOtkazati);

		JLabel label = new JLabel("Tip:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(243, 302, 102, 15);
		panelNovi.add(label);

		textField_5 = new JComboBox();
		textField_5.addItem("Obični korisnik");
		textField_5.addItem("Privilegirani korisnik");
		textField_5.setBounds(363, 300, 196, 19);
		panelNovi.add(textField_5);

		setBounds(190, 0, 1000, 700);
		setVisible(true);
		
	
		
	}

	public void Model() {
		
		tablemodel=new DefaultTableModel(
				new Object[][] {}, new String[] {"Ime i Prezime", "Korisničko ime", "Status"})
		{
			Class[] columnTypes = new Class[]{
					String.class, String.class, String.class
					
			};
			public Class getColumnClass (int columnIndex){
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[]{
					true, true, true
			};
			public boolean isCellEditable(int row, int column){
				return columnEditables[column];
			}
			
		};
		
	}
	
private void PopuniTabelu()
{
	IzbrisiTabelu();
	List<Zaposlenik> lz = HibernateZaposlenik.dajZaposlenikePoKriteriju(textField_4.getText());
	for(int i=0;i<lz.size();i++) {
		data[i][0] = lz.get(i).getIme() + " " + lz.get(i).getPrezime();
		data[i][1] = HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(lz.get(i).getPristupniPodaci());
		if(lz.get(i).dajTipUposlenika()==TipUposlenika.neaktivan)
			data[i][2] = "Deaktiviran";
		else if(lz.get(i).dajTipUposlenika()==TipUposlenika.obicni)
			data[i][2] = "Obični";
		else
			data[i][2] = "Privilegirani";
	}
	if(data.length==0)
	{
		JOptionPane.showMessageDialog(null, "Nema rezultata pretrage","Info",JOptionPane.INFORMATION_MESSAGE);
		return;
	}
	for(int j=0;j<lz.size();j++)
	{
		
		tablemodel.addRow(data[j]);
	}
	
	
}

private void IzbrisiTabelu(){
	tablemodel.getDataVector().removeAllElements();
	tablemodel.fireTableDataChanged();
	
}

}
