package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.soap.SOAPException;

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
import domain.Bestellingsregel;
import domain.Categorie;
import domain.Klant;
import domain.Product;
import restfull.SoapCaller;

public class OracleDaoHibernate implements OracleDao {
	private static EntityManager em;
	private static SessionFactory factory;
	
	public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }         
        return factory;
    }
//	
//	public SessionFactory getFactory(){
//		
//		try {				//Configuration
//	         factory = new Configuration().configure().buildSessionFactory();
//	      } catch (Throwable ex) { 
//	         System.err.println("Failed to create sessionFactory object." + ex);
//	         throw new ExceptionInInitializerError(ex); 
//	      }
//		
//		return factory;
//		/*	   try {
//			StandardServiceRegistry standardRegistry = 
//		       new StandardServiceRegistryBuilder().configure("/webshop/categorie.hbm.xmll").build();
//			Metadata metaData = 
//		        new MetadataSources(standardRegistry).getMetadataBuilder().build();
//			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
//			return sessionFactory;
//		   } catch (Throwable th) {
//			System.err.println("Enitial SessionFactory creation failed" + th);
//			throw new ExceptionInInitializerError(th);
//		  }*/
//		
//	}
//	
//	
//	public EntityManager setUp() {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "webshop" );
//		EntityManager em = entityManagerFactory.createEntityManager();
//		return em;
//		
//	}
	
	


	@Override
	public List<Object[]> getProduct(int prod_id) {
        //SessionFactory sessionFactory = factory.getSessionFactory().getI;
		//Configuration cf=new Configuration();
        //cf.configure();
        //factory = cf.buildSessionFactory();
		factory = getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(prod_id);
        Query query = session.createSQLQuery(
                "SELECT * FROM PRODUCT WHERE product_id = :sid");
                query.setParameter("sid", prod_id);
                //query.executeUpdate();
                List<Object[]> l = query.list();

               System.out.println("1 product: " + l.size());
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
       // factory.close();
        System.out.println(query);
        return l;
    }

	@Override
	public List<Object[]> getAllProducten() {

        factory = getSessionFactory();
       // System.out.println("hallow product");
       // Configuration cf=new Configuration();
        //cf.configure();
        //SessionFactory sessionFactory = cf.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
       // System.out.println(em.getTransaction());
        Query query = session.createSQLQuery(
                "SELECT * FROM Product");
        List<Object[]> producten = query.list();
        System.out.println("producten size: " + producten.size());
        
        //query.executeUpdate();
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        
        session.close();
        //factory.close();
        System.out.println(query);

        return producten;
    }

//	@Override
//	public Set<Product> getAllProductenVanCategorie(int id) {
//
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//
//		// JsonArrayBuilder jab = Json.createArrayBuilder();
//
//		// List<Product> producten;
//		List<Categorie> categorien;
//
//		Categorie cg = em.find(Categorie.class, id); // nodig?
//		// producten = (List<Product>) em.find(Product.class, id);// moet ze
//		// allemaal returnen
//
//		int ids = id;
//		Set<Product> producten = (Set<Product>)em.createQuery("SELECT p FROM Product p WHERE p.categorie IN :ids")
//				.setParameter("ids", ids).getResultList();
//		System.out.println(producten); // zou alle producten moeten returnen
//
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
//		return producten;
//	}

	@Override
	public int createBestelling(Bestelling bst, int prijs) throws SOAPException, Exception {
		SoapCaller sc = new SoapCaller();
		Bestelling best = bst;
		factory = getSessionFactory();
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createSQLQuery(
        		"insert into Bestelling (bestelling_id, afleveradres, account_id, betalingskenmerk) values( :sid, :saflevera, :saccount, :c)");

        		query.setParameter("saccount", bst.getAccount());
        		//System.out.println(bst.getAccount());
        		//query.setParameter("sadres", bst.getAdres().getStraat());
        		//System.out.println(bst.getAdres().getStraat().length());
        		System.out.println(bst.getAfleverAdres().length());
        		System.out.println(bst.getAfleverAdres());
        		query.setParameter("saflevera", bst.getAfleverAdres());
        		//query.setParameter("sbestellingrgl", bst.getBestellingsRegel());

        		query.setParameter("sid", bst.getId());
        		System.out.println(query.toString());
        		
		
        	    //soapcaller
        	    //call soapcaller en krijg C
        	    String prijsString = Integer.toString(prijs);
        	    //String naam = Integer.toString(bst.getAccount());
        	    
        	    		//System.out.println(bst.getAccount().getKlant().getNaam());
        	    
        	    String c = sc.createBetaling("naam", bst.getAfleverAdres(), prijsString);
        	    
        	    query.setParameter("c", c);
        	    
        	    best.setBetalingsKenmerk(c);
        	    
        	    query.executeUpdate();
        	    tx.commit();
        	    
        	    System.out.println("Betalingskenmerk set: "+c);
		// add bestelling aan DB

		return 200;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> getAllCategorien() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//		List<Categorie> categorien =em.createQuery("SELECT * FROM Categorie").getResultList();
		
		factory = getSessionFactory();
		//System.out.println("Categorie");
		//Configuration cf=new Configuration();
		//cf.configure();
		//SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
       // System.out.println(em.getTransaction());
        Query query = session.createSQLQuery(
        		"SELECT * FROM Categorie");
        List<Object[]> categorien = query.list();
        System.out.println("categorien size: " + categorien.size());
        //query.executeUpdate();
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        //factory.close();
        System.out.println(query);

		return categorien;
	}

	@Override
	public List<Object[]> getAllAanbiedingen() {
		//Configuration cf=new Configuration();
		//cf.configure();
		//SessionFactory sessionFactory = cf.buildSessionFactory();
		factory = getSessionFactory();
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
       // System.out.println(em.getTransaction());
        Query query = session.createSQLQuery(
        		"SELECT * FROM aanbieding");
        List<Object[]> aanbiedingen = query.list();
        System.out.println("aantal aanbiedingen: " + aanbiedingen.size());
        //query.executeUpdate();
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
       // factory.close();
        System.out.println(query);

		return aanbiedingen;
		
		
		
	}
//
//	@Override
//	public Adres getAdres(int k_id) {
//		System.out.println(k_id + "derp");
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//
//		Adres a = em.find(Adres.class, k_id); // k_id is foreign key, vindt ie
//												// dit?
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
//		return a;
//	}
//
//	@Override
//	public Account getAccount(int k_id) {
//		System.out.println(k_id + "derp");
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//
//		Account ac = em.find(Account.class, k_id);
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
//		return ac;
//	}

	@Override
	public Product createProduct(Product pd) {
       // factory = getFactory();
		// add pd aan DB
		//Configuration cf=new Configuration();
		//cf.configure();
		//SessionFactory sessionFactory = cf.buildSessionFactory();
		factory = getSessionFactory();
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createSQLQuery(
        		"insert into PRODUCT (NAAM, OMSCHRIJVING, PRIJS) values( :snaam, :somschrijving, :sprijs)");
        		//query.setParameter("sid", pd.getId());
        		//System.out.println(pd.getId());
        		query.setParameter("snaam", pd.getNaam());
        		System.out.println(pd.getNaam());
        		query.setParameter("somschrijving", pd.getOmschrijving());
        		query.setParameter("sprijs", pd.getPrijs());
        		//System.out.println(query.toString());
        		 System.out.println("execute numero uno"); 
        		query.executeUpdate();
        	    tx.commit(); 

        		System.out.println("eind query 1");
       
        		Query query2 = session.createSQLQuery(
                        "SELECT * FROM Product");
                List<Object[]> producten = query2.list();
                int counter = 0;
                for (Object[] pd2 : producten) {
                	
                	Number id = (Number) pd2[0];
        			String naam = "" + pd2[1];
        			String oms = "" + pd2[2];
        			Number prijs = (Number) pd2[3];
                	if (id.intValue() > counter) {
                		counter = id.intValue();
                	}
                	System.out.println(counter);
                }
                
        		
        System.out.println(pd.getNaam());
        Query queryCat = session.createSQLQuery("insert into CAT_PROD (CATEGORIE_ID, PRODUCT_ID) values(:scategorie, :ssid)");
		queryCat.setParameter("scategorie", 1);
		//System.out.println(pd.getId());
		queryCat.setParameter("ssid", counter);
		System.out.println("before execute..");	
		System.out.println(queryCat.toString());

		System.out.println("execute numero dos");
		tx = session.beginTransaction();
		queryCat.executeUpdate();
		
        


        
        
        //System.out.println("eind query 2");
        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
        //sessionFactory.close();
        System.out.println(query);
		return pd;
	}

//	@Override
//	public Product updateProduct(Product pd) {
//      //  factory = getFactory();
//		// update pd aan DB
//		Configuration cf=new Configuration();
//		cf.configure();
//		SessionFactory factory = cf.buildSessionFactory();
//		Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        System.out.println(pd.getNaam());
//        Query query = session.createSQLQuery(
//        		"UPDATE Product SET (naam = :snaam, id = :sid, omschrijving = :somschrijving, prijs = :sprijs, categorie = :scategorie) "
//        		+ "WHERE Product.id = :sid"); //zo iets
//        		query.setParameter("sid", pd.getId());
//        		query.setParameter("sprijs", pd.getPrijs());
//        		query.setParameter("scategorie", pd.getCategorie());
//        		query.setParameter("snaam", pd.getNaam());
//        		query.setParameter("somschrijving", pd.getOmschrijving());
//        		query.executeUpdate();
//
//        tx.commit();
//        System.out.println("COMMITTTEDEDEDE AF");
//        session.close();
//        System.out.println(query);
//		return pd;
//	}
//
//	@Override
//	public Product deleteProduct(Product id) {
//		//Delete pd van db
//       // factory = getFactory();
//		Configuration cf=new Configuration();
//		cf.configure();
//		SessionFactory factory = cf.buildSessionFactory();
//		Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        System.out.println(id);
//        Query query = session.createSQLQuery(
//        		"DELETE FROM Product WHERE id = :sid");
//        		query.setParameter("sid", id);
//        		query.executeUpdate();
//
//        tx.commit();
//        System.out.println("COMMITTTEDEDEDE AF");
//        session.close();
//        System.out.println(query);
//		return id;
//
//	}



	@Override
	public List<Object[]> getCategorie(int cat_id) {
		//Get cat van db

        factory = getSessionFactory();
		//Configuration cf=new Configuration();
		//cf.configure();
		//SessionFactory factory = cf.buildSessionFactory();
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(cat_id + " zoekt op deze ID in db");
        Query query = session.createSQLQuery(
        		"SELECT * FROM CATEGORIE WHERE categorie_id = :sid");
        		query.setParameter("sid", cat_id);
        		//query.executeUpdate();
        		List<Object[]> l = query.list();
        		System.out.println("1 categorie: " + l.size());

        tx.commit();
        System.out.println("COMMITTTEDEDEDE AF");
        session.close();
       // factory.close();
        System.out.println(query);
		return l;

		
	}
//
//	@Override
//	public Categorie createCategorie(Categorie catg) {
//		// add cat aan DB
//       // factory = getFactory();
//		Configuration cf=new Configuration();
//		cf.configure();
//		SessionFactory factory = cf.buildSessionFactory();
//		Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        System.out.println(catg.getNaam());
//        Query query = session.createSQLQuery(
//        		"insert into categorie VALUES (:sid, :somschrijving, :snaam)");
//        
//        query.setParameter("sid", catg.getId());
//        query.setParameter("somschrijving", catg.getOmschrijving());
//        query.setParameter("snaam", catg.getNaam());
//
//        query.executeUpdate();
//        tx.commit();
//        System.out.println("COMMITTTEDEDEDE AF");
//        session.close();
//        System.out.println(query);
//		return catg;
//	}
//	
//	@Override
//	public Categorie updateCategorie(Categorie catg) {
//		// update cat aan DB
//      //  factory = getFactory();
//				Configuration cf=new Configuration();
//				cf.configure();
//				SessionFactory factory = cf.buildSessionFactory();
//				Session session = factory.openSession();
//		        Transaction tx = session.beginTransaction();
//
//		        System.out.println(catg.getNaam());
//		        Query query = session.createSQLQuery(
//		        		"UPDATE Categorie SET (naam = :snaam, id = :sid, omschrijving = :somschrijving)"
//		        		+ " WHERE Categorie.id = catg.id;"); //zo iets
//		        query.setParameter("sid", catg.getId());
//		        query.setParameter("snaam", catg.getNaam());
//		        query.setParameter("somschrijving", catg.getOmschrijving());
//		        query.executeUpdate();
//
//		        tx.commit();
//		        System.out.println("COMMITTTEDEDEDE AF");
//		        session.close();
//		        System.out.println(query);
//				return catg;
//	}
//
//	@Override
//	public Categorie deleteCategorie(Categorie cat_id) {
//		//Delete cat van db
//       // factory = getFactory();
//		Configuration cf=new Configuration();
//		cf.configure();
//		SessionFactory factory = cf.buildSessionFactory();
//		Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        System.out.println(cat_id);
//        Query query = session.createSQLQuery(
//        		"DELETE FROM Categorie WHERE id = :sid");
//        		query.setParameter("sid", cat_id);
//        		query.executeUpdate();
//
//        tx.commit();
//        System.out.println("COMMITTTEDEDEDE AF");
//        session.close();
//        System.out.println(query);
//		return cat_id;
//	}
//
//
//	@Override
//	public Product deleteProduct(int code) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public List<Object[]> getAllBestellingenVanKlant(int id) {
//		return null;
//
////		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
////		em = emf.createEntityManager();
////		em.getTransaction().begin();
////
////		// JsonArrayBuilder jab = Json.createArrayBuilder();
////
////		// List<Product> producten;
////		// List<Categorie> categorien;
////
////		Klant k = em.find(Klant.class, accountId); // nodig?
////		// producten = (List<Product>) em.find(Product.class, id);// moet ze
////		// allemaal returnen
////
////		int ids = accountId; // WHERE p.account.ID?
////		Set<Bestelling> bestellingen = (Set<Bestelling>)em.createQuery("SELECT p FROM Bestelling p WHERE p.account IN :ids")
////				.setParameter("ids", ids).getResultList();
////		System.out.println(bestellingen); // zou alle producten moeten returnen
////
////		em.getTransaction().commit();
////		em.close();
////		emf.close();
////		return bestellingen;
//
//	}
//
//
//	@Override
//	public List<Object[]> getAllBestellingen() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public List<Object[]> getBestelling(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public void createKlant(Klant bst) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public List getKlant(int id) {
//		return null;
////		System.out.println(id + "derp");
////		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
////		em = emf.createEntityManager();
////		em.getTransaction().begin();
////
////		Klant k = em.find(Klant.class, id);
////		// System.out.println(b.getId() + "ID");
////		em.getTransaction().commit();
////		em.close();
////		emf.close();
////		return k;
//	}
//
//
//	@Override
//	public List<Object[]> getAllKlanten() {
//		// TODO Auto-generated method stub
//		return null;
//	}




	@Override
	public String doSoapCall() {
		
		
		return null;
	}
}
