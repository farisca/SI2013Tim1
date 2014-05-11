package jKP;

import java.sql.Time;
import java.util.Date;

public class RadniNalog {
	private int BrojRadnogNaloga;
	private Date VrijemeRadnogNaloga;
	private Zaposlenik KreatorRadnogNaloga;
	private StatusRadnogNaloga Status;
	private TipPosla Posao;
	private Date PlaniraniDatumIzvrsenja;
	private Zaposlenik[] ListaIzvrsioca;
	private String PotrebniMaterijal;
	private String Lokacija;
	private Date DatumIzvrsenja;
	private Time UtrosenoVrijeme;
	private Boolean Odobren;
	RadniNalog(int BRN,Date VRN,Zaposlenik KRN,StatusRadnogNaloga stat,TipPosla pos,Date PDI,Zaposlenik[] LI,String PM,String lok,Date DI,Time UV,Boolean odo) {
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
	}
	void Obrisi() {};
	void Zakljuci() {
		setStatus(StatusRadnogNaloga.zakljucen);
	}
	void Printaj() {};
	void Odobri() {
		setOdobren(true);
	}
	public int getBrojRadnogNaloga() {
		return BrojRadnogNaloga;
	}
	public void setBrojRadnogNaloga(int brojRadnogNaloga) {
		BrojRadnogNaloga = brojRadnogNaloga;
	}
	public Zaposlenik getKreatorRadnogNaloga() {
		return KreatorRadnogNaloga;
	}
	public void setKreatorRadnogNaloga(Zaposlenik kreatorRadnogNaloga) {
		KreatorRadnogNaloga = kreatorRadnogNaloga;
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
	public Zaposlenik[] getListaIzvrsioca() {
		return ListaIzvrsioca;
	}
	public void setListaIzvrsioca(Zaposlenik[] listaIzvrsioca) {
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
	}
