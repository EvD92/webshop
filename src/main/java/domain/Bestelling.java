package domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "BESTELLING")
public class Bestelling {
	
	@Id
	@Column(name = "BESTELLING_ID")
	private int iD;

	@Column(name = "AFLEVERADRES")
	private String afleverAdres;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy="bestelling")
	private Adres adres;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bestelling")
	private Set<Bestellingsregel> bestellingsRegel;
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getAfleverAdres() {
		return afleverAdres;
	}
	public void setAfleverAdres(String afleverAdres) {
		this.afleverAdres = afleverAdres;
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
	public Set<Bestellingsregel> getBestellingsRegel() {
		return bestellingsRegel;
	}
	public void setBestellingsRegel(Set<Bestellingsregel> bestellingsRegel) {
		this.bestellingsRegel = bestellingsRegel;
	}
	

}
