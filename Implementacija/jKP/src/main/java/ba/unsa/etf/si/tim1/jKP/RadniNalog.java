package ba.unsa.etf.si.tim1.jKP;

import java.awt.List;
import java.sql.Time;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RadniNalog implements java.io.Serializable {
	private long brojRadnogNaloga;
	private Date datumKreiranja;
	private long kreatorRadnogNaloga;
	private StatusRadnogNaloga status;
	private TipPosla posao;
	private Date planiraniDatumIzvrsenja;
	private long izvrsilacPosla;
	private String potrebniMaterijal;
	private String lokacija;
	private Date datumIzvrsenja;
	private Time utrosenoVrijeme;
	private Boolean odobren;
	private String opisPosla;
	private String razlogStorniranja;
	private long osobaKojaStornira;
	private String razlogModifikovanja;
	private long osobaKojaModifikuje;
	private Date datumModifikovanja;
	
	public RadniNalog() {}
	
	public RadniNalog(Date datumKreiranja, Zaposlenik kreatorNaloga, StatusRadnogNaloga status, TipPosla tip, Date planiraniDatumIzvrsenja, 
						long izvrsilacPosla, String potrebniMaterijal, String lokacija, Date datumIzvrsenja, Time utrosenoVrijeme, Boolean odobren, String opis) {
		//setBrojRadnogNaloga(BRN);
		setDatumKreiranja(datumKreiranja);
		setKreatorRadnogNaloga(kreatorNaloga);
		setStatus(status);
		setPosao(tip);
		setPlaniraniDatumIzvrsenja(planiraniDatumIzvrsenja);
		setIzvrsilacPosla(izvrsilacPosla);
		setPotrebniMaterijal(potrebniMaterijal);
		setLokacija(lokacija);
		setDatumIzvrsenja(datumIzvrsenja);
		setUtrosenoVrijeme(utrosenoVrijeme);
		setOdobren(odobren);
		setOpisPosla(opis);
		setRazlogStorniranja("");
		setOsobaKojaStornira(null);
		setRazlogModifikovanja(null);
		setOsobaKojaModifikuje(null);
		setDatumModifikovanja(null);
	}
	void Obrisi() {};
	void Zakljuci() {
		setStatus(StatusRadnogNaloga.zakljucen);
	}
	void Odobri() {
		setOdobren(true);
	}
	public long getBrojRadnogNaloga() {
		return brojRadnogNaloga;
	}
	public void setBrojRadnogNaloga(int brojRadnogNaloga) {
		this.brojRadnogNaloga = brojRadnogNaloga;
	}
	public long getKreatorRadnogNaloga() {
		return kreatorRadnogNaloga;
	}
	public void setKreatorRadnogNaloga(Zaposlenik kreatorRadnogNaloga) {
		this.kreatorRadnogNaloga = kreatorRadnogNaloga.getId();
	}
	public Date getDatumKreiranja() {
		return datumKreiranja;
	}
	public void setDatumKreiranja(Date vrijemeRadnogNaloga) {
		datumKreiranja = vrijemeRadnogNaloga;
	}
	public StatusRadnogNaloga getStatus() {
		return status;
	}
	public void setStatus(StatusRadnogNaloga status) {
		this.status = status;
	}
	public TipPosla getPosao() {
		return posao;
	}
	public void setPosao(TipPosla posao) {
		this.posao = posao;
	}
	public long getIzvrsilacPosla() {
		return izvrsilacPosla;
	}
	public void setIzvrsilacPosla(long izvrsilac) {
		izvrsilacPosla = izvrsilac;
	}
	public Date getPlaniraniDatumIzvrsenja() {
		return planiraniDatumIzvrsenja;
	}
	public void setPlaniraniDatumIzvrsenja(Date planiraniDatumIzvrsenja) {
		this.planiraniDatumIzvrsenja = planiraniDatumIzvrsenja;
	}
	public String getPotrebniMaterijal() {
		return potrebniMaterijal;
	}
	public void setPotrebniMaterijal(String potrebniMaterijal) {
		this.potrebniMaterijal = potrebniMaterijal;
	}
	public String getLokacija() {
		return lokacija;
	}
	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}
	public Date getDatumIzvrsenja() {
		return datumIzvrsenja;
	}
	public void setDatumIzvrsenja(Date datumIzvrsenja) {
		this.datumIzvrsenja = datumIzvrsenja;
	}
	public Time getUtrosenoVrijeme() {
		return this.utrosenoVrijeme;
	}
	public void setUtrosenoVrijeme(Time utrosenoVrijeme) {
		this.utrosenoVrijeme = utrosenoVrijeme;
	}
	public Boolean getOdobren() {
		return odobren;
	}
	public void setOdobren(Boolean odobren) {
		this.odobren = odobren;
	}
	public String getOpisPosla() {
		return opisPosla;
	}
	public void setOpisPosla(String opisPosla) {
		this.opisPosla = opisPosla;
	}
	public String getRazlogStorniranja() {
		return razlogStorniranja;
	}
	public void setRazlogStorniranja(String razlogStorniranja) {
		this.razlogStorniranja = razlogStorniranja;
	}
	public long getOsobaKojaStornira() {
		return osobaKojaStornira;
	}
	public void setOsobaKojaStornira(Zaposlenik osobaKojaStornira) {
		if(osobaKojaStornira == null) this.osobaKojaStornira = -1;
		else this.osobaKojaStornira = osobaKojaStornira.getId();
	}
	public String getRazlogModifikovanja() {
		return razlogModifikovanja;
	}
	public void setRazlogModifikovanja(String razlogModifikovanja) {
		this.razlogModifikovanja = razlogModifikovanja;
	}
	public long getOsobaKojaModifikuje() {
		return osobaKojaModifikuje;
	}
	public void setOsobaKojaModifikuje(Zaposlenik osobaKojaModifikuje) {
		if(osobaKojaModifikuje == null) this.osobaKojaModifikuje = -1;
		else this.osobaKojaModifikuje = osobaKojaModifikuje.getId();
	}
	public Date getDatumModifikovanja() {
		return datumModifikovanja;
	}
	public void setDatumModifikovanja(Date datumModifikovanja) {
		this.datumModifikovanja = datumModifikovanja;
	}

	
	public void spasiUBazu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(this);
		t.commit();
		session.close();
	}
}
