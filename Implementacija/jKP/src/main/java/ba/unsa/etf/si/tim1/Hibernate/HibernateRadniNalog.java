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
	
	
	
}
