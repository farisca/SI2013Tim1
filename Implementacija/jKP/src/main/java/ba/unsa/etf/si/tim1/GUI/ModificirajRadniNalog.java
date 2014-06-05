package ba.unsa.etf.si.tim1.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.sql.Time;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import ba.unsa.etf.si.tim1.Entiteti.*;
import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class ModificirajRadniNalog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private ModificirajRadniNalog ovaj=this;
	
	private Zaposlenik korisnik;
	private RadniNalog nalog;
	private JTextField textField;
	private JComboBox<StatusRadnogNaloga> comboBoxStatusNaloga;
	private JComboBox<TipPosla> comboBoxTipPosla;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextField textField_10;
	private JLabel lblNewLabel;
	private JButton modifikujButton;
	private JPanel buttonPane;
	private JTextField textField_1;
	private JTextField textField_5;
	private JTextField textField_9;
	
	public ModificirajRadniNalog(Zaposlenik kor, final RadniNalog r, Dialog.ModalityType m, GlavniProzor parent) {
		super(parent);

		korisnik=kor;
		nalog=r;
		
		this.setModalityType(m);
		setBounds(100, 100, 760, 511);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("Broj radnog naloga:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(37, 14, 119, 14);
			contentPanel.add(lblNewLabel);
		}
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText(Long.toString(r.getBrojRadnogNaloga()));
		textField.setBounds(166, 11, 200, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		comboBoxStatusNaloga = new JComboBox<StatusRadnogNaloga>();
        comboBoxStatusNaloga.setModel(new DefaultComboBoxModel<StatusRadnogNaloga>(new StatusRadnogNaloga[] {StatusRadnogNaloga.kreiran, StatusRadnogNaloga.zakljucen, StatusRadnogNaloga.nezakljucen, StatusRadnogNaloga.storniran}));
        comboBoxStatusNaloga.setBounds(166, 42, 200, 20);
        if(r.getStatus()=="kreiran")
        	comboBoxStatusNaloga.setSelectedIndex(0);
        if(r.getStatus()=="nezakljucen")
        	comboBoxStatusNaloga.setSelectedIndex(2);
        if(r.getStatus()=="storniran")
        	comboBoxStatusNaloga.setSelectedIndex(3);
        if(r.getStatus()=="zakljucen")
        	comboBoxStatusNaloga.setSelectedIndex(1);
        contentPanel.add(comboBoxStatusNaloga);
        
        comboBoxTipPosla = new JComboBox<TipPosla>();
        comboBoxTipPosla.setModel(new DefaultComboBoxModel<TipPosla>(new TipPosla[] {TipPosla.WomaMasina, TipPosla.UgradnjaVodomjera, TipPosla.ZamjenaVodomjera, TipPosla.ZamjenaCijevi, TipPosla.Ostalo}));
        comboBoxTipPosla.setBounds(166, 73, 200, 20);
        if(r.getPosao()=="WomaMasina")
        	comboBoxTipPosla.setSelectedIndex(0);
        if(r.getPosao()=="UgradnjaVodomjera")
        	comboBoxTipPosla.setSelectedIndex(1);
        if(r.getPosao()=="ZamjenaVodomjera")
        	comboBoxTipPosla.setSelectedIndex(2);
        if(r.getPosao()=="ZamjenaCijevi")
        	comboBoxTipPosla.setSelectedIndex(3);
        if(r.getPosao()=="Ostalo")
        	comboBoxTipPosla.setSelectedIndex(4);
        contentPanel.add(comboBoxTipPosla);
        
     // Datepicker
	    UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
	    final JDatePickerImpl datePickerPlaniraniDatumIzvrsenja = new JDatePickerImpl(datePanel);
	    datePickerPlaniraniDatumIzvrsenja.getJFormattedTextField().setBackground(Color.WHITE);
	    datePickerPlaniraniDatumIzvrsenja.setLocation(166, 285);
	    datePickerPlaniraniDatumIzvrsenja.setSize(200, 20);
	    model.setValue(r.getPlaniraniDatumIzvrsenja());
	    model.setSelected(true);
	    contentPanel.add(datePickerPlaniraniDatumIzvrsenja);
	    
	    
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
        lblStatus.setBounds(110, 45, 46, 14);
        contentPanel.add(lblStatus);
        
        JLabel lblPosao = new JLabel("Posao:");
        lblPosao.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPosao.setBounds(110, 76, 46, 14);
        contentPanel.add(lblPosao);
        
        JLabel lblPlaniraniDatumIzvrenja = new JLabel("Planirani datum izvršenja:");
        lblPlaniraniDatumIzvrenja.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPlaniraniDatumIzvrenja.setBounds(10, 285, 146, 14);
        contentPanel.add(lblPlaniraniDatumIzvrenja);
        
        JLabel label = new JLabel("ID izvršioca radnog naloga:");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBounds(2, 350, 154, 14);
        contentPanel.add(label);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(166, 192, 200, 82);
        textField_2.setText(r.getPotrebniMaterijal());
        contentPanel.add(textField_2);
        
        JLabel label_1 = new JLabel("Potrebni materijal:");
        label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_1.setBounds(34, 193, 122, 14);
        contentPanel.add(label_1);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(166, 378, 200, 20);
        textField_3.setText(r.getLokacija());
        contentPanel.add(textField_3);
        
        JLabel label_2 = new JLabel("Lokacija:");
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_2.setBounds(73, 381, 83, 14);
        contentPanel.add(label_2);
        
     // Datepicker
	    UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
	    final JDatePickerImpl datePickerDatumIzvrsenja = new JDatePickerImpl(datePanel1);
	    datePickerDatumIzvrsenja.getJFormattedTextField().setBackground(Color.WHITE);
	    datePickerDatumIzvrsenja.setLocation(166, 316);
	    datePickerDatumIzvrsenja.setSize(200, 20);
	    model.setValue(r.getDatumIzvrsenja());
	    model.setSelected(true);
	    contentPanel.add(datePickerDatumIzvrsenja);
	    
	    JLabel lblDatumIzvrenja = new JLabel("Datum izvršenja:");
	    lblDatumIzvrenja.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblDatumIzvrenja.setBounds(59, 322, 97, 14);
	    contentPanel.add(lblDatumIzvrenja);
	    
	    JLabel label_3 = new JLabel("Utrošeno vrijeme:");
	    label_3.setHorizontalAlignment(SwingConstants.RIGHT);
	    label_3.setBounds(33, 414, 125, 14);
	    contentPanel.add(label_3);
	    
	    Time t=r.getUtrosenoVrijeme();
	    
	    final JSpinner spinner = new JSpinner();
	    spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
	    spinner.setBounds(168, 411, 46, 20);
	    if (t!=null)
	    	spinner.setValue(t.getHours());
	    else
	    	spinner.setValue(0);
	    contentPanel.add(spinner);
	    
	    JLabel label_4 = new JLabel("Sati");
	    label_4.setBounds(224, 414, 40, 14);
	    contentPanel.add(label_4);
	    
	    final JSpinner spinner_1 = new JSpinner();
	    spinner_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
	    spinner_1.setBounds(264, 411, 46, 20);
	    if (t!=null)
	    	spinner_1.setValue(t.getMinutes());
	    else
	    	spinner_1.setValue(0);
	    contentPanel.add(spinner_1);
	    
	    JLabel label_5 = new JLabel("Minuta");
	    label_5.setBounds(320, 414, 46, 14);
	    contentPanel.add(label_5);
	    
	    textField_4 = new JTextField();
	    textField_4.setColumns(10);
	    textField_4.setBounds(166, 101, 200, 82);
	    textField_4.setText(r.getOpisPosla());
	    contentPanel.add(textField_4);
	    
	    JLabel label_6 = new JLabel("Opis posla:");
	    label_6.setHorizontalAlignment(SwingConstants.RIGHT);
	    label_6.setBounds(63, 104, 93, 14);
	    contentPanel.add(label_6);
	    
	    JLabel label_7 = new JLabel("ID osobe koja stornira:");
	    label_7.setHorizontalAlignment(SwingConstants.RIGHT);
	    label_7.setBounds(381, 17, 132, 14);
	    contentPanel.add(label_7);
	    
	    JLabel label_8 = new JLabel("Razlog storniranja:");
	    label_8.setHorizontalAlignment(SwingConstants.RIGHT);
	    label_8.setBounds(391, 47, 122, 14);
	    contentPanel.add(label_8);
	    
	    textField_6 = new JTextField();
	    textField_6.setColumns(10);
	    textField_6.setBounds(519, 42, 201, 82);
	    textField_6.setText(r.getRazlogStorniranja());
	    contentPanel.add(textField_6);
	    
	    JLabel label_10 = new JLabel("Razlog modifikovanja:");
	    label_10.setHorizontalAlignment(SwingConstants.RIGHT);
	    label_10.setBounds(381, 170, 132, 14);
	    contentPanel.add(label_10);
	    
	    textField_8 = new JTextField();
	    textField_8.setColumns(10);
	    textField_8.setBounds(519, 164, 201, 82);
	    textField_8.setText(r.getRazlogModifikovanja());
	    contentPanel.add(textField_8);
	    
	    JLabel label_11 = new JLabel("ID osobe koja zaključuje:");
	    label_11.setHorizontalAlignment(SwingConstants.RIGHT);
	    label_11.setBounds(359, 135, 154, 14);
	    contentPanel.add(label_11);
	    
	    JLabel label_12 = new JLabel("Dodatni komentar:");
	    label_12.setHorizontalAlignment(SwingConstants.RIGHT);
	    label_12.setBounds(391, 262, 122, 14);
	    contentPanel.add(label_12);
	    
	    textField_10 = new JTextField();
	    textField_10.setColumns(10);
	    textField_10.setText(r.getDodatniKomentar());
	    textField_10.setBounds(519, 257, 201, 82);
	    contentPanel.add(textField_10);
	    
	    textField_1 = new JTextField();
	    textField_1.setBounds(166, 347, 200, 20);
	    textField_1.setText(Long.toString(r.getIzvrsilacPosla()));
	    contentPanel.add(textField_1);
	    textField_1.setColumns(10);
	    
	    textField_5 = new JTextField();
	    textField_5.setBounds(519, 14, 201, 20);
	    textField_5.setText(Long.toString(r.getOsobaKojaStornira()));
	    contentPanel.add(textField_5);
	    textField_5.setColumns(10);
	    
	    textField_9 = new JTextField();
	    textField_9.setBounds(519, 132, 201, 20);
	    textField_9.setText(Long.toString(r.getOsobaKojaZakljucuje()));
	    contentPanel.add(textField_9);
	    textField_9.setColumns(10);
	    
	    java.util.List<Zaposlenik> listaZaposlenika = HibernateZaposlenik.dajSveZaposlenike();
        Zaposlenik[] zaposlenici = listaZaposlenika.toArray(new Zaposlenik[listaZaposlenika.size()]);
	    
	   
	    
	    contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, comboBoxStatusNaloga, comboBoxTipPosla, textField_4, textField_2, datePickerPlaniraniDatumIzvrsenja, datePickerDatumIzvrsenja, datePickerPlaniraniDatumIzvrsenja.getJFormattedTextField(), lblNewLabel, datePickerDatumIzvrsenja.getJFormattedTextField(), lblStatus, lblPosao, textField_3, spinner, spinner_1, textField_6, textField_8, textField_10, lblPlaniraniDatumIzvrenja, label, label_1, label_2, lblDatumIzvrenja, label_3, label_4, label_5, label_6, label_7, label_8, label_10, label_11, label_12}));
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				modifikujButton = new JButton("Modifikuj");
				modifikujButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						java.util.List<Zaposlenik> listaZaposlenika = HibernateZaposlenik.dajSveZaposlenike();
				        Zaposlenik[] zaposlenici = listaZaposlenika.toArray(new Zaposlenik[listaZaposlenika.size()]);
						if(comboBoxStatusNaloga.getSelectedIndex()==0)
							r.postaviStatus(StatusRadnogNaloga.kreiran);
						if(comboBoxStatusNaloga.getSelectedIndex()==1)
							r.postaviStatus(StatusRadnogNaloga.zakljucen);
						if(comboBoxStatusNaloga.getSelectedIndex()==2)
							r.postaviStatus(StatusRadnogNaloga.nezakljucen);
						if(comboBoxStatusNaloga.getSelectedIndex()==3)
							r.postaviStatus(StatusRadnogNaloga.storniran);
						if(comboBoxTipPosla.getSelectedIndex()==0)
							r.postaviPosao(TipPosla.WomaMasina);
						if(comboBoxTipPosla.getSelectedIndex()==1)
							r.postaviPosao(TipPosla.UgradnjaVodomjera);
						if(comboBoxTipPosla.getSelectedIndex()==2)
							r.postaviPosao(TipPosla.ZamjenaVodomjera);
						if(comboBoxTipPosla.getSelectedIndex()==3)
							r.postaviPosao(TipPosla.ZamjenaCijevi);
						if(comboBoxTipPosla.getSelectedIndex()==4)
							r.postaviPosao(TipPosla.Ostalo);
						Date d = (Date) datePickerPlaniraniDatumIzvrsenja.getModel().getValue();
						Date d1 = (Date) datePickerDatumIzvrsenja.getModel().getValue();
						int temp4=1;
						Date temp =r.getDatumKreiranja();
						r.setPlaniraniDatumIzvrsenja(d);
						Date tempDatum=r.getDatumIzvrsenja();
						if(tempDatum==null && d1==null)
							temp4=1;
						else if(tempDatum==null && d1!=null){
							try {
								d1.setHours(temp.getHours());
								d1.setMinutes(temp.getMinutes());
								d1.setSeconds(temp.getSeconds()+1);
								r.setDatumIzvrsenja(d1);
								
							} catch (Exception e) {
								JOptionPane.showMessageDialog(contentPanel, "Datuma izvršenja ne može biti u budućnosti");		
								temp4=0;
							}
						}
						else if(tempDatum!=null && d1==null){
							try {
								r.setDatumIzvrsenja(null);
								
							} catch (Exception e) {
								JOptionPane.showMessageDialog(contentPanel, "Datuma izvršenja ne može biti u budućnosti");		
								temp4=0;
							}
						}
						
						else if(tempDatum.getYear()!=d1.getYear() || tempDatum.getMonth()!=d1.getMonth() || tempDatum.getDay()!=d1.getDay()){
							try {
								d1.setHours(temp.getHours());
								d1.setMinutes(temp.getMinutes());
								d1.setSeconds(temp.getSeconds());
								r.setDatumIzvrsenja(d1);
								
							} catch (Exception e) {
								JOptionPane.showMessageDialog(contentPanel, "Datuma izvršenja ne može biti u budućnosti");		
								temp4=0;
							}
						}
						int temp1=0;
						for(Zaposlenik z:zaposlenici){
							if(z.getId()==Long.parseLong(textField_1.getText())){
								r.setIzvrsilacPosla(Long.parseLong(textField_1.getText()));
								temp1=1;
							}
						}
						if(Long.parseLong(textField_1.getText())==-1){
							r.setIzvrsilacPosla(Long.parseLong(textField_1.getText()));
							temp1=1;
						}
						if(temp1==0)
							JOptionPane.showMessageDialog(contentPanel, "Unijeli ste nepostojeći ID zaposlenika za izvršioca radnog naloga");
						Time t;
						r.setPotrebniMaterijal(textField_2.getText());
						r.setLokacija(textField_3.getText());
						if((Integer)spinner.getValue()==0 && (Integer)spinner_1.getValue()==0)
							t=null;
						else
							t = new Time((Integer)spinner.getValue(),(Integer)spinner_1.getValue(), 0);
						r.setUtrosenoVrijeme(t);
						r.setOpisPosla(textField_4.getText());
						int temp2=0;
						for(Zaposlenik z:zaposlenici){
							if(z.getId()==Long.parseLong(textField_5.getText())){
								r.setOsobaKojaStornira(Long.parseLong(textField_5.getText()));
								temp2=1;
							}
						}
						if(Long.parseLong(textField_5.getText())==-1){
							r.setOsobaKojaStornira(Long.parseLong(textField_5.getText()));
							temp2=1;
						}
						if(temp2==0)
							JOptionPane.showMessageDialog(contentPanel, "Unijeli ste nepostojeći ID zaposlenika koji stornira radni nalog");
						r.setRazlogStorniranja(textField_6.getText());
						r.setOsobaKojaModifikuje(korisnik.getId());
						r.setDatumModifikovanja(new Date());
						int temp3=0;
						for(Zaposlenik z:zaposlenici){
							if(z.getId()==Long.parseLong(textField_9.getText())){
								r.setOsobaKojaModifikuje(Long.parseLong(textField_9.getText()));
								temp3=1;
							}
						}
						if(Long.parseLong(textField_9.getText())==-1){
							r.setOsobaKojaModifikuje(Long.parseLong(textField_9.getText()));
							temp3=1;
						}
						if(temp3==0)
							JOptionPane.showMessageDialog(contentPanel, "Unijeli ste nepostojeći ID zaposlenika koji zaključuje radni nalog");
						r.setDodatniKomentar(textField_10.getText());
						if(textField_8.getText()=="" || textField_8.getText()==null)
							JOptionPane.showMessageDialog(contentPanel, "Niste unijeli razlog modifikovanja");
						else if(temp1==1 && temp2==1 && temp3==1 && temp4==1){
							r.setRazlogModifikovanja(textField_8.getText());
							HibernateRadniNalog.modifikujRadniNalog(r);
							JOptionPane.showMessageDialog(contentPanel, "Uspješno ste modifikovali radni nalog");
							ovaj.dispose();
						}
					}
				});
				buttonPane.add(modifikujButton);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, comboBoxStatusNaloga, comboBoxTipPosla, textField_4, textField_2, datePickerPlaniraniDatumIzvrsenja.getJFormattedTextField(), datePickerDatumIzvrsenja.getJFormattedTextField(), getContentPane(), contentPanel, lblNewLabel, lblStatus, lblPosao, datePickerPlaniraniDatumIzvrsenja, lblPlaniraniDatumIzvrenja, datePickerDatumIzvrsenja, label, textField_3, spinner, spinner_1, textField_6, textField_8, textField_10, modifikujButton, label_1, label_2, lblDatumIzvrenja, label_3, label_4, label_5, label_6, label_7, label_8, label_10, label_11, label_12, buttonPane}));
	}
}
