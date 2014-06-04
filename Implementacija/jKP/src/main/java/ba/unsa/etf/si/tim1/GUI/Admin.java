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
import ba.unsa.etf.si.tim1.util.Util;
import ba.unsa.etf.si.tim1.util.Validacija;

public class Admin extends JPanel {
	private JTextField txtImePrezime;
	private JTextField txtUsername;
	private JPasswordField pwdSifra;
	private JPasswordField pwdPotvrda;
	
	
	private JComboBox comboBoxTip;
	private Boolean nemaTaba;
	
	private JTextField txtPretraga;
	private JTable table;
	private DefaultTableModel tablemodel;
	private Object[][] data = { { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
			{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" } };

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

		txtPretraga = new JTextField();
		txtPretraga.setBounds(210, 31, 388, 19);
		panelPretraga.add(txtPretraga);
		txtPretraga.setColumns(10);

		Model();

		JButton btnTrazi = new JButton("Traži");
		btnTrazi.setBackground(Color.LIGHT_GRAY);
		btnTrazi.setBounds(610, 28, 117, 25);
		panelPretraga.add(btnTrazi);

		btnTrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopuniTabelu();
			}
		});
		JLabel lblRezultatiPretrage = new JLabel("Rezultati pretrage:");
		lblRezultatiPretrage.setBounds(90, 121, 162, 15);
		panelPretraga.add(lblRezultatiPretrage);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(tablemodel);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(171, 152, 431, 106);
		panelPretraga.add(jsp);
		pomoc = txtPretraga.getText();
		JButton btnAktiviraj = new JButton("(De)aktiviraj");
		btnAktiviraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() == -1)
						throw new Exception("Niste odabrali nijednog zaposlenika!");
					List<Zaposlenik> lz = HibernateZaposlenik.dajZaposlenikePoKriteriju(txtPretraga.getText());
					Zaposlenik novi = lz.get(table.getSelectedRow());
					if (novi.getTipUposlenika().equals("neaktivan")) {
						Object[] options = { "Obični", "Privilegirani",
								"Otkazujem aktivaciju!" };
						int n = JOptionPane.showOptionDialog(panelPretraga,
								"Odabarite vrstu korisnika kojeg aktivirate",
								"Upit", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[2]);
						if (n == 0) {
							novi.postaviTipUposlenika(TipUposlenika.obicni);
							HibernateZaposlenik.urediZaposlenika(novi);
						} else if (n == 1) {
							novi.postaviTipUposlenika(TipUposlenika.privilegirani);
							HibernateZaposlenik.urediZaposlenika(novi);
						}
					}
					else {
						novi.postaviTipUposlenika(TipUposlenika.neaktivan);
						HibernateZaposlenik.urediZaposlenika(novi);
					}
					dispose();
					PopuniTabelu();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(tabovi, e1.getMessage(),
							"Potvrda", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			private void dispose() {
				// TODO Auto-generated method stub

			}
		});
		btnAktiviraj.setBackground(Color.LIGHT_GRAY);
		btnAktiviraj.setBounds(481, 269, 117, 25);
		panelPretraga.add(btnAktiviraj);
		nemaTaba = true;
		JButton btnModifikuj = new JButton("Modifikuj");
		btnModifikuj.setBackground(Color.LIGHT_GRAY);
		btnModifikuj.setBounds(354, 270, 117, 25);
		panelPretraga.add(btnModifikuj);
		btnModifikuj.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() == -1)
						throw new Exception("Niste odabrali nijednog zaposlenika!");
					final List<Zaposlenik> lz = HibernateZaposlenik.dajZaposlenikePoKriteriju(txtPretraga.getText());
					final Zaposlenik novi = lz.get(table.getSelectedRow());
					final JPanel panelNovi = new JPanel();
					panelNovi.setLayout(null);
					if (nemaTaba == true) {
						tabovi.addTab("Modifikuj", panelNovi);
						tabovi.setSelectedComponent(panelNovi);
						nemaTaba = false;
					}
					else {
						tabovi.setSelectedIndex(2);
						txtImePrezime.setText(novi.getIme() + " " + novi.getPrezime());
						txtUsername.setText(HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(novi.getPristupniPodaci()).toString());
					}
					final JLabel lblImeIPrezime_1 = new JLabel("Ime i prezime:");
					lblImeIPrezime_1.setBounds(243, 181, 102, 15);
					lblImeIPrezime_1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
					panelNovi.add(lblImeIPrezime_1);

					JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
					lblKorisnikoIme.setBounds(232, 212, 113, 15);
					lblKorisnikoIme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
					panelNovi.add(lblKorisnikoIme);

					JLabel lblifra = new JLabel("Šifra:");
					lblifra.setBounds(301, 243, 44, 15);
					lblifra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
					panelNovi.add(lblifra);

					JLabel lblPotvrdaifre = new JLabel("Potvrda šifre:");
					lblPotvrdaifre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
					lblPotvrdaifre.setBounds(243, 274, 102, 15);
					panelNovi.add(lblPotvrdaifre);

					txtImePrezime = new JTextField();
					txtImePrezime.setBounds(363, 179, 196, 19);
					panelNovi.add(txtImePrezime);
					txtImePrezime.setColumns(10);
					txtImePrezime.setText(novi.getIme()+" "+novi.getPrezime());
					txtUsername = new JTextField();
					txtUsername.setColumns(10);
					txtUsername.setBounds(363, 210, 196, 19);
					panelNovi.add(txtUsername);
					txtUsername.setText(HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(novi.getPristupniPodaci()).toString());
					pwdSifra = new JPasswordField();
					pwdSifra.setColumns(10);
					pwdSifra.setBounds(363, 241, 196, 19);
					panelNovi.add(pwdSifra);

					pwdPotvrda = new JPasswordField();
					pwdPotvrda.setColumns(10);
					pwdPotvrda.setBounds(363, 272, 196, 19);
					panelNovi.add(pwdPotvrda);

					JButton btnSpasiti = new JButton("Spasiti");
					btnSpasiti.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String imePrezime = txtImePrezime.getText();
								String username = txtUsername.getText();
								String sifra = pwdSifra.getText();
								String potvrdaSifre = pwdPotvrda.getText();
								
								
								Validacija.validirajImeIPrezime(imePrezime);
								Validacija.validirajUsername(username);
								Validacija.validirajPassword(sifra);
								if (!sifra.equals(potvrdaSifre))
									throw new Exception("Šifre nisu iste!");
				
								String[] temp = imePrezime.split(" ");
								temp = Util.remove(temp, "");
								String ime = temp[0].toString();
								temp = Util.remove(temp, 0);
								String prezime = Util.join(temp, " ");
								
								if (comboBoxTip.getSelectedIndex() == 1)
									novi.postaviTipUposlenika(TipUposlenika.privilegirani);
								else
									novi.postaviTipUposlenika(TipUposlenika.obicni);
								
								novi.setIme(ime);
								novi.setPrezime(prezime);
								PristupniPodaci p = HibernateZaposlenik.dajPristupnePodatkePoId(novi);
								p.setKorisnickoIme(username);
								p.setLozinka(HibernatePristupniPodaci.HesirajMD5(sifra));
								HibernatePristupniPodaci.urediPristupnePodatke(p);
								HibernateZaposlenik.urediZaposlenika(novi);
								JOptionPane.showMessageDialog(
										panelNovi,
										"Uspjesno ste ažurirali korisnika "
												+ novi.getIme() + " "
												+ novi.getPrezime()
												+ " koji je sada"
												+ novi.getTipUposlenika()
												+ " uposlenik!", "Potvrda",
										JOptionPane.INFORMATION_MESSAGE);
								tabovi.remove(2);
								nemaTaba = true;
								dispose();
							}
							catch (Exception e1) {
								JOptionPane.showMessageDialog(panelNovi,
									e1.getMessage(), "Potvrda",
									JOptionPane.INFORMATION_MESSAGE);
							}
	
							PopuniTabelu();
						}
	
						private void dispose() {
							// TODO Auto-generated method stub
							txtImePrezime.setText("");
							txtUsername.setText("");
							pwdSifra.setText("");
							pwdPotvrda.setText("");
						}
					});
					btnSpasiti.setBackground(Color.LIGHT_GRAY);
					btnSpasiti.setBounds(635, 450, 117, 25);
					panelNovi.add(btnSpasiti);

					JButton btnOtkazati = new JButton("Otkazati");
					btnOtkazati.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(panelNovi,
									"Modifikacija korisnika otkazana.",
									"Potvrda", JOptionPane.INFORMATION_MESSAGE);
							tabovi.remove(2);
							nemaTaba = true;
							dispose();
						}
	
						private void dispose() {
							// TODO Auto-generated method stub
							txtImePrezime.setText("");
							txtUsername.setText("");
							pwdSifra.setText("");
							pwdPotvrda.setText("");
						}
					});
					btnOtkazati.setBackground(Color.LIGHT_GRAY);
					btnOtkazati.setBounds(504, 450, 117, 25);
					panelNovi.add(btnOtkazati);
	
					JLabel label = new JLabel("Tip:");
					label.setHorizontalAlignment(SwingConstants.RIGHT);
					label.setBounds(243, 302, 102, 15);
					panelNovi.add(label);
	
					comboBoxTip = new JComboBox();
					comboBoxTip.addItem("Obični korisnik");
					comboBoxTip.addItem("Privilegirani korisnik");
					comboBoxTip.setBounds(363, 300, 196, 19);
					panelNovi.add(comboBoxTip);
	
					setBounds(190, 0, 1000, 700);
					setVisible(true);
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(tabovi, e1.getMessage(),
						"Potvrda", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Tab za kreiranje novog korisnika
		final JPanel panelNovi = new JPanel();
		tabovi.addTab("Novi korisnik", panelNovi);
		panelNovi.setLayout(null);

		final JLabel lblImeIPrezime_1 = new JLabel("Ime i prezime:");
		lblImeIPrezime_1.setBounds(243, 181, 102, 15);
		lblImeIPrezime_1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblImeIPrezime_1);

		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setBounds(232, 212, 113, 15);
		lblKorisnikoIme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblKorisnikoIme);

		JLabel lblifra = new JLabel("Šifra:");
		lblifra.setBounds(301, 243, 44, 15);
		lblifra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblifra);

		JLabel lblPotvrdaifre = new JLabel("Potvrda šifre:");
		lblPotvrdaifre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblPotvrdaifre.setBounds(243, 274, 102, 15);
		panelNovi.add(lblPotvrdaifre);

		txtImePrezime = new JTextField();
		txtImePrezime.setBounds(363, 179, 196, 19);
		panelNovi.add(txtImePrezime);
		txtImePrezime.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(363, 210, 196, 19);
		panelNovi.add(txtUsername);

		pwdSifra = new JPasswordField();
		pwdSifra.setColumns(10);
		pwdSifra.setBounds(363, 241, 196, 19);
		panelNovi.add(pwdSifra);

		pwdPotvrda = new JPasswordField();
		pwdPotvrda.setColumns(10);
		pwdPotvrda.setBounds(363, 272, 196, 19);
		panelNovi.add(pwdPotvrda);

		JButton btnSpasiti = new JButton("Spasiti");
		btnSpasiti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String imePrezime = txtImePrezime.getText();
					String username = txtUsername.getText();
					String sifra = pwdSifra.getText();
					String potvrdaSifre = pwdPotvrda.getText();
					
					
					Validacija.validirajImeIPrezime(imePrezime);
					Validacija.validirajUsername(username);
					Validacija.validirajPassword(sifra);
					if (!sifra.equals(potvrdaSifre))
						throw new Exception("Šifre nisu iste!");
	
					String[] temp = imePrezime.split(" ");
					temp = Util.remove(temp, "");
					String ime = temp[0].toString();
					temp = Util.remove(temp, 0);
					String prezime = Util.join(temp, " ");
					
					Zaposlenik z = new Zaposlenik(ime, prezime, TipUposlenika.obicni.toString(), 1);
					if(comboBoxTip.getSelectedIndex() == 1)
						z.postaviTipUposlenika(TipUposlenika.privilegirani);
					else
						z.postaviTipUposlenika(TipUposlenika.obicni);

					HibernateZaposlenik.pohraniZaposlenika(z, HibernatePristupniPodaci.spremiPodatke(username, sifra));
					JOptionPane.showMessageDialog(panelNovi,
							"Uspjesno ste kreirali novog korisnika "
									+ z.getIme() + " "
									+ z.getPrezime() + " koji je "
									+ z.getTipUposlenika() + " uposlenik!",
							"Potvrda", JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(panelNovi, e1.getMessage(), "Potvrda", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			private void dispose() {
				// TODO Auto-generated method stub
				txtImePrezime.setText("");
				txtUsername.setText("");
				pwdSifra.setText("");
				pwdPotvrda.setText("");
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
				txtImePrezime.setText("");
				txtUsername.setText("");
				pwdSifra.setText("");
				pwdPotvrda.setText("");
			}
		});
		btnOtkazati.setBackground(Color.LIGHT_GRAY);
		btnOtkazati.setBounds(504, 450, 117, 25);
		panelNovi.add(btnOtkazati);

		JLabel label = new JLabel("Tip:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(243, 302, 102, 15);
		panelNovi.add(label);

		comboBoxTip = new JComboBox();
		comboBoxTip.addItem("Obični korisnik");
		comboBoxTip.addItem("Privilegirani korisnik");
		comboBoxTip.setBounds(363, 300, 196, 19);
		panelNovi.add(comboBoxTip);

		setBounds(190, 0, 1000, 700);
		setVisible(true);

	}

	public void Model() {

		tablemodel = new DefaultTableModel(new Object[][] {}, new String[] {"Ime i Prezime", "Korisničko ime", "Status" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class
			};

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};

	}

	private void PopuniTabelu() {
		IzbrisiTabelu();
		List<Zaposlenik> lz = HibernateZaposlenik.dajZaposlenikePoKriteriju(txtPretraga.getText());
		for (int i = 0; i < lz.size(); i++) {
			data[i][0] = lz.get(i).getIme() + " " + lz.get(i).getPrezime();
			data[i][1] = HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(lz.get(i).getPristupniPodaci());
			if (lz.get(i).dajTipUposlenika() == TipUposlenika.neaktivan)
				data[i][2] = "Deaktiviran";
			else if (lz.get(i).dajTipUposlenika() == TipUposlenika.obicni)
				data[i][2] = "Obični";
			else
				data[i][2] = "Privilegirani";
		}
		if (lz.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nema rezultata pretrage", "Info", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		for (int j = 0; j < lz.size(); j++) {
			tablemodel.addRow(data[j]);
		}

	}

	private void IzbrisiTabelu() {
		tablemodel.getDataVector().removeAllElements();
		tablemodel.fireTableDataChanged();
	}

}
