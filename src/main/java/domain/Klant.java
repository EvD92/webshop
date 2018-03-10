package domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Klant {
	
	@Id
	@Column(name = "NAAM")
	private String naam;
	
	@Column(name = "AFBEELDING")
	private int afbeelding;
	
	@Column(name = "WOONADRES")
	private String woonAdres;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "klant")
	private Account account;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "klant")
	private Adres adres;
	
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getAfbeelding() {
		return afbeelding;
	}
	public void setAfbeelding(int afbeelding) {
		this.afbeelding = afbeelding;
	}
	public String getWoonAdres() {
		return woonAdres;
	}
	public void setWoonAdres(String woonAdres) {
		this.woonAdres = woonAdres;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}

}
