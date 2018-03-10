package domain;

import org.hibernate.annotations.*;

@Entity
@Table(name = "AANBIEDING")
public class Aanbieding {
	
	@Id
	@Column(name = "ID")
	private int iD;
	private String vanDatum;
	private String totDatum;
	private Product product;
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
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
