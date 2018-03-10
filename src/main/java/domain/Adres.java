package domain;

public class Adres {
	private int iD;
	private String Straat;
	private int straatNummer;
	private Klant klant;
	private Account account;
	private Bestelling bestelling;
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
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