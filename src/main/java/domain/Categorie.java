package domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIE")
public class Categorie {
	@Id
	@Column(name="CATEGORIE_ID")
	private int id;
	
	@Column(name="NAAM")
	private String naam;
	
	@Column(name="OMSCHRIJVING")
	private String omschrijving;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CAT_PROD",
        joinColumns = @JoinColumn(name = "CATEGORIE_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	private Set<Product> product;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	

}
