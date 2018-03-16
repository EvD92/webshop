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
import domain.Aanbieding;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Bestellingsregel;
import domain.Categorie;
import domain.Product;

@Path("/products")
public class product {
	OracleDao dao;
	ResponseBuilder rb = null;

	// Requested functions in application/json:
	// getAllProducts();
	// getProduct(int id);
	// getProductsByCategory(int id);
	
	//CRUD product		eis
	//public Product createProduct(int id);
	//public Product getProduct(int id);
	//public Product updateProduct(int id);
	//public Product deleteProduct(int id);

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
	public String getAllProducten() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Product> producten;
		producten = dao.getAllProducten();
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Product pd : producten) {
			job.add("product_id", pd.getiD());
			job.add("naam", pd.getNaam());
			job.add("omschrijving", pd.getOmschrijving());
			job.add("prijs", pd.getPrijs());
			job.add("aanbieding", (JsonValue) pd.getAanbieding());
			job.add("bestellingsRegel", (JsonValue) pd.getBestellingsregel());
			job.add("categorie", (JsonValue) pd.getCategorie());
			jab.add(job);
		}

		return jab.build().toString();
	}
	//Crud
	

	@POST
	@Produces("application/json")
	public String createProduct(@FormParam("p_id") int p_id, @FormParam("c_id") int c_id,
			@FormParam("naam") String naam, @FormParam("prijs") int prijs,
			@FormParam("omschrijving") String oms) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Product pd = new Product();
		
		pd.setiD(p_id);;
		pd.setCategorie(c_id); //cat id?
		pd.setNaam(naam);
		pd.setOmschrijving(oms);
		
		dao.createProduct(pd);
		
		//return JSON nog nodig?
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("product_id", pd.getiD());
		job.add("categorie_id", pd.getCategorie());
		job.add("naam",  pd.getNaam());
		job.add("oms", pd.getOmschrijving());
		jab.add(job);

		return jab.build().toString();
	}
	
	
	//cRud
	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getProduct(int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Product pd = dao.getProduct(id);
		List<Aanbieding> aanbiedingen = dao.getAllAanbiedingen();
		

		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("product_id", pd.getiD());
		job.add("naam", pd.getNaam());
		job.add("omschrijving", pd.getOmschrijving());
		job.add("prijs", pd.getPrijs());
		job.add("aanbieding", (JsonValue) pd.getAanbieding());
		job.add("bestellingsRegel", (JsonValue) pd.getBestellingsregel());
		job.add("categorie", (JsonValue) pd.getCategorie());

		for (Aanbieding ab : aanbiedingen) {
			if (ab.getProduct().getiD() == pd.getiD()) { // als aanbieding.product_id = pd.id
				job.add("aanbiedingId", ab.getiD());
				job.add("totDatum", ab.getTotDatum());
				job.add("vanDatum", ab.getVanDatum());
				
			}
		}
		jab.add(job);

		return jab.build().toString();
	}

	@GET
	@Path("/bycat/{id}")
	@Produces("application/json")
	public String getProductenVanCategorie(int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Product> producten;
		producten = dao.getAllProductenVanCategorie(id);
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Product pd : producten) {
			job.add("product_id", pd.getiD());
			job.add("naam", pd.getNaam());
			job.add("omschrijving", pd.getOmschrijving());
			job.add("prijs", pd.getPrijs());
			job.add("aanbieding", (JsonValue) pd.getAanbieding());
			job.add("bestellingsRegel", (JsonValue) pd.getBestellingsregel());
			job.add("categorie", (JsonValue) pd.getCategorie());
			jab.add(job);
		}

		return jab.build().toString();
	}

}