package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.jKP.HibernateUtil;
import ba.unsa.etf.si.tim1.jKP.PristupniPodaci;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class HibernatePristupniPodaci {
	public HibernatePristupniPodaci() {}
	
	private static String HesirajMD5(String message){ 
		String digest = null; 
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8")); //converting byte array to Hexadecimal String
			
			StringBuilder sb = new StringBuilder(2*hash.length);
			
			for(byte b : hash)
				sb.append(String.format("%02x", b&0xff));
			
			digest = sb.toString();
		}
		catch (UnsupportedEncodingException ex) { 
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
			//Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (NoSuchAlgorithmException ex) { 
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
			//Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
		} 
		
		return digest; 
	}
	
	public static boolean postojiKorisnik(String username) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();		
		s.beginTransaction();
		
		Query query = s.createQuery("from PristupniPodaci where korisnickoIme = :uName");
		query.setParameter("uName", username);
		
		if(query.list().isEmpty()) {
			s.close();
			return false;
		}
		s.close();
		return true;
	}
	
	public static long provjeriPodatke(String username, String password) throws Exception {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		Query query = s.createQuery("FROM PristupniPodaci WHERE korisnickoIme = :uName AND lozinka = :pass");
		query.setParameter("uName", username);
		query.setParameter("pass", HesirajMD5(password));
		
		if (query.list().isEmpty()) {
			s.close();
			throw new Exception("Pristupni podaci nisu tačni");
		}
		
		return ((PristupniPodaci)query.list().get(0)).getId();
	}
}