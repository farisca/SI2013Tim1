package ba.unsa.etf.si.tim1.jKP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.sql.Time;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ba.unsa.etf.si.tim1.Hibernate.HibernatePristupniPodaci;
import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZakljuciRadniNalog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private ZakljuciRadniNalog ovaj=this;
	
	private Zaposlenik korisnik;
	private RadniNalog nalog;
	private JTextField textField;
	private JTextField textField_1;
	
	public ZakljuciRadniNalog(Zaposlenik kor, final RadniNalog r, Dialog.ModalityType m, GlavniProzor parent) {
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
			lblNewLabel.setBounds(48, 47, 131, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setText(Long.toString(r.getBrojRadnogNaloga()));
			textField.setBounds(189, 41, 200, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}	
		{
			JLabel lblNewLabel_1 = new JLabel("Datum izvršenja:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(74, 78, 105, 14);
			contentPanel.add(lblNewLabel_1);
		}
		
		// Datepicker
	    UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
	    final JDatePickerImpl datePickerDatumIzvrsenja = new JDatePickerImpl(datePanel);
	    datePickerDatumIzvrsenja.getJFormattedTextField().setBackground(Color.WHITE);
	    datePickerDatumIzvrsenja.setLocation(189, 72);
	    datePickerDatumIzvrsenja.setSize(200, 20);
	    contentPanel.add(datePickerDatumIzvrsenja);
	    
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner.setBounds(189, 103, 29, 20);
		contentPanel.add(spinner);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_1.setBounds(287, 103, 29, 20);
		contentPanel.add(spinner_1);
		
		JLabel lblUtroenoVrijeme = new JLabel("Utrošeno vrijeme:");
		lblUtroenoVrijeme.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUtroenoVrijeme.setBounds(54, 109, 125, 14);
		contentPanel.add(lblUtroenoVrijeme);
		
		JLabel lblSati = new JLabel("Sati");
		lblSati.setBounds(228, 109, 46, 14);
		contentPanel.add(lblSati);
		
		JLabel lblMinuta = new JLabel("Minuta");
		lblMinuta.setBounds(326, 109, 46, 14);
		contentPanel.add(lblMinuta);
		
		JLabel lblDodatniKomentaropcionalno = new JLabel("Dodatni komentar(opcionalno):");
		lblDodatniKomentaropcionalno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDodatniKomentaropcionalno.setBounds(10, 134, 169, 14);
		contentPanel.add(lblDodatniKomentaropcionalno);
		
		textField_1 = new JTextField();
		textField_1.setBounds(189, 129, 200, 62);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton zakljuciButton = new JButton("Zaključi");
				zakljuciButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date d = (Date) datePickerDatumIzvrsenja.getModel().getValue();
						try {
							r.setDatumIzvrsenja(d);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(contentPanel, "Greška u dodjeli datuma izvršenja");
						}
						if((Integer)spinner.getValue()==0 && (Integer)spinner_1.getValue()==0)
							JOptionPane.showMessageDialog(contentPanel, "Niste unijeli utrošeno vrijeme");
						else{
							Time t = new Time((Integer)spinner.getValue(),(Integer)spinner_1.getValue(), 0);
							r.setUtrosenoVrijeme(t);
							r.postaviStatus(StatusRadnogNaloga.zakljucen);
							r.setOsobaKojaZakljucuje(korisnik.getId());
							r.setDodatniKomentar(textField_1.getText());
							HibernateRadniNalog.modifikujRadniNalog(r);
							JOptionPane.showMessageDialog(contentPanel, "Uspješno ste zaključili radni nalog");
							ovaj.dispose();
						}
					}
				});
				buttonPane.add(zakljuciButton);
			}
		}
	}
}
