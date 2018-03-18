package dao;

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
	public SessionFactory getFactory();
	public EntityManager setUp();
	
	//getX
	public Klant getKlant(int id);
	public Adres getAdres(int k_id);
	public Account getAccount(int k_id);
	
	
	//getAllX
	public List<Object[]> getAllCategorien();
	
	public Set<Product> getAllProducten();
	
	public Set<Aanbieding> getAllAanbiedingen();

	//getAllXvanB
	public Set<Product> getAllProductenVanCategorie(int i);

	public Set<Bestelling> getAllBestellingenVanKlant(int id);
	
	//CRUD product		eis
	public Product createProduct(Product pd);
	public Product getProduct(int id);
	public Product updateProduct(Product pd);
	public Product deleteProduct(int code);
	
	//CRUD categorie    eis
	public Categorie createCategorie(Categorie cat);
	public List getCategorie(int cat_id);
	public Categorie updateCategorie(Categorie catg);
	public Categorie deleteCategorie(Categorie catg);
	
	//createX
	public Bestelling createBestelling(Bestelling best);
	Product deleteProduct(Product id);



}