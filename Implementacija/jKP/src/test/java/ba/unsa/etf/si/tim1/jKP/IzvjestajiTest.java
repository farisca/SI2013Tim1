package ba.unsa.etf.si.tim1.jKP;

import java.util.Date;

import junit.framework.TestCase;

public class IzvjestajiTest extends TestCase {

	public void testSedmicniRadniciLosDatum() {
		try { 
			Date date = new Date();
			date.setYear(2100);
			Izvjestaji iz = new Izvjestaji();
			iz.SedmicniRadnici("proba", date);
			fail("Nije bacen izuzetak."); 
		} catch (Exception e) { 
			 assertTrue(true); 
		} 
	}
	
	public void testSedmicniRadniciDobarDatum() {
		try { 
			Date date = new Date();
			date.setYear(2013);
			Izvjestaji iz = new Izvjestaji();
			iz.SedmicniRadnici("proba", date);
			assertTrue(true); 
		} catch (Exception e) { 
			fail(e.getMessage()); 
		} 
	}
	
	public void testGodisnjiSumarniLosDatum() {
		try { 
			Date date = new Date();
			date.setYear(2100);
			Izvjestaji iz = new Izvjestaji();
			iz.Godisnji("proba", date);
			fail("Nije bacen izuzetak."); 
		} catch (Exception e) { 
			 assertTrue(true); 
		} 
	}
	
	public void testGodisnjiSumarniDobarDatum() {
		try { 
			Date date = new Date();
			date.setYear(2013);
			Izvjestaji iz = new Izvjestaji();
			iz.Godisnji("proba", date);
			assertTrue(true); 
		} catch (Exception e) { 
			fail(e.getMessage()); 
		} 
	}

}
