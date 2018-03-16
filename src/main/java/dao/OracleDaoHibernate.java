package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import domain.Aanbieding;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Categorie;
import domain.Klant;
import domain.Product;

public class OracleDaoHibernate implements OracleDao {
	private static EntityManager em;

	public SessionFactory getFactory(){
		   try {
			StandardServiceRegistry standardRegistry = 
		       new StandardServiceRegistryBuilder().configure("/webshop/src/main/resources/META-INF/persistence.xml").build();
			Metadata metaData = 
		        new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			return sessionFactory;
		   } catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		  }
		
	}
	
	
	public EntityManagerFactory setUp() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
		return entityManagerFactory;
		
	}
	
	
	@Override
	public Klant getKlant(int id) {
		System.out.println(id + "derp");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Klant k = em.find(Klant.class, id);
		// System.out.println(b.getId() + "ID");
		em.getTransaction().commit();
		em.close();
		emf.close();
		return k;
	}

	@Override
	public Product getProduct(int id) {
		System.out.println(id + "derp");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Product p = em.find(Product.class, id);
		// System.out.println(b.getId() + "ID");
		em.getTransaction().commit();
		em.close();
		emf.close();
		return p;
	}

	@Override
	public Set<Product> getAllProducten() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Set<Product> producten = (Set<Product>)em.createQuery("SELECT * FROM Product").getResultList();

		return producten;
	}

	@Override
	public Set<Product> getAllProductenVanCategorie(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		// JsonArrayBuilder jab = Json.createArrayBuilder();

		// List<Product> producten;
		List<Categorie> categorien;

		Categorie cg = em.find(Categorie.class, id); // nodig?
		// producten = (List<Product>) em.find(Product.class, id);// moet ze
		// allemaal returnen

		int ids = id;
		Set<Product> producten = (Set<Product>)em.createQuery("SELECT p FROM Product p WHERE p.categorie IN :ids")
				.setParameter("ids", ids).getResultList();
		System.out.println(producten); // zou alle producten moeten returnen

		em.getTransaction().commit();
		em.close();
		emf.close();
		return producten;
	}

	@Override
	public Set<Bestelling> getAllBestellingenVanKlant(int accountId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		// JsonArrayBuilder jab = Json.createArrayBuilder();

		// List<Product> producten;
		// List<Categorie> categorien;

		Klant k = em.find(Klant.class, accountId); // nodig?
		// producten = (List<Product>) em.find(Product.class, id);// moet ze
		// allemaal returnen

		int ids = accountId; // WHERE p.account.ID?
		Set<Bestelling> bestellingen = (Set<Bestelling>)em.createQuery("SELECT p FROM Bestelling p WHERE p.account IN :ids")
				.setParameter("ids", ids).getResultList();
		System.out.println(bestellingen); // zou alle producten moeten returnen

		em.getTransaction().commit();
		em.close();
		emf.close();
		return bestellingen;

	}

	@Override
	public Bestelling createBestelling(Bestelling bst) {
		Bestelling best = bst;
		// add bestelling aan DB

		return best;
	}

	@Override
	public Set<Categorie> getAllCategorien() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Set<Categorie> categorien = (Set<Categorie>)em.createQuery("SELECT * FROM Categorie").getResultList();

		return categorien;
	}

	@Override
	public Set<Aanbieding> getAllAanbiedingen() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Set<Aanbieding> aanbiedingen = (Set<Aanbieding>)em.createQuery("SELECT * FROM Categorie").getResultList();

		return aanbiedingen;
	}

	@Override
	public Adres getAdres(int k_id) {
		System.out.println(k_id + "derp");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Adres a = em.find(Adres.class, k_id); // k_id is foreign key, vindt ie
												// dit?
		em.getTransaction().commit();
		em.close();
		emf.close();
		return a;
	}

	@Override
	public Account getAccount(int k_id) {
		System.out.println(k_id + "derp");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Account ac = em.find(Account.class, k_id);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return ac;
	}

	@Override
	public Product createProduct(Product pd) {
		Product prod = pd;
		// add product aan DB

		return pd;
	}

	@Override
	public Product updateProduct(Product id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product deleteProduct(Product id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Categorie getCategorie(int cat_id) {
		System.out.println(cat_id + "derp");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Categorie cat = em.find(Categorie.class, cat_id);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return cat;
	}

	@Override
	public Categorie createCategorie(Categorie catg) {
		//Categorie cat = catg;
		// add cat aan DB
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session =sessionFactory.openSession();
        session.beginTransaction();
        
        //Add new Employee object
        Categorie cat = catg;
         
        //Save the employee in database
        session.save(cat);
 
        //Commit the transaction
        session.flush();
        session.close();
        
        session.getTransaction().commit();
        
		return cat;
	}
	
	@Override
	public Categorie updateCategorie(Categorie cat_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie deleteCategorie(Categorie cat_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
