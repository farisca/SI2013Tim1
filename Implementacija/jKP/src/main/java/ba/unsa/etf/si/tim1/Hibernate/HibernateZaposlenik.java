package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.jKP.HibernateUtil;
import ba.unsa.etf.si.tim1.jKP.PristupniPodaci;
import ba.unsa.etf.si.tim1.jKP.RadniNalog;
import ba.unsa.etf.si.tim1.jKP.Zaposlenik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateZaposlenik {
	public HibernateZaposlenik() {}
	
	public static boolean postojiZaposlenik() {
		
		return true;
	}
	
	public static Zaposlenik dajZaposlenikaPoPristupnimPodacima(long id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query query = s.createQuery("FROM Zaposlenik WHERE pristupnipodaci = :id");
		query.setParameter("id", id);
		
		if(query.list().isEmpty()) {
			s.close();
			return null;
		}
		
		Zaposlenik z = (Zaposlenik)query.list().get(0);
		
		s.close();
		return z;
	}
	
	public static List<Zaposlenik> dajSveZaposlenike() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query query = s.createQuery("FROM Zaposlenik");
		
		if(query.list().isEmpty()) {
			s.close();
			return null;
		}
		
		List<Zaposlenik> lista = (List<Zaposlenik>)query.list();
		
		s.close();
		return lista;
	}
	public static void pohraniZaposlenika(Zaposlenik z, long podaci) {
		z.setPristupniPodaci(podaci);
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		s.save(z);
		t.commit();
		s.close();
	}
	public static List<Zaposlenik> dajZaposlenikePoKriteriju(String kriterij) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query query = s.createQuery("FROM Zaposlenik WHERE ime LIKE :kriterij OR prezime LIKE :kriterij");
		query.setParameter("kriterij", kriterij);
		if(query.list().isEmpty()) {
			s.close();
			return null;
		}
		List<Zaposlenik> lista = (List<Zaposlenik>)query.list();
		return lista;
	}
	public static void urediZaposlenika(Zaposlenik z) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		s.update(z);
		t.commit();
		s.close();
	}
}

