package ba.unsa.etf.si.tim1.GUI;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class IzvjestajiTest extends TestCase {

	public void testSedmicniRadniciLosDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2100);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
			Izvjestaji iz = new Izvjestaji();
			iz.SedmicniRadnici("proba", myDate);
			fail("Nije bacen izuzetak."); 
		} catch (Exception e) { 
			 assertTrue(true); 
		} 
	}
	
	public void testSedmicniRadniciDobarDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2013);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
			Izvjestaji iz = new Izvjestaji();
			iz.SedmicniRadnici("proba", myDate);
			assertTrue(true); 
		} catch (Exception e) { 
			fail(e.getMessage()); 
		} 
	}
	
	public void testGodisnjiSumarniLosDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2100);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
			Izvjestaji iz = new Izvjestaji();
			iz.Godisnji("proba", myDate);
			fail("Nije bacen izuzetak."); 
		} catch (Exception e) { 
			 assertTrue(true); 
		} 
	}
	
	public void testGodisnjiSumarniDobarDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2013);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
	        
			Izvjestaji iz = new Izvjestaji();
			iz.Godisnji("proba", myDate);
			assertTrue(true); 
		} catch (Exception e) { 
			fail(e.getMessage()); 
		} 
	}

	public void testMjesecniViseLokacijaLosDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2100);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
			Izvjestaji iz = new Izvjestaji();
			iz.SedmicniRadnici("proba", myDate);
			fail("Nije bacen izuzetak."); 
		} catch (Exception e) { 
			 assertTrue(true); 
		} 
	}
	
	public void testMjesecniViseLokacijaDobarDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2013);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
			Izvjestaji iz = new Izvjestaji();
			iz.SedmicniRadnici("proba", myDate);
			assertTrue(true); 
		} catch (Exception e) { 
			fail(e.getMessage()); 
		} 
	}
	
	public void testMjesecniStorniraniLosDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2100);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
			Izvjestaji iz = new Izvjestaji();
			iz.Godisnji("proba", myDate);
			fail("Nije bacen izuzetak."); 
		} catch (Exception e) { 
			 assertTrue(true); 
		} 
	}
	
	public void testMjesecniStorniraniDobarDatum() {
		try { 
			Date myDate;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.MONTH, 9);
	        cal.set(Calendar.DATE, 24);
	        cal.set(Calendar.YEAR, 2013);
	        cal.set(Calendar.HOUR,13);
	        cal.set(Calendar.MINUTE,45);
	        cal.set(Calendar.SECOND,52);
	        myDate = cal.getTime();
	        
			Izvjestaji iz = new Izvjestaji();
			iz.Godisnji("proba", myDate);
			assertTrue(true); 
		} catch (Exception e) { 
			fail(e.getMessage()); 
		} 
	}
}
