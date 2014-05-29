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
}