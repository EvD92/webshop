package domain;

import java.util.Set;

public class Bestelling {
	private int iD;
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
	private String afleverAdres;
	private Account account;
	private Adres adres;
	private Set<Bestellingsregel> bestellingsRegel;

}
