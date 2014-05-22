package ba.unsa.etf.si.tim1.jKP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


import java.util.Date;

import javax.swing.*;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.GridLayout;

public class Izvjestaji extends JPanel {
	private JComboBox<String> comboBox;
	public Izvjestaji()  {
		this.setBounds(201, 0, 990, 800);
		this.setBackground(Color.white);
		this.setLayout(null);
		
        JLabel lblTipIzvjetaja = new JLabel("Tip izvještaja:");
        lblTipIzvjetaja.setBounds(39, 28, 87, 15);
        lblTipIzvjetaja.setHorizontalTextPosition(JLabel.RIGHT);
        this.add(lblTipIzvjetaja);
        
        comboBox = new JComboBox<String>();
        comboBox.setBounds(144, 23, 300, 24);
        comboBox.addItem("Sedmični pregled rada zaposlenika");
        comboBox.addItem("Mjesečni izvještaj o radnim nalozima");
        comboBox.addItem("Mjesečni izvještaj o storniranim radnim nalozima");
        comboBox.addItem("Mjesečni izvještaj o lokacijama s više intervencija");
        comboBox.addItem("Godišnji sumarni izvještaj o radnim nalozima");
        comboBox.setSelectedIndex(4);
        this.add(comboBox);

        JLabel lblDoDatuma = new JLabel("Do datuma:");
        lblDoDatuma.setBounds(51, 68, 75, 15);
        this.add(lblDoDatuma);
        
        UtilDateModel model = new UtilDateModel();
                JDatePanelImpl datePanel = new JDatePanelImpl(model);
        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.setLocation(144, 59);
        datePicker.setSize(202, 26);
        this.add(datePicker);
        
        File file = new File("izvjestaj2.pdf");
        RandomAccessFile raf;
        FileChannel channel; 
        ByteBuffer buf;
        PDFFile pdffile;
        final PDFPage page;
        try {
			raf = new RandomAccessFile(file, "r");
			channel = raf.getChannel();
			buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			pdffile = new PDFFile(buf);
			page = pdffile.getPage(1); 
			final PagePanel prikazPdf = new PagePanel();
	        prikazPdf.useZoomTool(true);
	        //prikazPdf.setBounds(10, 149, 1098, 298);
	        
	        //prikazPdf.showPage(page);   
	        JScrollPane a = new JScrollPane(prikazPdf);
	        a.setBounds(20, 130, 950, 420);
	        a.setVisible(true);
	        this.add(a);
			
			
			datePicker.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        prikazPdf.showPage(page);
                        Date dateString = (Date) datePicker.getModel().getValue();
                        JOptionPane.showMessageDialog(getRootPane(), dateString.toString());
                }
			});
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        //donja dugmad
        JButton jSpasiti = new JButton("Spasiti");
        jSpasiti.setBounds(650, 600, 153, 48);
        jSpasiti.setBackground(Color.LIGHT_GRAY);
        this.add(jSpasiti);
        jSpasiti.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(getRootPane(), "Nije implementirano!");
        	}
        });
        
        JButton jStampati = new JButton("Odštampati");
        jStampati.setBounds(810, 600, 153, 48);
        jStampati.setBackground(Color.LIGHT_GRAY);
        this.add(jStampati);
        jStampati.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(getRootPane(), "Nije implementirano!");
        	}
        });
       
	}
	
}
