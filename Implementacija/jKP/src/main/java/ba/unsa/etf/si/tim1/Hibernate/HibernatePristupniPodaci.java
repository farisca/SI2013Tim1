package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.jKP.HibernateUtil;
import ba.unsa.etf.si.tim1.jKP.Zaposlenik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class HibernatePristupniPodaci {
	public HibernatePristupniPodaci() {}
	
	public static boolean postojiKorisnik(String username) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();		
		Transaction t = s.beginTransaction();
		
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
		Transaction t = s.beginTransaction();
		
		Query query = s.createQuery("FROM PristupniPodaci WHERE korisnickoIme = :uName AND lozinka = :pass");
		query.setParameter("uName", username);
		query.setParameter("pass", password);
		
		if (query.list().isEmpty()) {
			s.close();
			throw new Exception("Pristupni podaci nisu tačni");
		}
		
		return -1;
	}
}
