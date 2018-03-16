package restfull;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.OracleDao;
import domain.Categorie;
import nl.hu.v1wac.secondapp.webservices.Country;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Bestellingsregel;

@Path("/Bestellings")
public class bestelling {
	OracleDao dao;
	ResponseBuilder rb = null;

	// Requested functions in application/json:
	// getAllBestellings();
	// getBestelling(int id);
	// getBestellingsByCategory(int id);

	@GET
	@RolesAllowed("guest")
	@Produces("text/html")
	public Response testing() {
		String s = "t werkte joepie";
		rb = Response.status(200).entity(s);
		return rb.build();
	}

	@GET
	@Path("/all")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getAllBestellingen() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Bestelling> Bestellingen;
		Bestellingen = dao.getAllBestellingen();
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Bestelling pd : Bestellingen) {
			job.add("Bestelling_id", pd.getiD());
			job.add("naam", pd.getAfleverAdres());
			job.add("omschrijving", (JsonValue) pd.getAccount());
			job.add("prijs", (JsonValue) pd.getAdres());
			job.add("bestellingsRegel", (JsonValue) pd.getBestellingsRegel());
			jab.add(job);
		}

		return jab.build().toString();
	}

	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getBestelling(int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Bestelling pd = dao.getBestelling(id);
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("Bestelling_id", pd.getiD());
		job.add("naam", pd.getAfleverAdres());
		job.add("omschrijving", (JsonValue) pd.getAccount());
		job.add("prijs", (JsonValue) pd.getAdres());
		job.add("bestellingsRegel", (JsonValue) pd.getBestellingsRegel());
		jab.add(job);

		return jab.build().toString();
	}

	@POST
	@Produces("application/json")
	public String createBestelling(@FormParam("id") int id, @FormParam("adleverAdres") String aa,
			@FormParam("account") Account acc, @FormParam("adres") Adres adr,
			@FormParam("bestellingsRegel") Set<Bestellingsregel> bstr) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Bestelling pd = new Bestelling();
		
		pd.setAccount(acc);
		pd.setAdres(adr);
		pd.setAfleverAdres(aa);
		pd.setBestellingsRegel(bstr);
		pd.setiD(id);
		
		dao.createBestelling(pd);
		
		//return JSON nog nodig?
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("Bestelling_id", pd.getiD());
		job.add("afleverAdres", pd.getAfleverAdres());
		job.add("account", (JsonValue) pd.getAccount());
		job.add("adres", (JsonValue) pd.getAdres());

		// for each bestellingsRegel?
		job.add("bestellingsRegel", (JsonValue) pd.getBestellingsRegel());
		jab.add(job);

		return jab.build().toString();
	}

	@GET
	@Path("/bycat/{id}")
	@Produces("application/json")
	public String getBestellingenVanKlant(int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Bestelling> Bestellingen;
		Bestellingen = dao.getAllBestellingenVanCategorie(id);
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Bestelling pd : Bestellingen) {
			job.add("Bestelling_id", pd.getiD());
			job.add("naam", pd.getAfleverAdres());
			job.add("omschrijving", (JsonValue) pd.getAccount());
			job.add("prijs", (JsonValue) pd.getAdres());
			job.add("bestellingsRegel", (JsonValue) pd.getBestellingsRegel());
			jab.add(job);
		}

		return jab.build().toString();
	}

}