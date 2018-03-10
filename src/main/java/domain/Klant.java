package domain;

public class Klant {
	private String naam;
	private int afbeelding;
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
	private String woonAdres;
	private Account account;
	private Adres adres;

}
