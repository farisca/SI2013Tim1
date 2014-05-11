package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ZakljucivanjeRadnogNaloga extends JFrame {
	private JPanel kontejner;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public ZakljucivanjeRadnogNaloga(){
		kontejner = new JPanel();
		kontejner.setBackground(Color.WHITE);
		kontejner.setLayout(null);
		getContentPane().add(kontejner);
		
		JLabel lblRedniBrojRadnog = new JLabel("Redni broj radnog naloga:");
		lblRedniBrojRadnog.setBounds(32, 26, 175, 14);
		kontejner.add(lblRedniBrojRadnog);
		
		JLabel lblDodatniKomentar = new JLabel("Dodatni komentar (opcionalno):");
		lblDodatniKomentar.setBounds(32, 83, 291, 14);
		kontejner.add(lblDodatniKomentar);
		
		JButton btnZakljui = new JButton("Zaklju\u010Di");
		btnZakljui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(kontejner, 
							"Radni nalog je uspje�no zaklju�en.", 
							"Potvrda", 
							JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		btnZakljui.setBounds(281, 139, 89, 23);
		kontejner.add(btnZakljui);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEnabled(false);
		textField.setBounds(217, 23, 154, 20);
		kontejner.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(32, 108, 339, 20);
		kontejner.add(textField_1);
		textField_1.setColumns(10);
		
		// date picker
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		
		this.setTitle("Zaklju�ivanje radnog naloga");
		this.setVisible(true);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
