package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.util.HibernateUtil;
import ba.unsa.etf.si.tim1.Entiteti.RadniNalog;

import java.util.List;

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
	
	public static List<RadniNalog> pretragaPoListiKriterija(List<String> lista) {
		if (lista.isEmpty()) return null;
		
		String upit = "SELECT * FROM RADNINALOG WHERE ";
		int brojac = 1;
		for(int i = 0; i < lista.size(); i += 2) {
			if (lista.get(i).equals("DATUMKREIRANJA"))
				upit += "DATE_FORMAT(DATUMKREIRANJA, '%Y-%m-%d')";
			else
				upit += lista.get(i);
			upit += " = :unos" + brojac;
			if (i != lista.size() - 2) upit += " AND ";
			brojac++;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction t = null;
		try {
			t = session.beginTransaction();
		    
		    SQLQuery query = session.createSQLQuery(upit);
		    query.addEntity(RadniNalog.class);
		    
		    int br1 = 1 ;
		    for (int i = 1; i <= lista.size() / 2; i++) {
		    	String param = "unos" + i;
		    	query.setParameter(param, lista.get(br1));	        	 
		    	br1 = br1 + 2;
		    }
			List<RadniNalog> result = (List<RadniNalog>)query.list();
			
			t.commit();
			session.close();
			return result;
		}
		catch (HibernateException e) {
			if (t != null) t.rollback();
		    e.printStackTrace();
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
		Query query = s.createSQLQuery("SELECT Count(*) FROM RadniNalog");
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

}
