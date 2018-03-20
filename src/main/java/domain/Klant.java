package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "KLANT")
public class Klant {

	@Id
	@SequenceGenerator(name="my_seq", sequenceName="klant_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	//@GeneratedValue(strategy=GenerationType.AUTO, generator="klant_id_seq")
	@Column(name = "KLANT_ID")

	private int iD;
	
	@Column(name = "NAAM")
	private String naam;
	
	@Column(name = "AFBEELDING")
	private int afbeelding;
	
	@Column(name = "WOONADRES")
	private String woonAdres;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ROLE")
	private String role;
	
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
	public String getWoonadres() {
		return woonAdres;
	}
	public void setWoonadres(String woonAdres) {
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
	public int getid() {
		return iD;
	}
	public void setid(int iD) {
		this.iD = iD;
	}

}
