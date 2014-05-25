package ba.unsa.etf.si.tim1.jKP;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Zaposlenik {
	private int id;
	private String Ime;
	private String Prezime;
	private TipUposlenika tipUposlenika;
	private String KorisnickoIme;
	private String lozinka;
	Zaposlenik(String ime,String prezime, TipUposlenika TU, String ki, String loz) {
		setIme(ime);
		setPrezime(prezime);
		setTipUposlenika(TU);
		setLozinka(loz);
		setKorisnickoIme(ki);
	}
	void Izbrisi() {};
	void DeaktivirajKorisnickiRacun() {
		setTipUposlenika(TipUposlenika.neaktivan);
	}
	void PromijeniLozinku(String staralozinka,String novalozinka) throws Exception
	{
		if(staralozinka == getLozinka())
			setLozinka(novalozinka);
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
	public void spasiUBazu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(this);
		t.commit();
		session.close();
	}
	public String getKorisnickoIme() {
		return KorisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		KorisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
}
