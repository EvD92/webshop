package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Cat_prod")
@Table(name = "CAT_PROD")
public class Cat_prod {
	
	@Id
	@Column(name = "CATEGORIE_ID")
	private int categorie_id;
	
	public int getCategorie_id() {
		return categorie_id;
	}

	public void setCategorie_id(int categorie_id) {
		this.categorie_id = categorie_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Column(name = "PRODUCT_ID")
	private int product_id;
}
