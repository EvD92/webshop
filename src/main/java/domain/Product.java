package domain;

import java.util.Set;

public class Product {
	private String naam;
	private double prijs;
	private int iD;
	private Set<Bestellingsregel> bestellingsregel;
	private Set<Aanbieding> aanbieding;
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public double getPrijs() {
		return prijs;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public Set<Bestellingsregel> getBestellingsregel() {
		return bestellingsregel;
	}
	public void setBestellingsregel(Set<Bestellingsregel> bestellingsregel) {
		this.bestellingsregel = bestellingsregel;
	}
	public Set<Aanbieding> getAanbieding() {
		return aanbieding;
	}
	public void setAanbieding(Set<Aanbieding> aanbieding) {
		this.aanbieding = aanbieding;
	}

}
