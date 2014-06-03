package ba.unsa.etf.si.tim1.GUI;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;

import javax.swing.BorderFactory;
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

import org.jdesktop.swingx.JXDatePicker;

import ba.unsa.etf.si.tim1.Entiteti.*;
import ba.unsa.etf.si.tim1.Hibernate.HibernateRadniNalog;
import ba.unsa.etf.si.tim1.Hibernate.HibernateZaposlenik;
import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import java.util.Calendar;

import javax.swing.SpringLayout;

public class RadniNalozi extends JTabbedPane {

	private final KreiranjeRadnogNaloga panelKreiranjeNaloga;
	private final PretragaRadnihNaloga panelPretraga;
	
	private GlavniProzor glavni;
	
	public RadniNalozi() {

		this.setBounds(280, 50, 800, 550);
        
        JScrollPane scrollPaneKreiranjeNaloga = new JScrollPane();
        scrollPaneKreiranjeNaloga.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneKreiranjeNaloga.getVerticalScrollBar().setUnitIncrement(20);
        

        this.addTab("Kreiranje", null, scrollPaneKreiranjeNaloga, null);
        
        panelKreiranjeNaloga = new KreiranjeRadnogNaloga();
        scrollPaneKreiranjeNaloga.setViewportView(panelKreiranjeNaloga);

        panelPretraga = new PretragaRadnihNaloga();
        this.addTab("Pretraga", null, panelPretraga, null);
        panelPretraga.setGlavni(glavni);
        
	}
	
	public GlavniProzor getGlavni() {
		return glavni;
	}

	public void setGlavni(GlavniProzor glavni) {
		this.glavni = glavni;
	}
}


