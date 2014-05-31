package ba.unsa.etf.si.tim1.Hibernate;

import static org.junit.Assert.*;

import org.junit.*;

import ba.unsa.etf.si.tim1.jKP.PristupniPodaci;
import ba.unsa.etf.si.tim1.jKP.TipUposlenika;
import ba.unsa.etf.si.tim1.jKP.Zaposlenik;

public class HibernateZaposlenikTest {
	
	private Zaposlenik zaposlenik;

	@Before
	public void inicijalizirajTest() {
		try {
			zaposlenik = new Zaposlenik();
			zaposlenik.setIme("Dejan");
			zaposlenik.setPrezime("Azinovic");
			zaposlenik.setPristupniPodaci(65535);
			zaposlenik.setTipUposlenika(TipUposlenika.privilegirani);
			long id = HibernateZaposlenik.pohraniZaposlenika(zaposlenik, 65535);
			zaposlenik.setId(id);
		}
		catch (Exception e) {
			
		}
	}
	
	@Test
	public void testDajZaposlenikaPoPristupnimPodacima() {
		Zaposlenik z = HibernateZaposlenik.dajZaposlenikaPoPristupnimPodacima(65535);
		Assert.assertEquals(zaposlenik.getId(), z.getId());
	}

	@Test
	public void testDajPristupnePodatkePoId() {
		PristupniPodaci podaci = HibernateZaposlenik.dajPristupnePodatkePoId(zaposlenik);
		Assert.assertEquals(zaposlenik.getPristupniPodaci(), podaci.getId());
	}

	@Test
	public void testDajSveZaposlenike() {
		java.util.List<Zaposlenik> zaposlenici = HibernateZaposlenik.dajSveZaposlenike();
		Assert.assertTrue(zaposlenici.size() > 0);
	}

	@Test
	public void testPohraniZaposlenika() {
		Zaposlenik z = new Zaposlenik();
		z.setIme("Faris");
		z.setPrezime("Cakaric");
		z.setPristupniPodaci(65534);
		z.setTipUposlenika(TipUposlenika.privilegirani);
		long id = HibernateZaposlenik.pohraniZaposlenika(z, 65534);
		z.setId(id);
		
		Zaposlenik zap = HibernateZaposlenik.dajZaposlenikaPoPristupnimPodacima(65534);
		Assert.assertEquals(z.getId(), zap.getId());
	}

	@Test
	public void testDajZaposlenikePoKriteriju() {
		java.util.List<Zaposlenik> zaposlenici = HibernateZaposlenik.dajZaposlenikePoKriteriju("Dejan");
		Assert.assertTrue(zaposlenici.size() > 0);
	}

	@Test
	public void testUrediZaposlenika() {
		Zaposlenik z = new Zaposlenik();
		z.setIme("Faris");
		z.setPrezime("Cakaric");
		z.setPristupniPodaci(65533);
		z.setTipUposlenika(TipUposlenika.privilegirani);
		long id = HibernateZaposlenik.pohraniZaposlenika(z, 65533);
		z.setId(id);
		
		z.setTipUposlenika(TipUposlenika.obicni);
		HibernateZaposlenik.urediZaposlenika(z);
		
		Zaposlenik zap = HibernateZaposlenik.dajZaposlenikaPoPristupnimPodacima(65533);
		Assert.assertEquals(z.getId(), zap.getId());
	}

	/*@Test
	public void testDajBrojZaposlenika() {
		fail("Not yet implemented"); // TODO
	}*/

	@Test
	public void testUbijOnogaKoJePravioHibernate() {
		Assert.assertTrue(true); // Because I can...
	}

}
