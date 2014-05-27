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


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        final PagePanel prikazPdf = new PagePanel();
      //prikazPdf.showPage(page);   
        final JScrollPane a = new JScrollPane(prikazPdf);
        a.setBounds(20, 130, 950, 420);
        a.setVisible(true);
        this.add(a);
        
        try {
			datePicker.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if(comboBox.getSelectedIndex() == 1) {
                		MjesecniSumarni(datePicker);
                	}
                	File file = new File("izvjestaj.pdf");
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
	        	        prikazPdf.useZoomTool(true);
	        	        prikazPdf.showPage(page);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
	public static void drawTable(PDPage page, PDPageContentStream contentStream, 
            float y, float margin, String[][] content) throws IOException {
			final int rows = content.length;
			final int cols = content[0].length;
			final float rowHeight = 20f;
			final float tableWidth = page.findMediaBox().getWidth() - margin - margin;
			final float tableHeight = rowHeight * rows;
			final float colWidth = tableWidth/(float)cols;
			final float cellMargin=5f;
			
			//draw the rows
			float nexty = y ;
			for (int i = 0; i <= rows; i++) {
				contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
				nexty-= rowHeight;
			}
			
			//draw the columns
			float nextx = margin;
			for (int i = 0; i <= cols; i++) {
				contentStream.drawLine(nextx, y, nextx, y-tableHeight);
				nextx += colWidth;
			}
			
			//now add the text        
			contentStream.setFont( PDType1Font.TIMES_ROMAN , 12 );    
			
			float textx = margin+cellMargin;
			float texty = y-15;        
			for(int i = 0; i < content.length; i++){
				for(int j = 0 ; j < content[i].length; j++){
					String text = content[i][j];
					contentStream.beginText();
					contentStream.moveTextPositionByAmount(textx,texty);
					contentStream.drawString(text);
					contentStream.endText();
					textx += colWidth;
				}
				texty-=rowHeight;
				textx = margin+cellMargin;
			}
			}
	void MjesecniSumarni(JDatePickerImpl datePicker) {
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();                		
		int neizvrseni = Integer.parseInt(((session.createSQLQuery("select count(*) from (select * from RADNINALOG where VRIJEMERADNOGNALOGA > '2014-05-17' and VRIJEMERADNOGNALOGA < '" +  dt1.format((Date) datePicker.getModel().getValue()) + "') a where DATUMIZVRSENJA IS NULL")).list().toArray()[0]).toString());
		int izvrseni = Integer.parseInt(((session.createSQLQuery("select count(*) from (select * from RADNINALOG where VRIJEMERADNOGNALOGA > '2014-05-17' and VRIJEMERADNOGNALOGA < '" +  dt1.format((Date) datePicker.getModel().getValue()) + "') a where DATUMIZVRSENJA IS NOT NULL")).list().toArray()[0]).toString());
		int stornirani = Integer.parseInt(((session.createSQLQuery("select count(*) from (select * from RADNINALOG where VRIJEMERADNOGNALOGA > '2014-05-17' and VRIJEMERADNOGNALOGA < '" +  dt1.format((Date) datePicker.getModel().getValue()) + "') a where OSOBAKOJASTORNIRA <> -1")).list().toArray()[0]).toString());
		JOptionPane.showMessageDialog(getRootPane(), neizvrseni);
		PDDocument doc;
		try {
			doc = new PDDocument();
			PDPage page = new PDPage();
    		doc.addPage( page );

    		PDPageContentStream contentStream = new PDPageContentStream(doc, page);

    		String[][] content = {{"Neizvrseni",Integer.toString(neizvrseni)}, 
    		                      {"Izvrseni",Integer.toString(izvrseni)}, 
    		                      {"Stornirani",Integer.toString(stornirani)}, 
    		                      {"Ukupno",Integer.toString(neizvrseni + izvrseni + stornirani)}};

    		
    		contentStream.beginText();
    		contentStream.moveTextPositionByAmount( 110, 700 );
    		contentStream.setFont( PDType1Font.HELVETICA_BOLD , 14 );  
    		contentStream.drawString("Kreirani, zakljuceni, nezakljuceni i stornirani radni nalozi");
    		contentStream.endText();
    		/*PDAnnotationLink txtLink = new PDAnnotationLink();
    		PDActionURI action = new PDActionURI();
    		action.setURI("http://www.pdfbox.org");
    		txtLink.set
    		page.getAnnotations().add(txtLink);
    		txtLink.setAction(action);*/
    		drawTable(page, contentStream, 650, 100, content);
    		contentStream.close();
    		doc.save("izvjestaj.pdf" );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (COSVisitorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

