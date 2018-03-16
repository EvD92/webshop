package dao;

import java.util.List;

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
	public EntityManagerFactory setUp();
	
	//getX
	public Klant getKlant(int id);
	public Adres getAdres(int k_id);
	public Account getAccount(int k_id);
	
	
	//getAllX
	public List<Categorie> getAllCategorien();
	
	public List<Product> getAllProducten();
	
	public List<Aanbieding> getAllAanbiedingen();

	//getAllXvanB
	public List<Product> getAllProductenVanCategorie(int i);

	public List<Bestelling> getAllBestellingenVanKlant(int id);
	
	//CRUD product		eis
	public Product createProduct(Product pd);
	public Product getProduct(int id);
	public Product updateProduct(Product id);
	public Product deleteProduct(Product id);
	
	//CRUD categorie    eis
	public Categorie createCategorie(Categorie cat);
	public Categorie getCategorie(int cat_id);
	public Categorie updateCategorie(Categorie cat_id);
	public Categorie deleteCategorie(Categorie cat_id);
	
	//createX
	public Bestelling createBestelling(Bestelling best);



}