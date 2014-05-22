package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class RadniNalozi extends JTabbedPane {
	
	public RadniNalozi() {
		

        this.setBounds(280, 50, 800, 550);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        // Tab za kreiranje radnih naloga
        this.addTab("Kreiranje", null, scrollPane, null);
        
        final JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(null);
        panel.setPreferredSize(new java.awt.Dimension(500, 750));
        
        JLabel lblKreiranjeRadnogNaloga = new JLabel("Kreiranje radnog naloga");
        lblKreiranjeRadnogNaloga.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblKreiranjeRadnogNaloga.setBounds(39, 29, 229, 20);
        panel.add(lblKreiranjeRadnogNaloga);
        
        JLabel lblDatumKreiranja = new JLabel("Datum kreiranja:");
        lblDatumKreiranja.setBounds(76, 112, 150, 14);
        panel.add(lblDatumKreiranja);
        
        JLabel lblKreirao = new JLabel("Kreirao:");
        lblKreirao.setBounds(76, 137, 150, 14);
        panel.add(lblKreirao);
        
        JLabel lblIzvrilac = new JLabel("Izvr\u0161ilac:");
        lblIzvrilac.setBounds(76, 162, 150, 14);
        panel.add(lblIzvrilac);
        
        JLabel lblLokacija = new JLabel("Lokacija:");
        lblLokacija.setBounds(76, 187, 150, 14);
        panel.add(lblLokacija);
        
        JLabel lblPlaniraniDatumIzvrsenja = new JLabel("Planirani datum izvr\u0161enja:");
        lblPlaniraniDatumIzvrsenja.setBounds(76, 212, 150, 14);
        panel.add(lblPlaniraniDatumIzvrsenja);
        
        JLabel lblDatumZavrsetkaRadova = new JLabel("Datum zavr\u0161etka radova:");
        lblDatumZavrsetkaRadova.setBounds(76, 237, 150, 14);
        panel.add(lblDatumZavrsetkaRadova);
        
        JLabel lblUtrosenoVrijeme = new JLabel("Utro\u0161eno vrijeme:");
        lblUtrosenoVrijeme.setBounds(76, 262, 150, 14);
        panel.add(lblUtrosenoVrijeme);
        
        JLabel lblTipPosla = new JLabel("Tip posla ili usluge:");
        lblTipPosla.setBounds(76, 287, 150, 14);
        panel.add(lblTipPosla);
        
        JLabel lblOpisPosla = new JLabel("Detaljniji opis posla:");
        lblOpisPosla.setBounds(76, 323, 150, 14);
        panel.add(lblOpisPosla);
        
        JLabel lblMaterijal = new JLabel("Materijal koji \u0107e biti utro\u0161en:");
        lblMaterijal.setBounds(76, 499, 200, 14);
        panel.add(lblMaterijal);
        
        JLabel lblStatusRadnogNaloga = new JLabel("Status radnog naloga:");
        lblStatusRadnogNaloga.setBounds(76, 620, 150, 14);
        panel.add(lblStatusRadnogNaloga);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dejan Azinovi\u0107", "Faris \u010Cakari\u0107", "Amina \u010Celik", "Haris Eminagi\u0107", "Dado Bajramovi\u0107", "Alen \u010Camd\u017Ei\u0107", "Ahmed Ali\u0107"}));
        comboBox.setEditable(true);
        comboBox.setBounds(236, 134, 200, 20);
        panel.add(comboBox);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Faris \u010Cakari\u0107", "Dejan Azinovi\u0107", "Amina \u010Celik", "Haris Eminagi\u0107", "Dado Bajramovi\u0107", "Alen \u010Camd\u017Ei\u0107", "Ahmed Ali\u0107"}));
        comboBox_1.setEditable(true);
        comboBox_1.setBounds(236, 159, 200, 20);
        panel.add(comboBox_1);
        
        final JTextField txtZmajaOdBosne = new JTextField();
        txtZmajaOdBosne.setBounds(236, 184, 200, 20);
        panel.add(txtZmajaOdBosne);
        txtZmajaOdBosne.setColumns(10);
        
        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setEditable(false);
        formattedTextField.setEnabled(false);
        formattedTextField.setBounds(236, 256, 200, 20);
        panel.add(formattedTextField);
        
        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Izlazak ma\u0161ine Woma na teren", "Ugradnja vodomjera", "Zamjena vodomjera", "Zamjena cijevi", "Ostalo"}));
        comboBox_2.setBounds(236, 284, 200, 20);
        panel.add(comboBox_2);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_1.setBounds(76, 348, 360, 131);
        panel.add(scrollPane_1);
        
        final JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        scrollPane_1.setViewportView(textArea);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(76, 524, 358, 70);
        panel.add(scrollPane_2);
        
        final JTextArea textArea_1 = new JTextArea();
        scrollPane_2.setViewportView(textArea_1);
        textArea_1.setLineWrap(true);
                
        JComboBox comboBox_3 = new JComboBox();
        comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Kreiran", "Zaklju\u010Den", "Nezaklju\u010Den", "Storniran"}));
        comboBox_3.setBounds(236, 617, 200, 20);
        panel.add(comboBox_3);
        
        // Datepicker
        UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.getJFormattedTextField().setBackground(Color.WHITE);
        datePicker.setLocation(236, 106);
        datePicker.setSize(200, 20);
        panel.add(datePicker);
        
        UtilDateModel model_2 = new UtilDateModel();
		JDatePanelImpl datePanel_2 = new JDatePanelImpl(model_2);
        final JDatePickerImpl datePicker_2 = new JDatePickerImpl(datePanel_2);
        datePicker_2.getJFormattedTextField().setBackground(Color.WHITE);
        datePicker_2.setLocation(236, 206);
        datePicker_2.setSize(200, 20);
        panel.add(datePicker_2);
        
        UtilDateModel model_3 = new UtilDateModel();
		JDatePanelImpl datePanel_3 = new JDatePanelImpl(model_3);
        final JDatePickerImpl datePicker_3 = new JDatePickerImpl(datePanel_3);
        datePicker_3.getJFormattedTextField().setEnabled(false);
        datePicker_3.setLocation(236, 231);
        datePicker_3.setSize(200, 20);
        panel.add(datePicker_3);
        
		
        JButton btnKreiraj = new JButton("Kreiraj");
        btnKreiraj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*JOptionPane.showMessageDialog(panel, 
						"Nije implementirano!", 
						"Potvrda", 
						JOptionPane.INFORMATION_MESSAGE);*/
        		Zaposlenik z = new Zaposlenik("Dejan", "Azinović", TipUposlenika.privilegirani, new PristupniPodaci("admin", "admin"));
        		RadniNalog rn = new RadniNalog(1, new Date(), z, StatusRadnogNaloga.kreiran, TipPosla.WomaMasina, new Date(), "Faris Čakarić", "Materijal", "Grbavica", new Date(), new Time(1000000), true, "testni posao");
        		rn.spasiUBazu();
        		// int BRN,Date VRN,Zaposlenik KRN,StatusRadnogNaloga stat,TipPosla pos,Date PDI,List LI,String PM,String lok,Date DI,Time UV,Boolean odo, String OP
        		dispose();
        	}

			private void dispose() {
				// TODO Auto-generated method stub
				datePicker.getJFormattedTextField().setText("");
		        datePicker_2.getJFormattedTextField().setText("");
		        datePicker_3.getJFormattedTextField().setText("");
		        txtZmajaOdBosne.setText("");
				textArea.setText("");
				textArea_1.setText("");
			}
        });
        btnKreiraj.setBounds(345, 700, 89, 23);
        panel.add(btnKreiraj);

       
        
        // Tab za pretragu
        final JPanel panelPretraga = new JPanel();
        this.addTab("Pretraga", null, panelPretraga, null);
        panelPretraga.setLayout(null);
        
        JLabel lblPretraga = new JLabel("Pretraga radnih naloga");
        lblPretraga.setBounds(39, 29, 198, 20);
        lblPretraga.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelPretraga.add(lblPretraga);
        
        JLabel lblKriterijPretrage = new JLabel("Kriterij pretrage:");
        lblKriterijPretrage.setBounds(76, 112, 150, 14);
        panelPretraga.add(lblKriterijPretrage);
        
        JLabel lblKljunaRije = new JLabel("Klju\u010Dna rije\u010D:");
        lblKljunaRije.setBounds(76, 137, 150, 14);
        panelPretraga.add(lblKljunaRije);
        
        JComboBox comboBox_4 = new JComboBox();
        comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Redni broj", "Kreirao", "Izvr\u0161ilac", "Status", "Lokacija", "Datum kreiranja", "Planirani datum izvr\u0161enja", "Datum zavr\u0161etka radova"}));
        comboBox_4.setBounds(236, 109, 200, 20);
        panelPretraga.add(comboBox_4);
        
        JTextField textField_1 = new JTextField();
        textField_1.setText("1");
        textField_1.setBounds(236, 134, 200, 20);
        panelPretraga.add(textField_1);
        textField_1.setColumns(10);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(76, 213, 638, 178);
        panelPretraga.add(scrollPane_3);
        
        final JTable table = new JTable();
        scrollPane_3.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"1", "Dejan Azinovi\u0107", "Faris \u010Cakari\u0107", "Kreiran", "Zmaja od Bosne bb", "21.04.2014.", "23.04.2014.", "23.04.2014.", "Zamjena cijevi", "5 sati"},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"Redni broj", "Kreirao", "Izvr\u0161ilac", "Status", "Lokacija", "Datum kreiranja", "Planirani datum izvr\u0161enja", "Datum zavr\u0161etka radova", "Tip posla ili usluge", "Utro\u0161eno vrijeme"
        	}
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setMinWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setMinWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setMinWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setMinWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setMinWidth(20);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setMinWidth(20);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setMinWidth(50);
        table.getColumnModel().getColumn(9).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setMinWidth(20);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JLabel lblRezultati = new JLabel("Rezultati:");
        lblRezultati.setBounds(76, 188, 150, 14);
        panelPretraga.add(lblRezultati);
        
        JButton btnDetaljnije = new JButton("Detaljnije");
        btnDetaljnije.setBounds(625, 420, 89, 23);
        btnDetaljnije.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(panelPretraga, "Nije implementirano!");
        	}
        });
        	
        panelPretraga.add(btnDetaljnije);
        
        JButton btnModifikuj = new JButton("Modifikuj");
        btnModifikuj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ModificiranjeRadnogNaloga();
        		table.setValueAt("Amina Celik", 0, 2);
        		table.setValueAt("05.05.2014.", 0, 5);
        		
        	}
        });
        btnModifikuj.setBounds(526, 420, 89, 23);
        panelPretraga.add(btnModifikuj);
        
        JButton btnStorniraj = new JButton("Storniraj");
        btnStorniraj.setBounds(427, 420, 89, 23);
        panelPretraga.add(btnStorniraj);
        btnStorniraj.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e){
        		
        		new StorniranjeRadnogNaloga();
        		table.setValueAt("Storniran", 0, 3);
        	}
        	
        });
        
        JButton btnZakljui = new JButton("Zaklju�i");
        btnZakljui.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ZakljucivanjeRadnogNaloga();
        		table.setValueAt("Zakljucen", 0, 3);
                
        	}
        });
        btnZakljui.setBounds(328, 420, 89, 23);
        panelPretraga.add(btnZakljui);
        
        JButton btnNoviKriterij = new JButton("Novi kriterij");
        btnNoviKriterij.setBounds(604, 108, 110, 23);
        panelPretraga.add(btnNoviKriterij);
        btnNoviKriterij.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(panelPretraga, "Nije implementirano!");
        	}
        });
        
        JButton button = new JButton("Tra\u017Ei");
        button.setBounds(604, 137, 110, 23);
        panelPretraga.add(button);
		
	}
}
