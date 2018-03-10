package domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Column(name = "NAAM")
	private String naam;
	
	@Column(name = "PRIJS")
	private double prijs;
	
	@Id
	@Column(name ="PRODUCT_ID")
	private int iD;
	
	@Column(name="OMSCHRIJVING")
	private String omschrijving;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	private Set<Bestellingsregel> bestellingsregel;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	private Set<Aanbieding> aanbieding;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORIE_ID")
	private Categorie categorie;
	
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
