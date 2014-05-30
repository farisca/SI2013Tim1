package ba.unsa.etf.si.tim1.Hibernate;

import ba.unsa.etf.si.tim1.jKP.HibernateUtil;
import ba.unsa.etf.si.tim1.jKP.RadniNalog;

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

	public static List<RadniNalog> pretraga(String kriterij, String unos) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction t = null;
	      try{
	         t = session.beginTransaction();
	         String upit = "select * from radninalog where " + kriterij + "= :unos";
	         SQLQuery query = session.createSQLQuery(upit);
	 		 query.addEntity(RadniNalog.class);
	 		 query.setParameter("unos", unos);
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
}
