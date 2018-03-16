package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ADRES")
public class Adres {
	
	@Id
	@SequenceGenerator(name="my_seq", sequenceName="adres_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	//@GeneratedValue(strategy=GenerationType.AUTO, generator="adres_id_seq")
	@Column(name = "ADRES_ID")
	private int iD;
	
	@Column(name = "STRAAT")
	private String Straat;
	
	@Column(name = "STRAATNUMMER")
	private int straatNummer;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="klant_id")
	private Klant klant;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bestelling_id")
	private Bestelling bestelling;
	
	public int getId() {
		return iD;
	}
	public void setId(int iD) {
		this.iD = iD;
	}
	public String getStraat() {
		return Straat;
	}
	public void setStraat(String straat) {
		Straat = straat;
	}
	public int getStraatNummer() {
		return straatNummer;
	}
	public void setStraatNummer(int straatNummer) {
		this.straatNummer = straatNummer;
	}
	public Klant getKlant() {
		return klant;
	}
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Bestelling getBestelling() {
		return bestelling;
	}
	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}

}