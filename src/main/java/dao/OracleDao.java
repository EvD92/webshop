package dao;

import java.util.Collection;
import java.util.List;
import javax.xml.soap.SOAPException;

import domain.Bestelling;
import domain.Product;

public interface OracleDao {
	
	//Factory
	
	//getX
	//public Adres getAdres(int k_id);
	//public Account getAccount(int k_id);
	
	
	//getAllX
	public List<Object[]> getAllCategorien();
	
	public List<Object[]> getAllProducten();
	
	public List<Object[]> getAllAanbiedingen();

	//getAllXvanB
	//public Set<Product> getAllProductenVanCategorie(int i);

	//public List<Object[]> getAllBestellingenVanKlant(int id);
	
	//CRUD product		eis
	public Product createProduct(Product pd);
	public List<Object[]> getProduct(int id);
	public Product updateProduct(Product pd);
	public String deleteProduct(int code);
	
	//CRUD categorie    eis
	//public Categorie createCategorie(Categorie cat);
	public List<Object[]> getCategorie(int cat_id);
	//public Categorie updateCategorie(Categorie catg);
	//public Categorie deleteCategorie(Categorie catg);

	public int createBestelling(Bestelling bst, int prijs) throws SOAPException, Exception;

	public List<Object[]> getAanbieding(int id);

	public List<Object[]> getKlant(String email);
	
	//createX
	//public Bestelling createBestelling(Bestelling best);
	//Product deleteProduct(Product id);
	//public List<Object[]> getAllBestellingen();
	
	//public List<Object[]> getBestelling(int id);
	//public void createKlant(Klant bst);
	//public List<Object[]> getKlant(int id);
	//public List<Object[]> getAllKlanten();



}