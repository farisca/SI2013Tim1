package ba.unsa.etf.si.tim1.jKP;

public class Zaposlenik {
	private String Ime;
	private String Prezime;
	private tipUposlenika TipUposlenika;
	private PristupniPodaci Podaci;
	public PristupniPodaci getPodaci() {
		return Podaci;
	}
	public void setPodaci(PristupniPodaci podaci) {
		Podaci = podaci;
	}
	Zaposlenik(String ime,String prezime, tipUposlenika TU, PristupniPodaci pod) {
		setIme(ime);
		setPrezime(prezime);
		setTipUposlenika(TU);
		Podaci = pod;
	}
	void Izbrisi() {};
	void DeaktivirajKorisnickiRacun() {
		setTipUposlenika(tipUposlenika.neaktivan);
	}
	void PromjeniLozinku(String staralozinka,String novalozinka) throws Exception
	{
		if(staralozinka == Podaci.getLozinka())
			Podaci.setLozinka(novalozinka);
		else throw new Exception("Lozinka nije validna!");
	}
	void PromjeniTipUposlenika(tipUposlenika ntu) {
		setTipUposlenika(ntu);
	}
	public String getIme() {
		return Ime;
	}
	public void setIme(String ime) {
		Ime = ime;
	}
	public String getPrezime() {
		return Prezime;
	}
	public void setPrezime(String prezime) {
		Prezime = prezime;
	}
	public tipUposlenika getTipUposlenika() {
		return TipUposlenika;
	}
	public void setTipUposlenika(tipUposlenika tipUposlenika) {
		TipUposlenika = tipUposlenika;
	}
}
