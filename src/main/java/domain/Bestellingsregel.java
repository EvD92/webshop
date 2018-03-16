package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BESTELLINGSREGEL")
public class Bestellingsregel {
	
	@Id
	@SequenceGenerator(name="my_seq", sequenceName="bestellingsregel_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	//@GeneratedValue(strategy=GenerationType.AUTO, generator="bestellingsregel_id_seq")
	@Column(name = "BESTELLINGSREGEL_ID")
	private int iD;
	
	@Column(name = "AANTAL")
	private int aantal;
	
	@Column(name = "PRIJS")
	private float prijs;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BESTELLING_ID")
	private Bestelling bestelling;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID_ID")
	private Product product;
	
	
	public int getId() {
		return iD;
	}
	public void setId(int iD) {
		this.iD = iD;
	}
	public int getAantal() {
		return aantal;
	}
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	public double getPrijs() {
		return prijs;
	}
	public void setPrijs(float prijs) {
		this.prijs = prijs;
	}
	public Bestelling getBestelling() {
		return bestelling;
	}
	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
