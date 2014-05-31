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
	private long osobaKojaZakljucuje;
	private String dodatniKomentar;
	
	

	public RadniNalog() {}
	
	public RadniNalog(Date datumKreiranja, long kreatorNaloga, StatusRadnogNaloga status, TipPosla tip, Date planiraniDatumIzvrsenja, 
						long izvrsilacPosla, String potrebniMaterijal, String lokacija, Date datumIzvrsenja, Time utrosenoVrijeme, Boolean odobren, String opis) throws Exception {
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
		setOsobaKojaStornira(-1);
		setRazlogModifikovanja(null);
		setOsobaKojaModifikuje(-1);
		setDatumModifikovanja(null);
		setOsobaKojaZakljucuje(-1);
		setDodatniKomentar(null);
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
	public void setBrojRadnogNaloga(long brojRadnogNaloga) {
		this.brojRadnogNaloga = brojRadnogNaloga;
	}
	public long getKreatorRadnogNaloga() {
		return kreatorRadnogNaloga;
	}
	public void setKreatorRadnogNaloga(long kreatorNaloga) {
		this.kreatorRadnogNaloga = kreatorNaloga;
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
	public void setDatumIzvrsenja(Date datumIzvrsenja) throws Exception {
		if (datumIzvrsenja != null && datumIzvrsenja.after(new Date()))
			throw new Exception("Datum izvršenja radnog naloga ne može biti u budućnosti.");
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
	public void setOsobaKojaStornira(long osobaKojaStornira) {
		//if(osobaKojaStornira == null) this.osobaKojaStornira = -1;
		//else 
			this.osobaKojaStornira = osobaKojaStornira;
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
	public void setOsobaKojaModifikuje(long osobaKojaModifikuje) {
	//	if(osobaKojaModifikuje == null) this.osobaKojaModifikuje = -1;
	//	else
			this.osobaKojaModifikuje = osobaKojaModifikuje;
	}
	public Date getDatumModifikovanja() {
		return datumModifikovanja;
	}
	public void setDatumModifikovanja(Date datumModifikovanja) {
		this.datumModifikovanja = datumModifikovanja;
	}
	
	public long getOsobaKojaZakljucuje() {
		return osobaKojaZakljucuje;
	}

	public void setOsobaKojaZakljucuje(long osobaKojaZakljucuje) {
		this.osobaKojaZakljucuje = osobaKojaZakljucuje;
	}

	public String getDodatniKomentar() {
		return dodatniKomentar;
	}

	public void setDodatniKomentar(String dodatniKomentar) {
		this.dodatniKomentar = dodatniKomentar;
	}

	
	/*public void spasiUBazu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(this);
		t.commit();
		session.close();
	}*/
}
