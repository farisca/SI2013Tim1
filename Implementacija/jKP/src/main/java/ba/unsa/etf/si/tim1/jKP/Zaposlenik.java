package ba.unsa.etf.si.tim1.jKP;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Zaposlenik implements java.io.Serializable {
	private long id;
	private String ime;
	private String prezime;
	private TipUposlenika tipUposlenika;
	private long pristupniPodaci;
	
	public Zaposlenik() {}
	
	public Zaposlenik(String ime, String prezime, TipUposlenika tipUposlenika, long podaci) {
		setIme(ime);
		setPrezime(prezime);
		setTipUposlenika(tipUposlenika);
		setPristupniPodaci(podaci);
		//setLozinka(loz);
		//setKorisnickoIme(ki);
	}
	void Izbrisi() {};
	void DeaktivirajKorisnickiRacun() {
		setTipUposlenika(TipUposlenika.neaktivan);
	}
	/*void PromijeniLozinku(String staralozinka,String novalozinka) throws Exception
	{
		if(staralozinka == getLozinka())
			setLozinka(novalozinka);
		else throw new Exception("Lozinka nije validna!");
	}*/
	void PromjeniTipUposlenika(TipUposlenika ntu) {
		setTipUposlenika(ntu);
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public TipUposlenika getTipUposlenika() {
		return tipUposlenika;
	}
	public void setTipUposlenika(TipUposlenika tipUposlenika) {
		this.tipUposlenika = tipUposlenika;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	private long getPristupniPodaci() {
		return pristupniPodaci;
	}

	private void setPristupniPodaci(long pristupniPodaci) {
		this.pristupniPodaci = pristupniPodaci;
	}
	
	@Override
	public String toString() {
		return getIme() + " " + getPrezime();
	}
	

}
