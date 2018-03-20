package dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;

import domain.Aanbieding;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Categorie;
import domain.Klant;
import domain.Product;

public interface OracleDao {
	
	//Factory
//	public SessionFactory getFactory();
	//public EntityManager setUp();
	
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
	//public Product updateProduct(Product pd);
	//public Product deleteProduct(int code);
	
	//CRUD categorie    eis
	//public Categorie createCategorie(Categorie cat);
	public List<Object[]> getCategorie(int cat_id);
	//public Categorie updateCategorie(Categorie catg);
	//public Categorie deleteCategorie(Categorie catg);
	
	//createX
	//public Bestelling createBestelling(Bestelling best);
	//Product deleteProduct(Product id);
	//public List<Object[]> getAllBestellingen();
	
	//public List<Object[]> getBestelling(int id);
	//public void createKlant(Klant bst);
	//public List<Object[]> getKlant(int id);
	//public List<Object[]> getAllKlanten();



}