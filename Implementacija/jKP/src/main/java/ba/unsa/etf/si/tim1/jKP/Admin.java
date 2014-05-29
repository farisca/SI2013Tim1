package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class Admin extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	private JPasswordField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JComboBox textField_5;

	public Admin() {
		this.setLayout(null);
		this.setBackground(Color.white);
		final JPanel panelPretraga = new JPanel();

		JTabbedPane tabovi = new JTabbedPane();
		tabovi.setSize(800, 550);
		tabovi.setLocation(110, 50);
		this.add(tabovi);
		tabovi.addTab("Pretraga", panelPretraga);
		panelPretraga.setLayout(null);

		JLabel lblNewLabel = new JLabel("Klju�na rije�:");
		lblNewLabel.setBounds(90, 33, 104, 15);
		panelPretraga.add(lblNewLabel);

		textField_4 = new JTextField();
		textField_4.setBounds(210, 31, 388, 19);
		panelPretraga.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText("Faris");

		JButton btnTrai = new JButton("Tra�i");
		btnTrai.setBackground(Color.LIGHT_GRAY);
		btnTrai.setBounds(610, 28, 117, 25);
		panelPretraga.add(btnTrai);

		JLabel lblRezultatiPretrage = new JLabel("Rezultati pretrage:");
		lblRezultatiPretrage.setBounds(90, 121, 162, 15);
		panelPretraga.add(lblRezultatiPretrage);
		String[] columnNames = { "Ime i prezime", "Korisni�ko ime", "Status" };
		Object[][] data = { { "Faris �akari�", "fckaric", "Aktiviran" } };
		table = new JTable((Object[][]) data, columnNames);

		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(171, 152, 431, 106);
		panelPretraga.add(jsp);

		JButton button = new JButton("(De)aktiviraj");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPretraga,
						"Korisnik je uspjesno deaktiviran.", "Potvrda",
						JOptionPane.INFORMATION_MESSAGE);
				table.setValueAt("Deaktiviran", 0, 2);

				dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub

			}
		});
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(481, 269, 117, 25);
		panelPretraga.add(button);

		JButton bModifikuj = new JButton("Modifikuj");
		bModifikuj.setBackground(Color.LIGHT_GRAY);
		bModifikuj.setBounds(354, 270, 117, 25);
		panelPretraga.add(bModifikuj);
		bModifikuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPretraga,
						"Nije implementirano!");
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

		JLabel lblKorisnikoIme = new JLabel("Korisni�ko ime:");
		lblKorisnikoIme.setBounds(232, 212, 113, 15);
		lblKorisnikoIme
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblKorisnikoIme);

		JLabel lblifra = new JLabel("�ifra:");
		lblifra.setBounds(301, 243, 44, 15);
		lblifra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		panelNovi.add(lblifra);

		JLabel lblPotvrdaifre = new JLabel("Potvrda �ifre:");
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
					Zaposlenik NoviZaposlenik = new Zaposlenik(
							ime_i_prezime[0], ime_i_prezime[1],TipUposlenika.obicni, textField_1.getText(),new String(textField_2.getPassword()));
					if (textField_5.getSelectedIndex() == 1)
						NoviZaposlenik
								.setTipUposlenika(TipUposlenika.privilegirani);
					if (textField.getText().length()==0 || ime_i_prezime[1].length()==0)
						throw new Exception("Niste Upisali Ime i Prezime!");
					if (textField_1.getText().length()==0)
						throw new Exception(
								"Niste Upisali Korisničko ime!");
					if(textField_2.getPassword().length==0 || textField_2.getPassword().length==0)
						throw new Exception("Niste upisali lozinku!");
					if (!(Arrays.equals(textField_2.getPassword(), textField_3.getPassword())))
						throw new Exception("Lozinke nisu iste!");
					JOptionPane.showMessageDialog(panelNovi,
							"Uspjesno ste kreirali novog korisnika "
									+ NoviZaposlenik.getIme() + " "
									+ NoviZaposlenik.getPrezime() + " koji je "
									+ NoviZaposlenik.getTipUposlenika() + " uposlenik!",
							"Potvrda", JOptionPane.INFORMATION_MESSAGE);
					//NoviZaposlenik.spasiUBazu();
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
		textField_5.addItem("Obi�ni korisnik");
		textField_5.addItem("Privilegirani korisnik");
		textField_5.setBounds(363, 300, 196, 19);
		panelNovi.add(textField_5);

		setBounds(190, 0, 1000, 700);
		setVisible(true);
	}
}
