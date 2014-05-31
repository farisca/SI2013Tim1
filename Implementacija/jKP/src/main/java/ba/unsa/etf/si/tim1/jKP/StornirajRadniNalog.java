package ba.unsa.etf.si.tim1.jKP;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StornirajRadniNalog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private StornirajRadniNalog ovaj=this;
	
	private Zaposlenik korisnik;
	private RadniNalog nalog;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public StornirajRadniNalog(Zaposlenik kor, final RadniNalog r, Dialog.ModalityType m, GlavniProzor parent) {
		super(parent);

		korisnik=kor;
		nalog=r;
		
		this.setModalityType(m);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Broj radnog naloga:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(60, 29, 136, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setText(Long.toString(r.getBrojRadnogNaloga()));
			textField.setBounds(206, 28, 200, 16);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblRazlogStorniranjaRadnog = new JLabel("Razlog storniranja radnog naloga:");
			lblRazlogStorniranjaRadnog.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRazlogStorniranjaRadnog.setBounds(16, 54, 180, 14);
			contentPanel.add(lblRazlogStorniranjaRadnog);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(206, 55, 200, 68);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblDodatniKomentaropcionalno = new JLabel("Dodatni komentar(opcionalno):");
			lblDodatniKomentaropcionalno.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDodatniKomentaropcionalno.setBounds(29, 129, 167, 14);
			contentPanel.add(lblDodatniKomentaropcionalno);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(206, 129, 200, 68);
			contentPanel.add(textField_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton stornirajButton = new JButton("Storniraj");
				stornirajButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(textField_1.getText()==""||textField_1.getText()==null)
							JOptionPane.showMessageDialog(contentPanel, "Niste unijeli razlog storniranja");
						else{
							r.setRazlogStorniranja(textField_1.getText());
							r.setOsobaKojaStornira(korisnik.getId());
							r.setStatus(StatusRadnogNaloga.storniran);
							r.setDodatniKomentar(textField_2.getText());
							HibernateRadniNalog.modifikujRadniNalog(r);
							JOptionPane.showMessageDialog(contentPanel, "Uspješno ste stornirali radni nalog");
							ovaj.dispose();
						}
					}
				});
				buttonPane.add(stornirajButton);
			}
		}
	}

}
