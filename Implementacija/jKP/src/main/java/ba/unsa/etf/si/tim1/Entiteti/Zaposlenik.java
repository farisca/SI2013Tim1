package ba.unsa.etf.si.tim1.Entiteti;


public class Zaposlenik implements java.io.Serializable {
	private long id;
	private String ime;
	private String prezime;
	private String tipUposlenika;
	private long pristupniPodaci;
	
	public Zaposlenik() {}
	
	public Zaposlenik(String ime, String prezime, String tipUposlenika, long podaci) {
		setIme(ime);
		setPrezime(prezime);
		setTipUposlenika(tipUposlenika);
		setPristupniPodaci(podaci);
	}

	void DeaktivirajKorisnickiRacun() {
		setTipUposlenika(TipUposlenika.neaktivan.toString());
	}

	void PromjeniTipUposlenika(String ntu) {
		setTipUposlenika(ntu);
	}
	
	public TipUposlenika dajTipUposlenika() {
		return TipUposlenika.valueOf(tipUposlenika);
	}
	
	public void postaviTipUposlenika(TipUposlenika tip) {
		setTipUposlenika(tip.toString());
	}
	
	public String getIme() {
		return ime;
	}
	
	public String getImeIPrezime(){
		return ime + " " + prezime;
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
	public String getTipUposlenika() {
		return tipUposlenika;
	}
	public void setTipUposlenika(String tipUposlenika) {
		this.tipUposlenika = tipUposlenika;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public long getPristupniPodaci() {
		return pristupniPodaci;
	}

	public void setPristupniPodaci(long pristupniPodaci) {
		this.pristupniPodaci = pristupniPodaci;
	}
	
	@Override
	public String toString() {
		return getIme() + " " + getPrezime();
	}
	

}
