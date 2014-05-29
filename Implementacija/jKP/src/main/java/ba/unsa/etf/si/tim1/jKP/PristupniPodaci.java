package ba.unsa.etf.si.tim1.jKP;

public class PristupniPodaci {
	private long id;
	private String korisnickoIme;
	private String lozinka;
	
	public PristupniPodaci(String korisnickoIme, String lozinka) {
		setKorisnickoIme(korisnickoIme);
		setLozinka(lozinka);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
}
