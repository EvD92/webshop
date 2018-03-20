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
import javax.ws.rs.FormParam;
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
import domain.Klant;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Bestellingsregel;

@Path("/klanten")
public class klant {
	OracleDao dao = new OracleDaoHibernate();

	// Requested functions in application/json:
	// getAllBestellings();
	// getBestelling(int id);
	// getBestellingsByCategory(int id);
	
	@GET
	public String getKlant() {
		return "";
	}
//	@GET
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String getAllKlanten() {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		List<Object[]> klanten = dao.getAllKlanten();
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		for(Object[] pd : klanten) { 
//			//id, afb, naam, woonadres		//int, int, string, string
//			
//			Number id = (Number) pd[0];
//			String naam = "" +pd[1]; 
//			String woonadres = "" + pd[2];
//			Number afb = (Number) pd[2];
//		
//			job.add("Bestelling_id", id.intValue());
//			job.add("Naam", naam);
//			job.add("Woonadres", woonadres);
//			job.add("Afbeelding", afb.intValue());
//			jab.add(job);
//		}
//
//		return jab.build().toString();
//	}

//	@GET
//	@Path("{id}")
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String getKlant(@PathParam ("id") int id) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		
//		List<Object[]> list = new ArrayList<Object[]>();
//		list.addAll(dao.getKlant(id)); //getKlant bestaat nog niet
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

	
//	//id, afb, naam, woonadres		//int, int, string, string
//	@POST
//	@Path("{afb}/{naam}/{straatnaam}/{straatnummer}/{account}")
//	@Produces("application/json")
//	public String createKlant( @FormParam("afb") int afb,
//			@FormParam("naam") String naam, @FormParam("straatnaam") String straatnaam,
//			@FormParam("straatnummer") int straatnummer, @FormParam("account") int acc) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		Klant bst = new Klant();
//		//bst.setid(id); // Koen? cat id?
//		bst.setNaam(naam);
//		Adres adres = new Adres();
//		adres.setStraat(straatnaam);
//		adres.setStraatNummer(straatnummer);;
//		bst.setAdres(adres);
//		bst.setAfbeelding(afb);
//		//Account account = new Account();
//		//account.setAdres(adres);
//		//account.setKlant(bst);
//		bst.setAccount(dao.getAccount(acc));
//
//		dao.createKlant(bst);
//
//		// return JSON nog nodig?
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		//job.add("Klant_id", id);
//		job.add("naam", naam);
//		job.add("Account", acc);
//		job.add("Adres",  adres.getId());
//		job.add("afbeelding", afb);
//		jab.add(job);
//
//		return jab.build().toString();
//	}

//	@GET
//	@Path("klant/{id}")
//	@Produces("application/json")
//	public String getBestellingenVanKlant(@PathParam("id")int id) {
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

}