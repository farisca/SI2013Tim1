package ba.unsa.etf.si.tim1.GUI;

import static org.junit.Assert.*;

import java.awt.Dialog;
import java.sql.Time;
import java.util.Date;

import ba.unsa.etf.si.tim1.Entiteti.*;

import org.junit.Test;

public class StornirajRadniNalogTest {

	@Test
	public void testNapraviStorniranRN() {
		Date d=new Date(2014,5,5);
		long kn = 1;
		String s = StatusRadnogNaloga.kreiran.toString();
		String tp = TipPosla.UgradnjaVodomjera.toString();
		Date dpi=new Date(2014,5,6);
		long ip = 1;
		String pm="masina";
		String l="sarajevo";
		String o="ja";
		RadniNalog r;
		try {
			r = new RadniNalog(d, kn, s, tp, dpi, ip, pm, l, null, null, false, o);
			Zaposlenik z= new Zaposlenik("ja","ja", TipUposlenika.privilegirani.toString(), 2);
			GlavniProzor g = new GlavniProzor(z);
			StornirajRadniNalog srn=new StornirajRadniNalog(z, r, Dialog.ModalityType.APPLICATION_MODAL, g);
			RadniNalog a = srn.NapraviStorniranRN(r, "zato",2, "" );
			r.setRazlogStorniranja("zato");
			r.setOsobaKojaStornira(2);
			r.setDodatniKomentar("");
			r.postaviStatus(StatusRadnogNaloga.storniran);
			assertEquals(a.getStatus(),r.getStatus());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
