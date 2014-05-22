package ba.unsa.etf.si.tim1.jKP;

import java.awt.List;
import java.sql.Time;
import java.util.Date;

public class RadniNalog implements java.io.Serializable {
	private long id;
	private int brojRadnogNaloga;
	private Date VrijemeRadnogNaloga;
	private int KreatorRadnogNaloga;
	private StatusRadnogNaloga Status;
	private TipPosla Posao;
	private Date PlaniraniDatumIzvrsenja;
	private List ListaIzvrsioca;
	private String PotrebniMaterijal;
	private String Lokacija;
	private Date DatumIzvrsenja;
	private Time UtrosenoVrijeme;
	private Boolean Odobren;
	private String opisPosla;
	private String razlogStorniranja;
	private int osobaKojaStornira;
	private String razlogModifikovanja;
	private int osobaKojaModifikuje;
	private Date datumModifikovanja;
	
	public RadniNalog() {}
	
	public RadniNalog(int BRN,Date VRN,Zaposlenik KRN,StatusRadnogNaloga stat,TipPosla pos,Date PDI,List LI,String PM,String lok,Date DI,Time UV,Boolean odo, String OP) {
		setBrojRadnogNaloga(BRN);
		setVrijemeRadnogNaloga(VRN);
		setKreatorRadnogNaloga(KRN);
		setStatus(stat);
		setPosao(pos);
		setPlaniraniDatumIzvrsenja(PDI);
		setListaIzvrsioca(LI);
		setPotrebniMaterijal(PM);
		setLokacija(lok);
		setDatumIzvrsenja(DI);
		setUtrosenoVrijeme(UV);
		setOdobren(odo);
		setOpisPosla(OP);
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
	public int getBrojRadnogNaloga() {
		return brojRadnogNaloga;
	}
	public void setBrojRadnogNaloga(int brojRadnogNaloga) {
		this.brojRadnogNaloga = brojRadnogNaloga;
	}
	public int getKreatorRadnogNaloga() {
		return KreatorRadnogNaloga;
	}
	public void setKreatorRadnogNaloga(Zaposlenik kreatorRadnogNaloga) {
		KreatorRadnogNaloga = kreatorRadnogNaloga.getId();
	}
	public Date getVrijemeRadnogNaloga() {
		return VrijemeRadnogNaloga;
	}
	public void setVrijemeRadnogNaloga(Date vrijemeRadnogNaloga) {
		VrijemeRadnogNaloga = vrijemeRadnogNaloga;
	}
	public StatusRadnogNaloga getStatus() {
		return Status;
	}
	public void setStatus(StatusRadnogNaloga status) {
		Status = status;
	}
	public TipPosla getPosao() {
		return Posao;
	}
	public void setPosao(TipPosla posao) {
		Posao = posao;
	}
	public List getListaIzvrsioca() {
		return ListaIzvrsioca;
	}
	public void setListaIzvrsioca(List listaIzvrsioca) {
		ListaIzvrsioca = listaIzvrsioca;
	}
	public Date getPlaniraniDatumIzvrsenja() {
		return PlaniraniDatumIzvrsenja;
	}
	public void setPlaniraniDatumIzvrsenja(Date planiraniDatumIzvrsenja) {
		PlaniraniDatumIzvrsenja = planiraniDatumIzvrsenja;
	}
	public String getPotrebniMaterijal() {
		return PotrebniMaterijal;
	}
	public void setPotrebniMaterijal(String potrebniMaterijal) {
		PotrebniMaterijal = potrebniMaterijal;
	}
	public String getLokacija() {
		return Lokacija;
	}
	public void setLokacija(String lokacija) {
		Lokacija = lokacija;
	}
	public Date getDatumIzvrsenja() {
		return DatumIzvrsenja;
	}
	public void setDatumIzvrsenja(Date datumIzvrsenja) {
		DatumIzvrsenja = datumIzvrsenja;
	}
	public Time getUtrosenoVrijeme() {
		return UtrosenoVrijeme;
	}
	public void setUtrosenoVrijeme(Time utrosenoVrijeme) {
		UtrosenoVrijeme = utrosenoVrijeme;
	}
	public Boolean getOdobren() {
		return Odobren;
	}
	public void setOdobren(Boolean odobren) {
		Odobren = odobren;
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
	public int getOsobaKojaStornira() {
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
	public int getOsobaKojaModifikuje() {
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
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	}
