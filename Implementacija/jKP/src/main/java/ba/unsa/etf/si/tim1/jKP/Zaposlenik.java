package ba.unsa.etf.si.tim1.jKP;

public class Zaposlenik {
	private int id;
	private String Ime;
	private String Prezime;
	private TipUposlenika tipUposlenika;
	private PristupniPodaci Podaci;
	public PristupniPodaci getPodaci() {
		return Podaci;
	}
	public void setPodaci(PristupniPodaci podaci) {
		Podaci = podaci;
	}
	Zaposlenik(String ime,String prezime, TipUposlenika TU, PristupniPodaci pod) {
		setIme(ime);
		setPrezime(prezime);
		setTipUposlenika(TU);
		Podaci = pod;
	}
	void Izbrisi() {};
	void DeaktivirajKorisnickiRacun() {
		setTipUposlenika(TipUposlenika.neaktivan);
	}
	void PromijeniLozinku(String staralozinka,String novalozinka) throws Exception
	{
		if(staralozinka == Podaci.getLozinka())
			Podaci.setLozinka(novalozinka);
		else throw new Exception("Lozinka nije validna!");
	}
	void PromjeniTipUposlenika(TipUposlenika ntu) {
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
	public TipUposlenika getTipUposlenika() {
		return tipUposlenika;
	}
	public void setTipUposlenika(TipUposlenika tipUposlenika) {
		this.tipUposlenika = tipUposlenika;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
