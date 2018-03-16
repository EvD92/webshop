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
@Table(name = "AANBIEDING")
public class Aanbieding {
	
	@Id
	@SequenceGenerator(name="my_seq", sequenceName="aanbieding_id_seq")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq")
	@Column(name = "AANBIEDING_ID")
	private int iD;
	
	@Column(name = "VAN_DATUM")
	private String vanDatum;
	
	@Column(name = "TOT_DATUM")
	private String totDatum;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	public int getId() {
		return iD;
	}
	public void setId(int iD) {
		this.iD = iD;
	}
	public String getVanDatum() {
		return vanDatum;
	}
	public void setVanDatum(String vanDatum) {
		this.vanDatum = vanDatum;
	}
	public String getTotDatum() {
		return totDatum;
	}
	public void setTotDatum(String totDatum) {
		this.totDatum = totDatum;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
