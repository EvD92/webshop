package main;
import java.awt.Event;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
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
			
//		Categorie cat = new Categorie();
//			
//			cat.setId(2);// auto incr?
//			cat.setNaam("bijtjes");
//			cat.setOmschrijving("Voor al uw bijtjes");
//			
//			odao.createCategorie(cat);
			List<Object[]> categorien = odao.getAllCategorien();
			System.out.println(categorien);
//			for (Object[] c : categorien){
//				//int i = (Integer) object
//				Number id = (Number) c[0];
//				if (id.intValue() == 6){			//beetje uitproberen
//				System.out.println(c[0]);
//				System.out.println(c[1]);
//				System.out.println(c[2]);
//				c[0]=3;
//				System.out.println(c[0]);
//				}
//			}
			
			JsonArrayBuilder jab = Json.createArrayBuilder();
		      JsonObjectBuilder job = Json.createObjectBuilder();
		      for (Object[] cg : categorien) {
		    	  Number id = (Number) cg[0]; //Maak number van Object
		    	  String naam = "" + cg[1];
		    	  String oms = "" + cg[2];
		        job.add("id", id.intValue()); //krijg int van Number
		        job.add("naam", naam);
		        job.add("omschrijving", oms);
		        jab.add(job);
		      }

		    System.out.println( jab.build().toString());
			
			sessionFactory.close();
		
		
		/*em.getTransaction().begin();
		em.persist(cat );

		em.getTransaction().commit();
		em.close();*/
		
	}

}
