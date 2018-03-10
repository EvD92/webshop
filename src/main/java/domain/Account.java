package domain;

import java.util.Set;

public class Account {
	private String openDatum;
	private String factuurAdres;
	private int iD;
	private boolean isActief;
	private Set<Bestelling> bestelling;
	private Adres adres;
	private Klant klant;
	public String getOpenDatum() {
		return openDatum;
	}
	public void setOpenDatum(String openDatum) {
		this.openDatum = openDatum;
	}
	public String getFactuurAdres() {
		return factuurAdres;
	}
	public void setFactuurAdres(String factuurAdres) {
		this.factuurAdres = factuurAdres;
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public boolean isActief() {
		return isActief;
	}
	public void setActief(boolean isActief) {
		this.isActief = isActief;
	}
	public Set<Bestelling> getBestelling() {
		return bestelling;
	}
	public void setBestelling(Set<Bestelling> bestelling) {
		this.bestelling = bestelling;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	public Klant getKlant() {
		return klant;
	}
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
}
