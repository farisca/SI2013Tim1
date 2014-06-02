package ba.unsa.etf.si.tim1.Hibernate;

import static org.junit.Assert.*;

import org.junit.*;

import ba.unsa.etf.si.tim1.jKP.PristupniPodaci;
import ba.unsa.etf.si.tim1.jKP.TipUposlenika;
import ba.unsa.etf.si.tim1.jKP.Zaposlenik;

public class HibernateZaposlenikTest {
	
	private Zaposlenik zaposlenik;
	private PristupniPodaci podaci;

	@Before
	public void inicijalizirajTest() {
		try {
			podaci = new PristupniPodaci("1civonizad", "password");
			podaci.setId(HibernatePristupniPodaci.spremiPodatke(podaci.getKorisnickoIme(), podaci.getLozinka()));
			zaposlenik = new Zaposlenik();
			zaposlenik.setIme("Dejan");
			zaposlenik.setPrezime("Azinovic");
			zaposlenik.setPristupniPodaci(podaci.getId());
			zaposlenik.postaviTipUposlenika(TipUposlenika.privilegirani);
			long id = HibernateZaposlenik.pohraniZaposlenika(zaposlenik, podaci.getId());
			zaposlenik.setId(id);
		}
		catch (Exception e) {
			
		}
	}
	
	@Test
	public void testDajZaposlenikaPoPristupnimPodacima() {
		Zaposlenik z = HibernateZaposlenik.dajZaposlenikaPoPristupnimPodacima(podaci.getId());
		Assert.assertEquals(zaposlenik.getId(), z.getId());
	}

	@Test
	public void testDajPristupnePodatkePoId() {
		PristupniPodaci p = HibernateZaposlenik.dajPristupnePodatkePoId(zaposlenik);
		Assert.assertEquals(p.getId(), podaci.getId());
	}

	@Test
	public void testDajSveZaposlenike() {
		java.util.List<Zaposlenik> zaposlenici = HibernateZaposlenik.dajSveZaposlenike();
		Assert.assertTrue(zaposlenici.size() > 0);
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
		z.setPristupniPodaci(65534);
		z.postaviTipUposlenika(TipUposlenika.privilegirani);
		long id = HibernateZaposlenik.pohraniZaposlenika(z, 65534);
		z.setId(id);
		
		z.postaviTipUposlenika(TipUposlenika.obicni);
		HibernateZaposlenik.urediZaposlenika(z);
		
		Zaposlenik zap = HibernateZaposlenik.dajZaposlenikaPoPristupnimPodacima(65534);
		Assert.assertEquals(z.getId(), zap.getId());
		
		HibernateZaposlenik.izbrisiZaposlenika(zap);
	}

	@After
	public void ocistiBazu() {
		HibernateZaposlenik.izbrisiZaposlenika(zaposlenik);
		HibernatePristupniPodaci.izbrisiPristupnePodatke(podaci);
	}

}
