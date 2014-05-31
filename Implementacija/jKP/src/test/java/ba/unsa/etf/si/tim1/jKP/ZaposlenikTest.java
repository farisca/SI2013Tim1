package ba.unsa.etf.si.tim1.jKP;

import junit.framework.TestCase;

public class ZaposlenikTest extends TestCase {

	public void testZaposlenik() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			assertEquals("i p", z.getImeIPrezime());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		}
	}

	public void testDeaktivirajKorisnickiRacun() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			z.DeaktivirajKorisnickiRacun();
			assertEquals(TipUposlenika.neaktivan, z.getTipUposlenika());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		}
	}

	public void testPromjeniTipUposlenika() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			z.setTipUposlenika(TipUposlenika.neaktivan);
			assertEquals(TipUposlenika.neaktivan, z.getTipUposlenika());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		}
	}

	public void testGetIme() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			assertEquals("i", z.getIme());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		}
	}
	
	public void testGetImeIPrezime() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			assertEquals("i p", z.getImeIPrezime());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		}
	}

	public void testSetIme() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			z.setIme("ABC");
			assertEquals("ABC", z.getIme());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		}
	}

	public void testGetPrezime() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			assertEquals("p", z.getPrezime());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		} // TODO
	}

	public void testSetPrezime() {
		try {
			Zaposlenik z = new Zaposlenik("i","p",TipUposlenika.obicni,1);
			z.setPrezime("ABC");
			assertEquals("ABC", z.getPrezime());
			fail("Nema izuzetka!");
		}
		catch(Exception s)
		{
			assertTrue(true);
		}
	}
}
