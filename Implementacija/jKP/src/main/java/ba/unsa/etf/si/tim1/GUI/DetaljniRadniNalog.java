package ba.unsa.etf.si.tim1.GUI;

import java.awt.BorderLayout;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ba.unsa.etf.si.tim1.Entiteti.*;


public class DetaljniRadniNalog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private Zaposlenik korisnik;
	private RadniNalog nalog;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	
	private DetaljniRadniNalog ovaj=this;

	public DetaljniRadniNalog(Zaposlenik kor, RadniNalog r, Dialog.ModalityType m, GlavniProzor parent) {
		super(parent);

		korisnik=kor;
		nalog=r;
		
		this.setModalityType(m);
		setBounds(100, 100, 773, 510);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIdRadnogNaloga = new JLabel("Broj radnog naloga:");
			lblIdRadnogNaloga.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIdRadnogNaloga.setBounds(27, 17, 137, 14);
			contentPanel.add(lblIdRadnogNaloga);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Datum kreiranja:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(62, 42, 102, 14);
		contentPanel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(170, 11, 201, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(170, 36, 201, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblIdKreatoraRadnog = new JLabel("ID kreatora radnog naloga:");
			lblIdKreatoraRadnog.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIdKreatoraRadnog.setBounds(10, 67, 154, 14);
			contentPanel.add(lblIdKreatoraRadnog);
		}
		{
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setColumns(10);
			textField_2.setBounds(170, 61, 201, 20);
			contentPanel.add(textField_2);
		}
		{
			JLabel lblNewLabel = new JLabel("Status:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(71, 92, 93, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField_3 = new JTextField();
			textField_3.setEditable(false);
			textField_3.setColumns(10);
			textField_3.setBounds(170, 86, 201, 20);
			contentPanel.add(textField_3);
		}
		{
			JLabel lblPosao = new JLabel("Posao:");
			lblPosao.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPosao.setBounds(81, 117, 83, 14);
			contentPanel.add(lblPosao);
		}
		{
			textField_4 = new JTextField();
			textField_4.setEditable(false);
			textField_4.setColumns(10);
			textField_4.setBounds(170, 111, 201, 20);
			contentPanel.add(textField_4);
		}
		{
			JLabel lblPlaniraniDatumIzvrenja = new JLabel("Planirani datum izvršenja:");
			lblPlaniraniDatumIzvrenja.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPlaniraniDatumIzvrenja.setBounds(10, 314, 154, 14);
			contentPanel.add(lblPlaniraniDatumIzvrenja);
		}
		{
			textField_5 = new JTextField();
			textField_5.setEditable(false);
			textField_5.setColumns(10);
			textField_5.setBounds(170, 308, 201, 20);
			contentPanel.add(textField_5);
		}
		{
			JLabel lblIdIzvriocaRadnog = new JLabel("ID izvršioca radnog naloga:");
			lblIdIzvriocaRadnog.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIdIzvriocaRadnog.setBounds(10, 364, 154, 14);
			contentPanel.add(lblIdIzvriocaRadnog);
		}
		{
			textField_6 = new JTextField();
			textField_6.setEditable(false);
			textField_6.setColumns(10);
			textField_6.setBounds(170, 358, 201, 20);
			contentPanel.add(textField_6);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Potrebni materijal:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(42, 227, 122, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			textField_7 = new JTextField();
			textField_7.setEditable(false);
			textField_7.setColumns(10);
			textField_7.setBounds(170, 221, 201, 82);
			contentPanel.add(textField_7);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Lokacija:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(81, 389, 83, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textField_8 = new JTextField();
			textField_8.setEditable(false);
			textField_8.setColumns(10);
			textField_8.setBounds(170, 383, 201, 20);
			contentPanel.add(textField_8);
		}
		{
			JLabel lblDatumIzvrenja = new JLabel("Datum izvršenja: ");
			lblDatumIzvrenja.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDatumIzvrenja.setBounds(27, 339, 137, 14);
			contentPanel.add(lblDatumIzvrenja);
		}
		{
			textField_9 = new JTextField();
			textField_9.setEditable(false);
			textField_9.setColumns(10);
			textField_9.setBounds(170, 333, 201, 20);
			contentPanel.add(textField_9);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Utrošeno vrijeme:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(42, 414, 122, 14);
			contentPanel.add(lblNewLabel_4);
		}
		{
			textField_10 = new JTextField();
			textField_10.setEditable(false);
			textField_10.setColumns(10);
			textField_10.setBounds(170, 408, 201, 20);
			contentPanel.add(textField_10);
		}
		{
			JLabel lblOpisPosla = new JLabel("Opis posla:");
			lblOpisPosla.setHorizontalAlignment(SwingConstants.RIGHT);
			lblOpisPosla.setBounds(71, 138, 93, 14);
			contentPanel.add(lblOpisPosla);
		}
		{
			textField_11 = new JTextField();
			textField_11.setEditable(false);
			textField_11.setColumns(10);
			textField_11.setBounds(170, 133, 201, 82);
			contentPanel.add(textField_11);
		}
		{
			JLabel lblIdOsobeKoja = new JLabel("ID osobe koja stornira:");
			lblIdOsobeKoja.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIdOsobeKoja.setBounds(381, 17, 132, 14);
			contentPanel.add(lblIdOsobeKoja);
		}
		{
			textField_12 = new JTextField();
			textField_12.setEditable(false);
			textField_12.setColumns(10);
			textField_12.setBounds(519, 11, 201, 20);
			contentPanel.add(textField_12);
		}
		{
			JLabel lblRazlogStorniranja = new JLabel("Razlog storniranja:");
			lblRazlogStorniranja.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRazlogStorniranja.setBounds(391, 42, 122, 14);
			contentPanel.add(lblRazlogStorniranja);
		}
		{
			textField_13 = new JTextField();
			textField_13.setEditable(false);
			textField_13.setColumns(10);
			textField_13.setBounds(519, 37, 201, 82);
			contentPanel.add(textField_13);
		}
		{
			textField_14 = new JTextField();
			textField_14.setEditable(false);
			textField_14.setColumns(10);
			textField_14.setBounds(519, 151, 201, 82);
			contentPanel.add(textField_14);
		}
		{
			textField_15 = new JTextField();
			textField_15.setEditable(false);
			textField_15.setColumns(10);
			textField_15.setBounds(519, 125, 201, 20);
			contentPanel.add(textField_15);
		}
		{
			JLabel lblIdOsobeKoja_1 = new JLabel("ID osobe koja modifikuje:");
			lblIdOsobeKoja_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIdOsobeKoja_1.setBounds(376, 130, 137, 14);
			contentPanel.add(lblIdOsobeKoja_1);
		}
		{
			JLabel lblRazlogModifikovanja = new JLabel("Razlog modifikovanja:");
			lblRazlogModifikovanja.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRazlogModifikovanja.setBounds(381, 157, 132, 14);
			contentPanel.add(lblRazlogModifikovanja);
		}
		{
			textField_16 = new JTextField();
			textField_16.setEditable(false);
			textField_16.setColumns(10);
			textField_16.setBounds(519, 265, 201, 82);
			contentPanel.add(textField_16);
		}
		{
			textField_17 = new JTextField();
			textField_17.setEditable(false);
			textField_17.setColumns(10);
			textField_17.setBounds(519, 239, 201, 20);
			contentPanel.add(textField_17);
		}
		{
			JLabel lblIdOsobeKoja_2 = new JLabel("ID osobe koja zaključuje:");
			lblIdOsobeKoja_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIdOsobeKoja_2.setBounds(376, 243, 137, 14);
			contentPanel.add(lblIdOsobeKoja_2);
		}
		{
			JLabel lblRazlogZakljuivanja = new JLabel("Dodatni komentar:");
			lblRazlogZakljuivanja.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRazlogZakljuivanja.setBounds(391, 270, 122, 14);
			contentPanel.add(lblRazlogZakljuivanja);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton closeButton = new JButton("Zatvori");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ovaj.dispose();
					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}
		try{
			PopunjavanjePolja(nalog.getBrojRadnogNaloga(),nalog.getDatumKreiranja(),
				nalog.getKreatorRadnogNaloga(), nalog.dajStatus(),
				nalog.dajPosao(), nalog.getPlaniraniDatumIzvrsenja(), nalog.getIzvrsilacPosla(),
				nalog.getPotrebniMaterijal(), nalog.getLokacija(), nalog.getDatumIzvrsenja(), 
				nalog.getUtrosenoVrijeme(),  nalog.getOpisPosla(),  nalog.getOsobaKojaStornira(),
				nalog.getRazlogStorniranja(), nalog.getRazlogModifikovanja(), 
				nalog.getOsobaKojaModifikuje(), nalog.getOsobaKojaZakljucuje(), 
				nalog.getDodatniKomentar());
		}
		catch (Exception e1) {
		}	
	}
	
	public void PopunjavanjePolja(long brRN, Date dKreiranja, long idKreatora, 
			StatusRadnogNaloga stat, TipPosla tip, Date planDatIzvrsenja,
			long idIzvrsioca, String materijal, String lokacija, Date dIzvrsenja,
			Time vrijeme, String opisP, long idOsobaS, String rStorniranja,
			String rModifikovanja, long idOsobaM, long idOsobaZ, String komentar){
		textField.setText(Long.toString(brRN));
		textField_1.setText(String.valueOf(dKreiranja));
		textField_2.setText(Long.toString(idKreatora));
		
		if(stat==StatusRadnogNaloga.kreiran)
			textField_3.setText("Kreiran");
		if(stat==StatusRadnogNaloga.storniran)
			textField_3.setText("Storniran");
		if(stat==StatusRadnogNaloga.zakljucen)
			textField_3.setText("Zaključen");
		
		if(tip==TipPosla.Ostalo)
			textField_4.setText("Ostalo");
		if(tip==TipPosla.UgradnjaVodomjera)
			textField_4.setText("Ugradnja vodomjera");
		if(tip==TipPosla.WomaMasina)
			textField_4.setText("Woma mašina");
		if(tip==TipPosla.ZamjenaCijevi)
			textField_4.setText("Zamjena cijevi");
		if(tip==TipPosla.ZamjenaVodomjera)
			textField_4.setText("Zamjena vodomjera");
		
		textField_5.setText(String.valueOf(planDatIzvrsenja));
		textField_6.setText(Long.toString(idIzvrsioca));
		textField_7.setText(materijal);
		textField_8.setText(lokacija);
		textField_9.setText(String.valueOf(dIzvrsenja));
		textField_10.setText(vrijeme.toString());
		textField_11.setText(opisP);
		
		if(idOsobaS!=-1)
			textField_12.setText(Long.toString(idOsobaS));
		else
			textField_12.setText("");
		
		textField_13.setText(rStorniranja);
		textField_14.setText(rModifikovanja);
		
		if(idOsobaM!=-1)
			textField_15.setText(Long.toString(idOsobaM));
		else
			textField_15.setText("");
		
		textField_16.setText(komentar);
		
		if(idOsobaZ!=-1)
			textField_17.setText(Long.toString(idOsobaZ));
		else
			textField_17.setText("");
	}
}
