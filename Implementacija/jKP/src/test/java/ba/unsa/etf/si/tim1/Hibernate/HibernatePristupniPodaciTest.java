package ba.unsa.etf.si.tim1.Hibernate;

import static org.junit.Assert.*;

import org.junit.*;

import ba.unsa.etf.si.tim1.Entiteti.PristupniPodaci;

public class HibernatePristupniPodaciTest {
	PristupniPodaci podaci;

	@Before
	public void inicijalizirajTest() {
		try {
			long id = HibernatePristupniPodaci.spremiPodatke("civonizad", "abc123");
			podaci = HibernatePristupniPodaci.dajPristupnePodatke(id);
		}
		catch (Exception e) {
			
		}
	}

	@Test
	public void testHesirajMD5() {
		String password = "abc123";
		String actual = HibernatePristupniPodaci.HesirajMD5(password);
		String expected = "e99a18c428cb38d5f260853678922e03";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDajPristupnePodatke() {
		Assert.assertEquals("civonizad", podaci.getKorisnickoIme());
	}

	@Test
	public void testPostojiKorisnik() {
		boolean daLiPostoji = HibernatePristupniPodaci.postojiKorisnik(podaci.getKorisnickoIme());
		Assert.assertTrue(daLiPostoji);
	}

	@Test
	public void testProvjeriPodatke() {
		try {
			HibernatePristupniPodaci.provjeriPodatke(podaci.getKorisnickoIme(), "abc123");
			Assert.assertTrue(true);
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testDajKorisnickoImePoKriteriju() {
		String ime = HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(podaci.getId());
		Assert.assertEquals("civonizad", ime);
	}

	@Test
	public void testUrediPristupnePodatke() {
		try {
			long id = HibernatePristupniPodaci.spremiPodatke("dazinovic999", "password");
			PristupniPodaci p = HibernatePristupniPodaci.dajPristupnePodatke(id);
			p.setKorisnickoIme("fcakaric999");
			HibernatePristupniPodaci.urediPristupnePodatke(p);
			String ime = HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(id);
			Assert.assertEquals("fcakaric999", ime);
			
			HibernatePristupniPodaci.izbrisiPristupnePodatke(p);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@After
	public void ocistiBazu() {
		HibernatePristupniPodaci.izbrisiPristupnePodatke(podaci);
	}

}
