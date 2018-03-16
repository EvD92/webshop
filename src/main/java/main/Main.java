package main;

import java.awt.Event;

import javax.persistence.EntityManager;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.OracleDao;
import dao.OracleDaoHibernate;
import domain.Categorie;

public class Main {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		OracleDao odao = new OracleDaoHibernate();
		SessionFactory sessionFactory = odao.getFactory();
		
		final EntityManager em = (EntityManager) odao.setUp();
		
		Session session = sessionFactory.openSession();
		Transaction tx1 = session.beginTransaction();
		
		
		odao.getKlant(1);
		//odao.getAllProductenVanCategorie(1); //tryout
		Categorie cat = new Categorie();
		
		cat.setId(6);
		cat.setNaam("Bloemetjes");
		cat.setOmschrijving("Voor al uw bloemen");
		odao.createCategorie(cat);

//	
//		long id1 = (Long) session.save(cat);
//		System.out.println("2. Employee save called with transaction, id="+id1);
//		System.out.println("3. Before committing save transaction");
//		tx1.commit();
//		System.out.println("4. After committing save transaction");
//		System.out.println("*****");
		
		em.getTransaction().begin();
		em.persist(cat );

		em.getTransaction().commit();
		em.close();
		
	}

}
