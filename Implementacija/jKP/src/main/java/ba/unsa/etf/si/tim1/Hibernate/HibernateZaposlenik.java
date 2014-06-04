package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.util.HibernateUtil;
import ba.unsa.etf.si.tim1.Entiteti.PristupniPodaci;
import ba.unsa.etf.si.tim1.Entiteti.Zaposlenik;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateZaposlenik {
	public HibernateZaposlenik() {}
	
	public static Zaposlenik dajZaposlenikaPoId(long id){
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query query = s.createQuery("FROM Zaposlenik WHERE id = :id");
		query.setParameter("id", id);
		
		if(query.list().isEmpty()) {
			s.close();
			return null;
		}
		
		Zaposlenik z = (Zaposlenik)query.list().get(0);

		s.close();
		return z;
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
	
	public static PristupniPodaci dajPristupnePodatkePoId(Zaposlenik z) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query query = s.createQuery("FROM PristupniPodaci WHERE id = :id");
		query.setParameter("id",z.getPristupniPodaci());
		
		if(query.list().isEmpty()) {
			s.close();
			return null;
		}
		
		PristupniPodaci p = (PristupniPodaci)query.list().get(0);
		s.close();
		return p;
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
	
	public static long pohraniZaposlenika(Zaposlenik z, long podaci) {
		z.setPristupniPodaci(podaci);
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		long id = (Long)s.save(z);
		t.commit();
		s.close();
		return id;
	}
	
	public static List<Zaposlenik> dajZaposlenikePoKriteriju(String kriterij) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		long id = -1;
		try {
			id = Long.parseLong(kriterij);
		}
		catch(Exception ex) {
			
		}
		
		Query query = s.createQuery("FROM Zaposlenik WHERE id=:id OR ime LIKE :kriterij OR prezime LIKE :kriterij");
		query.setParameter("kriterij", "%"+kriterij+"%");
		query.setParameter("id", id);
		
		List<Zaposlenik> lista = (List<Zaposlenik>)query.list();
		s.close();
		
		return lista;
	}
	
	public static void urediZaposlenika(Zaposlenik z) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		s.update(z);
		t.commit();
		s.close();
	}
	
	public static int dajBrojZaposlenika() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Query query = s.createSQLQuery("SELECT Count(*) FROM zaposlenik");
		int brojKorisnika = ((java.math.BigInteger)query.list().get(0)).intValue();
		s.close();
		return brojKorisnika;
	}
	
	public static void izbrisiZaposlenika(Zaposlenik z) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		
		s.delete(z);
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
					st.execute("INSERT INTO zaposlenik VALUES (NULL, 'Administrator', 'Administrator', 'privilegirani', '1');");
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
		if (dajBrojZaposlenika() == 0)
			inicijalizirajTabelu();
		dajSveZaposlenike();
	}*/
}

