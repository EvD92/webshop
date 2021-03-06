package restfull;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.soap.SOAPException;

import dao.OracleDao;
import dao.OracleDaoHibernate;
import domain.Account;
import domain.Adres;
import domain.Bestelling;

@Path("/bestellingen")
public class bestelling {
	OracleDao dao = new OracleDaoHibernate();
	
	// Requested functions in application/json:
	// getAllBestellings();
	// getBestelling(int id);
	// getBestellingsByCategory(int id);

	@GET
	public String getBestelling() {
		return"";
	}


	@GET
	@Path("{id}/{afleverAdres}/{account}/{prijs}")
	@Produces("application/json")
	public String createBestelling(@PathParam("id") int id, @PathParam("afleverAdres") String aa,
			@PathParam("account") int acc,
			@PathParam("prijs") int  prijs) throws SOAPException {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Bestelling bst = new Bestelling();
		Adres adres = new Adres();
		
		adres.setStraat(aa);
		bst.setId(id); // Koen? cat id?
		//bst.setAdres(adres);
		System.out.println(adres.getStraat());
		bst.setAfleverAdres(adres.getStraat());
		//bst.setBestellingsRegel(bstr);
		
		Account account = new Account();
		account.setId(acc);
		bst.setAccount(account);

		try {
			dao.createBestelling(bst, prijs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return JSON nog nodig?
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("Bestelling_id", id);
		job.add("afleverAdres", aa);
		job.add("Account",  acc);
		jab.add(job);

		return jab.build().toString();
	}

//	@GET
//	@Path("/catby/{id}")
//	@Produces("application/json")
//	public String getBestellingenVanKlant(@PathParam ("id") int id) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		List<Object[]> Bestellingen;
//		Bestellingen = dao.getAllBestellingenVanKlant(id);
//		
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		for (Object[] pd : Bestellingen) {
//			
//			Number b_id = (Number) pd[0];
//			String aflvradres = "" +pd[1]; 
//			String acc = "" + pd[2];
//			String adres = ""+ pd[3];
//			String bestrgl = "" +pd[4];
//		
//			job.add("Bestelling_id", b_id.intValue());
//			job.add("afleverAdres", aflvradres);
//			job.add("Account", acc);
//			job.add("Adres", adres);
//			job.add("bestellingsRegel", bestrgl);
//			jab.add(job);
//		}
//
//		return jab.build().toString();
//	}
//	@GET
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String getAllBestellingen() {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		List<Object[]> Bestellingen = dao.getAllBestellingen();
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		for(Object[] pd : Bestellingen) {
//			
//			Number id = (Number) pd[0];
//			String aflvradres = "" +pd[1]; 
//			String acc = "" + pd[2];
//			String adres = ""+ pd[3];
//			String bestrgl = "" +pd[4];
//		
//			job.add("Bestelling_id", id.intValue());
//			job.add("afleverAdres", aflvradres);
//			job.add("Account", acc);
//			job.add("Adres", adres);
//			job.add("bestellingsRegel", bestrgl);
//			jab.add(job);
//		}
//
//		return jab.build().toString();
//	}

//	@GET
//	@Path("{id}")
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String getBestelling(@PathParam ("id") int id) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		
//		List<Object[]> list = new ArrayList<Object[]>();
//		list.addAll(dao.getBestelling(id));
//		
//		System.out.println("----------------------------------------------------------------------------------------------------------");
//		System.out.println(list.size());
//		System.out.println(list.get(0));
//		
//	Object o = list.get(0);
//	Object[] pd = (Object[]) o;
//	
//	JsonObjectBuilder job = Json.createObjectBuilder();
//	Number b_id = (Number) pd[0];
//	String aflvradres = "" +pd[1]; 
//	String acc = "" + pd[2];
//	String adres = ""+ pd[3];
//	String bestrgl = "" +pd[4];
//
//	job.add("Bestelling_id", b_id.intValue());
//	job.add("afleverAdres", aflvradres);
//	job.add("Account", acc);
//	job.add("Adres", adres);
//	job.add("bestellingsRegel", bestrgl);
//	jab.add(job);
//		return jab.build().toString();
//	}
}