package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;




import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StorniranjeRadnogNaloga extends JFrame {
	private JPanel kontejner;
	private JTextField textField;
	private JLabel lblBrojRadnogNaloga;
	private JTextField textField_1;
	private JLabel lblDodatniKomentaropcionalno;
	private JTextField textField_2;
	
	public StorniranjeRadnogNaloga(){
		kontejner = new JPanel();
		kontejner.setBackground(Color.WHITE);
		kontejner.setLayout(null);
		getContentPane().add(kontejner);
		
		JLabel lblRazlogStorniranjaRadnog = new JLabel("Razlog storniranja radnog naloga:");
		lblRazlogStorniranjaRadnog.setBounds(31, 85, 287, 14);
		kontejner.add(lblRazlogStorniranjaRadnog);
		
		JButton btnStorniraj = new JButton("Storniraj");
		btnStorniraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(kontejner, 
						"Radni nalog je storniran.", 
						"Potvrda", 
						JOptionPane.INFORMATION_MESSAGE);
				
				dispose();
			}
		});
		btnStorniraj.setBounds(229, 219, 89, 23);
		kontejner.add(btnStorniraj);
		
		textField = new JTextField();
		textField.setBounds(31, 110, 287, 20);
		textField.setText("Radnik nema pristup.");
		kontejner.add(textField);
		textField.setColumns(10);
		
		lblBrojRadnogNaloga = new JLabel("Broj radnog naloga:");
		lblBrojRadnogNaloga.setBounds(32, 24, 159, 14);
		kontejner.add(lblBrojRadnogNaloga);
		
		textField_1 = new JTextField();
		textField_1.setText("1");
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setEnabled(false);
		textField_1.setBounds(201, 21, 117, 20);
		kontejner.add(textField_1);
		textField_1.setColumns(10);
		
		lblDodatniKomentaropcionalno = new JLabel("Dodatni komentar (opcionalno):");
		lblDodatniKomentaropcionalno.setBounds(31, 141, 287, 14);
		kontejner.add(lblDodatniKomentaropcionalno);
		
		textField_2 = new JTextField();
		textField_2.setBounds(31, 166, 287, 20);
		kontejner.add(textField_2);
		textField_2.setColumns(10);
		
		this.setTitle("Storniranje radnog naloga");
		this.setVisible(true);
		this.setBounds(550, 200, 360, 291);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
