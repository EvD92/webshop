package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Klant;

public class OracleDaoHibernate implements OracleDao {
	private static EntityManager em;

	@Override
	public Klant getKlant(int id) {
		System.out.println(id + "derp");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Klant k = em.find(Klant.class, id);
//		System.out.println(b.getId() + "ID");
		em.getTransaction().commit();
		em.close();
		emf.close();
		return k;
	}

}
