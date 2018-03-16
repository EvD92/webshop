package main;

import java.awt.Event;

import javax.persistence.EntityManager;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
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
		
		//final EntityManager em = odao.setUp();

	
		

		//odao.getAllProductenVanCategorie(1); //tryout


//	
//		long id1 = (Long) session.save(cat);
//		System.out.println("2. Employee save called with transaction, id="+id1);
//		System.out.println("3. Before committing save transaction");
//		tx1.commit();
//		System.out.println("4. After committing save transaction");
//		System.out.println("*****");
		

	        // tx1 = session.beginTransaction();
	         //Employee employee = new Employee(fname, lname, salary);
	        // employeeID = (Integer) session.save(employee); 
			//odao.getKlant(1);
			//odao.getAllProductenVanCategorie(1); //tryout
			Categorie cat = new Categorie();
			
			cat.setId(2);// auto incr?
			cat.setNaam("bijtjes");
			cat.setOmschrijving("Voor al uw bijtjes");
			
			odao.createCategorie(cat);
			sessionFactory.close();
		
		
		/*em.getTransaction().begin();
		em.persist(cat );

		em.getTransaction().commit();
		em.close();*/
		
	}

}
