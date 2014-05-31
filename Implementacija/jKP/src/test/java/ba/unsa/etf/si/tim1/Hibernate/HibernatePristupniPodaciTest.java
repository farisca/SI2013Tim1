package ba.unsa.etf.si.tim1.Hibernate;

import static org.junit.Assert.*;

import org.junit.*;

import ba.unsa.etf.si.tim1.jKP.PristupniPodaci;

public class HibernatePristupniPodaciTest {
	PristupniPodaci podaci;

	@Before
	public void inicijalizirajTest() {
		try {
			long id = HibernatePristupniPodaci.spremiPodatke("dazinovic", HibernatePristupniPodaci.HesirajMD5("password"));
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
		Assert.assertEquals("dazinovic", podaci.getKorisnickoIme());
	}

	@Test
	public void testPostojiKorisnik() {
		boolean daLiPostoji = HibernatePristupniPodaci.postojiKorisnik(podaci.getKorisnickoIme());
		Assert.assertTrue(daLiPostoji);
	}

	@Test
	public void testProvjeriPodatke() {
		try {
			long id = HibernatePristupniPodaci.provjeriPodatke(podaci.getKorisnickoIme(), podaci.getLozinka());
			Assert.assertTrue(true);
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testSpremiPodatke() {
		try {
			long id = HibernatePristupniPodaci.spremiPodatke("dazinovic", HibernatePristupniPodaci.HesirajMD5("password"));
			podaci = HibernatePristupniPodaci.dajPristupnePodatke(id);
			Assert.assertEquals("dazinovic", podaci.getKorisnickoIme());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDajKorisnickoImePoKriteriju() {
		try {
			long id = HibernatePristupniPodaci.spremiPodatke("dazinovic", HibernatePristupniPodaci.HesirajMD5("password"));
			String ime = HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(id);
			Assert.assertEquals("dazinovic", ime);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUrediPristupnePodatke() {
		try {
			long id = HibernatePristupniPodaci.spremiPodatke("dazinovic", HibernatePristupniPodaci.HesirajMD5("password"));
			PristupniPodaci p = HibernatePristupniPodaci.dajPristupnePodatke(id);
			p.setKorisnickoIme("fcakaric");
			HibernatePristupniPodaci.urediPristupnePodatke(p);
			String ime = HibernatePristupniPodaci.dajKorisnickoImePoKriteriju(id);
			Assert.assertEquals("fcakaric", ime);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDajBrojKorisnika() {
		long brojKorisnika = HibernatePristupniPodaci.dajBrojKorisnika();
		Assert.assertEquals(1, brojKorisnika);
	}

}
