package restfull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.OracleDao;
import dao.OracleDaoHibernate;
import domain.Categorie;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Bestellingsregel;

@Path("/Bestellingen")
public class bestelling {
	OracleDao dao = new OracleDaoHibernate();

	// Requested functions in application/json:
	// getAllBestellings();
	// getBestelling(int id);
	// getBestellingsByCategory(int id);

	@GET
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getAllBestellingen() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Object[]> Bestellingen = dao.getAllBestellingen();
		JsonObjectBuilder job = Json.createObjectBuilder();
		for(Object[] pd : Bestellingen) {
			
			Number id = (Number) pd[0];
			String aflvradres = "" +pd[1]; 
			String acc = "" + pd[2];
			String adres = ""+ pd[3];
			String bestrgl = "" +pd[4];
		
			job.add("Bestelling_id", id.intValue());
			job.add("afleverAdres", aflvradres);
			job.add("Account", acc);
			job.add("Adres", adres);
			job.add("bestellingsRegel", bestrgl);
			jab.add(job);
		}

		return jab.build().toString();
	}

	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getBestelling(@PathParam ("id") int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		List<Object[]> list = new ArrayList<Object[]>();
		list.addAll(dao.getBestelling(id));
		
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println(list.size());
		System.out.println(list.get(0));
		
	Object o = list.get(0);
	Object[] pd = (Object[]) o;
	
	JsonObjectBuilder job = Json.createObjectBuilder();
	Number b_id = (Number) pd[0];
	String aflvradres = "" +pd[1]; 
	String acc = "" + pd[2];
	String adres = ""+ pd[3];
	String bestrgl = "" +pd[4];

	job.add("Bestelling_id", b_id.intValue());
	job.add("afleverAdres", aflvradres);
	job.add("Account", acc);
	job.add("Adres", adres);
	job.add("bestellingsRegel", bestrgl);
	jab.add(job);
		return jab.build().toString();
	}

	@POST
	@Path("{id}/{afleverAdres}/{account}/{adres}/{bestellingsRegel}")
	@Produces("application/json")
	public String createBestelling(@PathParam("id") int id, @PathParam("afleverAdres") String aa,
			@PathParam("account") Account acc, @PathParam("adres") Adres adr,
			@PathParam("bestellingsRegel") Set<Bestellingsregel> bstr) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Bestelling bst = new Bestelling();
		bst.setId(id); // Koen? cat id?
		bst.setAdres(adr);
		bst.setAfleverAdres(aa);
		bst.setBestellingsRegel(bstr);
		bst.setAccount(acc);

		dao.createBestelling(bst);

		// return JSON nog nodig?
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("Bestelling_id", id);
		job.add("afleverAdres", aa);
		job.add("Account", (JsonValue) acc);
		job.add("Adres", (JsonValue) adr);
		job.add("bestellingsRegel", (JsonValue) bstr);
		jab.add(job);

		return jab.build().toString();
	}

	@GET
	@Path("/bycat/{id}")
	@Produces("application/json")
	public String getBestellingenVanKlant(int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Object[]> Bestellingen;
		Bestellingen = dao.getAllBestellingenVanKlant(id);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Object[] pd : Bestellingen) {
			
			Number b_id = (Number) pd[0];
			String aflvradres = "" +pd[1]; 
			String acc = "" + pd[2];
			String adres = ""+ pd[3];
			String bestrgl = "" +pd[4];
		
			job.add("Bestelling_id", b_id.intValue());
			job.add("afleverAdres", aflvradres);
			job.add("Account", acc);
			job.add("Adres", adres);
			job.add("bestellingsRegel", bestrgl);
			jab.add(job);
		}

		return jab.build().toString();
	}

}