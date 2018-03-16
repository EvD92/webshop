package domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Column(name = "NAAM")
	private String naam;
	
	@Column(name = "PRIJS")
	private float prijs;
	
	@Id
	@SequenceGenerator(name="my_seq", sequenceName="product_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	//@GeneratedValue(strategy=GenerationType.AUTO, generator="product_id_seq")
	@Column(name ="PRODUCT_ID")
	private int iD;
	
	@Column(name="OMSCHRIJVING")
	private String omschrijving;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	private Set<Bestellingsregel> bestellingsregel;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	private Set<Aanbieding> aanbieding;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "product")
	private Set<Categorie> categorie;
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public float getPrijs() {
		return prijs;
	}
	public void setPrijs(float prijs) {
		this.prijs = prijs;
	}
	public int getId() {
		return iD;
	}
	public void setId(int iD) {
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
	public String getOmschrijving() {
		return omschrijving;
	}
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	public Set<Categorie> getCategorie() {
		return categorie;
	}
	public void setCategorie(Set<Categorie> categorie) {
		this.categorie = categorie;
	}

}
