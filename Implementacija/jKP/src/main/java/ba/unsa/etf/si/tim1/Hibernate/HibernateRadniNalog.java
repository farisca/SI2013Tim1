package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.jKP.HibernateUtil;
import ba.unsa.etf.si.tim1.jKP.PristupniPodaci;
import ba.unsa.etf.si.tim1.jKP.RadniNalog;
import ba.unsa.etf.si.tim1.jKP.Zaposlenik;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateRadniNalog {
	public static void pohraniRadniNalog(RadniNalog nalog) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		
		s.save(nalog);
		
		t.commit();
		s.close();
	}

	public static void modifikujRadniNalog(RadniNalog nalog) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		
		s.update(nalog);
		
		t.commit();
		s.close();
	}
	
	

	public static List<RadniNalog> pretraga(List<String> lista) {
		
		String upit = "select * from radninalog where ";
		int brojac=1;
		for(int i=0; i<lista.size(); i=i+2){
			upit = upit + lista.get(i) + "= :unos"+brojac;
			if(i != lista.size()-2) upit = upit +" and ";
			brojac++;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction t = null;
	      try{
	         t = session.beginTransaction();
	        
	         SQLQuery query = session.createSQLQuery(upit);
	         query.addEntity(RadniNalog.class);
	         
	         
	         int br1 = 1 ;
	         for(int i = 1; i<=lista.size() / 2; i++){
	        	 String param = "unos"+i;
	        	 query.setParameter(param, lista.get(br1));	        	 
	        	 br1=br1+2;
	 		 
	         }
	 		 List result = query.list();
	 		 
	 		 t.commit(); 
	 		 return result;

	      }catch (HibernateException e) {
	         if (t!=null) t.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return null;	
		
	}
	
	public static List<RadniNalog> dajSveRadneNaloge() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query query = s.createQuery("FROM RadniNalog");
		
		if(query.list().isEmpty()) {
			s.close();
			return null;
		}
		
		List<RadniNalog> lista = (List<RadniNalog>)query.list();
		
		s.close();
		return lista;
	}
	
	public static int dajBrojRadnihNaloga() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Query query = s.createSQLQuery("SELECT Count(*) FROM radninalog");
		int brojNaloga = ((java.math.BigInteger)query.list().get(0)).intValue();
		s.close();
		return brojNaloga;
	}
	
	public static void izbrisiRadniNalog(RadniNalog nalog) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		
		s.delete(nalog);
		t.commit();
		
		s.close();
	}
	
	/*private static void inicijalizirajTabelu() {
		String url = "jdbc:mysql://localhost/jkp"; 
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			java.sql.Connection c = java.sql.DriverManager.getConnection(url, "root", ""); 
		 
				try { 
					java.sql.Statement st = c.createStatement(); 
					st.execute("INSERT INTO radninalog (BROJRADNOGNALOGA, STATUS, POSAO, KREATORRADNOGNALOGA, IZVRSILACPOSLA, OSOBAKOJASTORNIRA, OSOBAKOJAMODIFIKUJE, OSOBAKOJAZAKLJUCUJE)  VALUES (NULL, 'zakljucen', 'Ostalo', '1', '1', '1', '1', '1');");
				} 
				catch (Exception e) { 
					System.out.println("Greska pri radu sa bazom: "+e.getMessage()); 
				}
				finally {
					c.close(); 
				}
		 }
		 catch (Exception e) {
			 System.out.println("Greska pri radu sa bazom: "+e.getMessage()); 
		 }
	}
	
	public static void ubijOnogaKoJePravioHibernate() {
		if (dajBrojRadnihNaloga() == 0)
			inicijalizirajTabelu();
		dajSveRadneNaloge();
	}*/
	
	
}
