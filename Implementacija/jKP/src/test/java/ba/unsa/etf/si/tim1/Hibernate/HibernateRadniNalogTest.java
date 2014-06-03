package ba.unsa.etf.si.tim1.Hibernate;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


import ba.unsa.etf.si.tim1.Entiteti.RadniNalog;
import ba.unsa.etf.si.tim1.Entiteti.StatusRadnogNaloga;
import ba.unsa.etf.si.tim1.Entiteti.TipPosla;


public class HibernateRadniNalogTest {

	RadniNalog rn1;
	RadniNalog rn2;
	
	@Before
	public void pripremiBazuZaTestiranje() throws Exception {
		try {
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat tf = new SimpleDateFormat("hh:mm:ss");
			
			 
			Date datumKreiranja = new Date();
			String status = StatusRadnogNaloga.kreiran.toString();
			String tipPosla = TipPosla.UgradnjaVodomjera.toString();
			
			
			Date planiraniDatumIzvrsenja = df.parse("2014-05-30 00:00:00");
			Date datumIzvrsenja = df.parse("2014-05-30 00:00:00");
			Time utrosenoVrijeme = new Time(2, 0, 0);
			
			String materijal = "vodomjer";
			String lokacija = "Bascarsija";
			
			
			rn1 = new RadniNalog(datumKreiranja, 3, status, tipPosla, planiraniDatumIzvrsenja, 1, materijal, lokacija, datumIzvrsenja, utrosenoVrijeme, true, "opis posla");
			rn2 = new RadniNalog(datumKreiranja, 3, status, tipPosla, planiraniDatumIzvrsenja, 1, materijal, lokacija, datumIzvrsenja, utrosenoVrijeme, true, "opis posla");
			
			HibernateRadniNalog.pohraniRadniNalog(rn1);
			HibernateRadniNalog.pohraniRadniNalog(rn2);
			
        }
		catch (java.text.ParseException e) {
            e.printStackTrace();
        }
	}
	
	/*@Test
	public void testPohraniRadniNalog() {
		
		fail("Not yet implemented"); // TODO
		
	}*/
	
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
			
			List<RadniNalog> nalozi = HibernateRadniNalog.pretragaPoListiKriterija(podaci);
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

	
	@After
	public void ocistiBazu() {
		HibernateRadniNalog.izbrisiRadniNalog(rn1);
		HibernateRadniNalog.izbrisiRadniNalog(rn2);
	}
	
}
