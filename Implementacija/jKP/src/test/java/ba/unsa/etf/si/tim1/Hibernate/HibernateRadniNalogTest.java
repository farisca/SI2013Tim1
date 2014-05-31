package ba.unsa.etf.si.tim1.Hibernate;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

import ba.unsa.etf.si.tim1.jKP.RadniNalog;
import ba.unsa.etf.si.tim1.jKP.StatusRadnogNaloga;
import ba.unsa.etf.si.tim1.jKP.TipPosla;
import junit.framework.TestCase;

public class HibernateRadniNalogTest extends TestCase {

	RadniNalog rn1;
	RadniNalog rn2;
	
	@Test
	public void testPohraniRadniNalog() {
		
		fail("Not yet implemented"); // TODO
		
	}
	@Test
	public void testModifikujRadniNalog() {
		
	}
	@Test
	public void testPretraga() {
		try{
			pripremiBazuZaTestiranje();
			HibernateRadniNalog.pohraniRadniNalog(rn1);
			List<String> podaci = new ArrayList();
			podaci.add("LOKACIJA");
			podaci.add("Bascarsija");
			
			List<RadniNalog> nalozi = HibernateRadniNalog.pretraga(podaci);
			Assert.assertEquals("Bascarsija", nalozi.get(0).getLokacija());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Test
	public void testDajSveRadneNaloge() {
		List<RadniNalog> nalozi = HibernateRadniNalog.dajSveRadneNaloge();
		Assert.assertTrue(2 <= nalozi.size());
	}
	@Test
	public void testUbijOnogaKoJePravioHibernate() {
		fail("Not yet implemented"); // TODO
	}

	@Before
	private void pripremiBazuZaTestiranje() throws Exception{
		

		try {
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat tf = new SimpleDateFormat("hh:mm:ss");
			
			 
			Date datumKreiranja = new Date();
			StatusRadnogNaloga status = StatusRadnogNaloga.kreiran;
			TipPosla tipPosla = TipPosla.UgradnjaVodomjera;
			
			
			Date planiraniDatumIzvrsenja = df.parse("2014-05-30 00:00:00");
			Date datumIzvrsenja = df.parse("2014-05-30 00:00:00");
			Date vrijeme = (Date)tf.parse("02:00:00");
			Time utrosenoVrijeme = (Time) vrijeme;
			
			String materijal = "vodomjer";
			String lokacija = "Bascarsija";
			
			
			RadniNalog rn1 = new RadniNalog(datumKreiranja, 3, status, tipPosla, planiraniDatumIzvrsenja, 1, materijal, lokacija, datumIzvrsenja, utrosenoVrijeme, true, "opis posla");
			RadniNalog rn2 = new RadniNalog(datumKreiranja, 3, status, tipPosla, planiraniDatumIzvrsenja, 1, materijal, lokacija, datumIzvrsenja, utrosenoVrijeme, true, "opis posla");
			
			
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
	
		
		
	}
	
}
