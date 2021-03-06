package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.util.HibernateUtil;
import ba.unsa.etf.si.tim1.Entiteti.PristupniPodaci;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernatePristupniPodaci {
	public HibernatePristupniPodaci() {
	}

	public static String HesirajMD5(String message) {
		String digest = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8")); // converting
																// byte array to
																// Hexadecimal
																// String

			StringBuilder sb = new StringBuilder(2 * hash.length);

			for (byte b : hash)
				sb.append(String.format("%02x", b & 0xff));

			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
			// Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
			// null, ex);
		} catch (NoSuchAlgorithmException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
			// Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
			// null, ex);
		}

		return digest;
	}

	public static PristupniPodaci dajPristupnePodatke(long id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		PristupniPodaci podaci = (PristupniPodaci) s.get(PristupniPodaci.class,
				id);
		s.close();

		return podaci;
	}

	public static boolean postojiKorisnik(String username) {

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		Query query = s
				.createQuery("from PristupniPodaci where KORISNICKOIME = :uName");
		query.setParameter("uName", username);

		if (query.list().isEmpty()) {
			s.close();
			return false;
		}
		s.close();
		return true;
	}

	public static long provjeriPodatke(String username, String password)
			throws Exception {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		Query query = s
				.createQuery("FROM PristupniPodaci WHERE korisnickoIme = :uName AND lozinka = :pass");
		query.setParameter("uName", username);
		query.setParameter("pass", HesirajMD5(password));

		if (query.list().isEmpty()) {
			s.close();
			throw new Exception("Pristupni podaci nisu tačni");
		}

		return ((PristupniPodaci) query.list().get(0)).getId();
	}

	public static long spremiPodatke(String username, String password)
			throws Exception {
		String uPass = HesirajMD5(password);
		PristupniPodaci p = new PristupniPodaci(username, uPass);
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		long id = (Long) s.save(p);
		t.commit();
		return id;
	}

	public static String dajKorisnickoImePoKriteriju(long id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Query query = s.createQuery("FROM PristupniPodaci WHERE ID = :id");
		query.setParameter("id", id);
		if (query.list().isEmpty())
			return "";
		return ((PristupniPodaci) query.list().get(0)).getKorisnickoIme();
	}

	public static void urediPristupnePodatke(PristupniPodaci p) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		s.update(p);
		t.commit();
		s.close();
	}

	public static long dajBrojKorisnika() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Query query = s.createSQLQuery("SELECT Count(*) FROM PRISTUPNIPODACI");
		int brojKorisnika = ((java.math.BigInteger) query.list().get(0))
				.intValue();
		s.close();
		return brojKorisnika;
	}

	public static void izbrisiPristupnePodatke(long podaci) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			PristupniPodaci PP = (PristupniPodaci) session.get(
					PristupniPodaci.class, podaci);
			session.delete(PP);
			session.getTransaction().commit();

		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

}
