package ba.unsa.etf.si.tim1.Hibernate;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ba.unsa.etf.si.tim1.jKP.RadniNalog;
import ba.unsa.etf.si.tim1.jKP.StatusRadnogNaloga;
import ba.unsa.etf.si.tim1.jKP.TipPosla;
import junit.framework.TestCase;

public class HibernateRadniNalogTest extends TestCase {

	public void testPohraniRadniNalog() {
		fail("Not yet implemented"); // TODO
	}

	public void testModifikujRadniNalog() {
		fail("Not yet implemented"); // TODO
	}

	public void testPretraga() {
		try{
			pripremiBazuZaTestiranje();
			List<String> podaci = new ArrayList();
			podaci.add("LOKACIJA");
			podaci.add("Bascarsija");
			
			List<RadniNalog> nalozi = HibernateRadniNalog.pretraga(podaci);
			assertEquals("Bla", nalozi.get(0).getLokacija());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void testDajSveRadneNaloge() {
		fail("Not yet implemented"); // TODO
	}

	public void testUbijOnogaKoJePravioHibernate() {
		fail("Not yet implemented"); // TODO
	}

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
			
			
			// kreiranje radnih naloga u bazi
			RadniNalog rn1 = new RadniNalog(datumKreiranja, 3, status, tipPosla, planiraniDatumIzvrsenja, 1, materijal, lokacija, datumIzvrsenja, utrosenoVrijeme, true, "opis posla");
			
			
			
			
			
			
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
	
		
		
	}
}
