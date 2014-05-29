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
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		Transaction t = session.beginTransaction();
		
		Query query = session.createQuery("from PristupniPodaci where korisnickoIme = :uName");
		query.setParameter("uName", username);
		
		if(query.list().size() == 0) {
			session.close();
			return false;
		}		
		session.close();
		return true;
	}
	
}
