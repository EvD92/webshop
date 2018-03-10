package domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	@Column(name = "OPEN_DATUM")
	private String openDatum;
	
	@Column(name = "FACTUURADRES")
	private String factuurAdres;
	
	@Id
	@Column(name = "ACCOUNT_ID")
	private int iD;
	
	@Column(name = "IS_ACTIEF")
	private boolean isActief;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "BESTELLING_ID")
	private Set<Bestelling> bestelling;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Adres adres;
	
	@OneToOne
	@PrimaryKeyJoinColumn
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
