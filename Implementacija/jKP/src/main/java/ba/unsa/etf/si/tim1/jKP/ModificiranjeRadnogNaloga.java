package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificiranjeRadnogNaloga extends JFrame{
	private JPanel kontejner;
	private JTextField txtAminaCelik;
	private JTextField txtZmajaOdBosne;
	private JTextField textField_2;
	
	private Zaposlenik korisnik;
	private RadniNalog nalog;
	
	public ModificiranjeRadnogNaloga(Zaposlenik kor, RadniNalog r){
		
		korisnik=kor;
		nalog=r;
		
		kontejner = new JPanel();
		kontejner.setBackground(Color.WHITE);
		kontejner.setLayout(null);
		getContentPane().add(kontejner);
		
		JLabel lblIzvrilac = new JLabel("Izvr\u0161ilac:");
		lblIzvrilac.setBounds(30, 75, 154, 14);
		kontejner.add(lblIzvrilac);
		
		JLabel lblLokacija = new JLabel("Lokacija:");
		lblLokacija.setBounds(30, 100, 154, 14);
		kontejner.add(lblLokacija);
		
		JLabel lblPlaniraniDatumIzvrenja = new JLabel("Planirani datum izvr\u0161enja:");
		lblPlaniraniDatumIzvrenja.setBounds(30, 127, 154, 14);
		kontejner.add(lblPlaniraniDatumIzvrenja);
		
		JLabel lblBrojRadnogNaloga = new JLabel("Broj radnog naloga:");
		lblBrojRadnogNaloga.setBounds(30, 22, 219, 14);
		kontejner.add(lblBrojRadnogNaloga);
		
		JLabel lblTipPoslaIli = new JLabel("Tip posla ili usluge:");
		lblTipPoslaIli.setBounds(30, 152, 154, 14);
		kontejner.add(lblTipPoslaIli);
		
		JLabel lblDetaljnijiOpisPosla = new JLabel("Detaljniji opis posla:");
		lblDetaljnijiOpisPosla.setBounds(30, 177, 154, 14);
		kontejner.add(lblDetaljnijiOpisPosla);
		
		JLabel lblUtroeniMaterijal = new JLabel("Utro\u0161eni materijal:");
		lblUtroeniMaterijal.setBounds(30, 277, 154, 14);
		kontejner.add(lblUtroeniMaterijal);
		
		txtAminaCelik = new JTextField();
		txtAminaCelik.setText("Amina Celik");
		txtAminaCelik.setBounds(194, 72, 200, 20);
		kontejner.add(txtAminaCelik);
		txtAminaCelik.setColumns(10);
		
		txtZmajaOdBosne = new JTextField();
		txtZmajaOdBosne.setText("Zmaja od Bosne bb");
		txtZmajaOdBosne.setBounds(194, 97, 200, 20);
		kontejner.add(txtZmajaOdBosne);
		txtZmajaOdBosne.setColumns(10);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
	    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	    datePicker.getJFormattedTextField().setBackground(Color.WHITE);
	    datePicker.getJFormattedTextField().setText("05.05.2014.");
	    datePicker.setLocation(194, 123);
	    datePicker.setSize(200, 20);
	    kontejner.add(datePicker);
        
        textField_2 = new JTextField();
        textField_2.setEnabled(false);
        textField_2.setText("1");
        textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
        textField_2.setBounds(194, 19, 200, 20);
        kontejner.add(textField_2);
        textField_2.setColumns(10);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(194, 152, 200, 20);
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Izlazak ma\u0161ine Woma na teren", "Ugradnja vodomjera", "Zamjena vodomjera", "Zamjena cijevi", "Ostalo"}));
        kontejner.add(comboBox);
        
       //  text area 1
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(30, 191, 364, 64);
        kontejner.add(scrollPane);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        scrollPane.setViewportView(textArea);
        
        // text area 2
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_1.setBounds(30, 290, 364, 64);
        kontejner.add(scrollPane_1);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setLineWrap(true);
        scrollPane_1.setViewportView(textArea_1);
        
        JButton btnModifikuj = new JButton("Modifikuj");
        btnModifikuj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(kontejner, 
						"Radni nalog je uspje�no modifikovan.", 
						"Potvrda", 
						JOptionPane.INFORMATION_MESSAGE);
			dispose();
        	}
        });
        btnModifikuj.setBounds(305, 365, 89, 23);
        kontejner.add(btnModifikuj);
        
     
        //
		
		this.setTitle("Modificiranje radnog naloga");
		this.setVisible(true);
		this.setBounds(500, 150, 461, 471);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
