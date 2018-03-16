package domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	@Column(name = "OPEN_DATUM")
	private String openDatum;
	
	@Column(name = "FACTUURADRES")
	private String factuurAdres;
	
	@Id
	@SequenceGenerator(name="my_seq", sequenceName="account_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	//@GeneratedValue(strategy=GenerationType.AUTO, generator="account_id_seq")
	@Column(name = "ACCOUNT_ID")
	private int iD;
	
	@Column(name = "IS_ACTIEF")
	private boolean isActief;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private Set<Bestelling> bestelling;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account") 
	private Adres adres;
	
	@OneToOne
	@JoinColumn(name="klant_id")
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
	public int getId() {
		return iD;
	}
	public void setId(int iD) {
		this.iD = iD;
	}
	public boolean getIsActief() {
		return isActief;
	}
	public void setActief(boolean isActief) {
		this.isActief = isActief;
	}
	public void setIsActief(boolean isActief) {
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
