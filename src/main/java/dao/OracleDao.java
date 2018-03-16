package dao;

import java.util.List;
import java.util.Set;

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
	public Set<Categorie> getAllCategorien();
	
	public Set<Product> getAllProducten();
	
	public Set<Aanbieding> getAllAanbiedingen();

	//getAllXvanB
	public Set<Product> getAllProductenVanCategorie(int i);

	public Set<Bestelling> getAllBestellingenVanKlant(int id);
	
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