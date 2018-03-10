package domain;

public class Bestellingsregel {
	private int iD;
	private int aantal;
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
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
	public void setPrijs(double prijs) {
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
	private double prijs;
	private Bestelling bestelling;
	private Product product;

}
