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
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fasterxml.classmate.AnnotationConfiguration;

import domain.Aanbieding;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Categorie;
import domain.Klant;
import domain.Product;

public class OracleDaoHibernate implements OracleDao {
	private static EntityManager em;
	private static SessionFactory factory; 
	
	public SessionFactory getFactory(){
		
		try {				//Configuration
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		return factory;
		/*	   try {
			StandardServiceRegistry standardRegistry = 
		       new StandardServiceRegistryBuilder().configure("/webshop/categorie.hbm.xmll").build();
			Metadata metaData = 
		        new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			return sessionFactory;
		   } catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		  }*/
		
	}
	
	
	public EntityManager setUp() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "webshop" );
		EntityManager em = entityManagerFactory.createEntityManager();
		return em;
		
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

		factory = getFactory();
		System.out.println("hallow product");
		Configuration cf=new Configuration();
		cf.configure();
		//SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
       // System.out.println(em.getTransaction());
        Query query = session.createSQLQuery(
        		"SELECT * FROM Product");
        Set<Product> producten = (Set<Product>) query.list();

        //query.executeUpdate();
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        factory.close();
        System.out.println(query);

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

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> getAllCategorien() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//		List<Categorie> categorien =em.createQuery("SELECT * FROM Categorie").getResultList();
		
		factory = getFactory();
		System.out.println("hallow");
		Configuration cf=new Configuration();
		cf.configure();
		//SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
       // System.out.println(em.getTransaction());
        Query query = session.createSQLQuery(
        		"SELECT * FROM Categorie");
        List<Object[]> categorien = query.list();

        //query.executeUpdate();
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        factory.close();
        System.out.println(query);

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
		// add pd aan DB
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(pd.getNaam());
        Query query = session.createSQLQuery(
        		"insert into Product values(:sid, :snaam, :somschrijving, :sprijs, :scategorie)");
        		query.setParameter("sid", pd.getId());
        		query.setParameter("sprijs", pd.getPrijs());
        		query.setParameter("scategorie", pd.getCategorie());
        		query.setParameter("snaam", pd.getNaam());
        		query.setParameter("somschrijving", pd.getOmschrijving());
        		query.executeUpdate();
        
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        sessionFactory.close();
        System.out.println(query);
		return pd;
	}

	@Override
	public Product updateProduct(Product pd) {
		// update pd aan DB
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(pd.getNaam());
        Query query = session.createSQLQuery(
        		"UPDATE Product SET (naam = :snaam, id = :sid, omschrijving = :somschrijving, prijs = :sprijs, categorie = :scategorie) "
        		+ "WHERE Product.id = :sid"); //zo iets
        		query.setParameter("sid", pd.getId());
        		query.setParameter("sprijs", pd.getPrijs());
        		query.setParameter("scategorie", pd.getCategorie());
        		query.setParameter("snaam", pd.getNaam());
        		query.setParameter("somschrijving", pd.getOmschrijving());
        		query.executeUpdate();

        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        sessionFactory.close();
        System.out.println(query);
		return pd;
	}

	@Override
	public Product deleteProduct(Product id) {
		//Delete pd van db
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(id);
        Query query = session.createSQLQuery(
        		"DELETE FROM Product WHERE id = :sid");
        		query.setParameter("sid", id);
        		query.executeUpdate();

        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        sessionFactory.close();
        System.out.println(query);
		return id;

	}



	/*@Override
	public List getCategorie(int cat_id) {
		//Get cat van db
//		System.out.println(cat_id + "derp");
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//																					WRM WERKT OUDE CODE NIET MEER?
//		Categorie cat = em.find(Categorie.class, cat_id);
//		System.out.println(cat.getNaam());
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
//		return cat;
		
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(cat_id);
        Query query = session.createSQLQuery(
        		"SELECT * FROM CATEGORIE WHERE categorie_id = :sid");
        		query.setParameter("sid", cat_id);
        		//query.executeUpdate();
        		List l = query.list();
        		

        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        sessionFactory.close();
        System.out.println(query);
		return l;

		
	}*/

	@Override
	public Categorie createCategorie(Categorie catg) {
		// add cat aan DB
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(catg.getNaam());
        Query query = session.createSQLQuery(
        		"insert into categorie VALUES (:sid, :somschrijving, :snaam)");
        
        query.setParameter("sid", catg.getId());
        query.setParameter("somschrijving", catg.getOmschrijving());
        query.setParameter("snaam", catg.getNaam());

        query.executeUpdate();
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        sessionFactory.close();
        System.out.println(query);
		return catg;
	}
	
	@Override
	public Categorie updateCategorie(Categorie catg) {
		// update cat aan DB
				Configuration cf=new Configuration();
				cf.configure();
				SessionFactory sessionFactory = cf.buildSessionFactory();
				Session session = sessionFactory.openSession();
		        Transaction tx = session.beginTransaction();

		        System.out.println(catg.getNaam());
		        Query query = session.createSQLQuery(
		        		"UPDATE Categorie SET (naam = :snaam, id = :sid, omschrijving = :somschrijving)"
		        		+ " WHERE Categorie.id = catg.id;"); //zo iets
		        query.setParameter("sid", catg.getId());
		        query.setParameter("snaam", catg.getNaam());
		        query.setParameter("somschrijving", catg.getOmschrijving());
		        query.executeUpdate();

		        tx.commit();
		        System.out.println("COMMITTTEDEDEDE AF");
		        session.close();
		        sessionFactory.close();
		        System.out.println(query);
				return catg;
	}

	@Override
	public Categorie deleteCategorie(Categorie cat_id) {
		//Delete cat van db
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(cat_id);
        Query query = session.createSQLQuery(
        		"DELETE FROM Categorie WHERE id = :sid");
        		query.setParameter("sid", cat_id);
        		query.executeUpdate();

        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        sessionFactory.close();
        System.out.println(query);
		return cat_id;
	}


	@Override
	public Product deleteProduct(int code) {
		// TODO Auto-generated method stub
		return null;
	}
}
