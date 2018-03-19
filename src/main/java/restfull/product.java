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
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.OracleDao;
import dao.OracleDaoHibernate;
import domain.Aanbieding;
import domain.Account;
import domain.Adres;
import domain.Bestelling;
import domain.Bestellingsregel;
import domain.Categorie;
import domain.Product;

@Path("/products")
public class product {
	OracleDao dao = new OracleDaoHibernate();
	ResponseBuilder rb = null;

	// Requested functions in application/json:
	// getAllProducts();
	// getProduct(int id);
	// getProductsByCategory(int id);

	// CRUD product eis
	// public Product createProduct(int id);
	// public Product getProduct(int id);
	// public Product updateProduct(int id);
	// public Product deleteProduct(int id);

	@GET
//	@Path("/all")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getAllProducten() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Object[]> producten;
		producten = dao.getAllProducten();
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Object[] pd : producten) {
			Number id = (Number) pd[0];
			String naam = "" + pd[1];
			String oms = "" + pd[2];
			Number prijs = (Number) pd[3];
			String aanbieding = "" +pd[4];
			String bestellingsRegel = "" + pd[5];
			String categorie = "" + pd[6];
			
			
			job.add("product_id", id.intValue());
			job.add("naam", naam);
			job.add("omschrijving", oms);
			job.add("prijs", prijs.intValue());
			job.add("aanbieding", aanbieding);
			job.add("bestellingsRegel", bestellingsRegel);
			job.add("categorie", categorie);
	        
			jab.add(job);
		}

		return jab.build().toString();
	}
//
//	// Crud
//	@POST
//	@Produces("application/json")
//	public String createProduct(@FormParam("p_id") int p_id, @FormParam("c_id") int c_id,
//			@FormParam("naam") String naam, @FormParam("prijs") int prijs, @FormParam("omschrijving") String oms) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		Product pd = new Product();
//
//		pd.setId(p_id);
//		pd.setCategorie(c_id); // Koen? cat id?
//		pd.setNaam(naam);
//		pd.setOmschrijving(oms);
//
//		dao.createProduct(pd);
//
//		// return JSON nog nodig?
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		job.add("product_id", pd.getId());
//		job.add("categorie_id", pd.getCategorie());
//		job.add("naam", pd.getNaam());
//		job.add("oms", pd.getOmschrijving());
//		jab.add(job);
//
//		return jab.build().toString();
//	}
//
// cRud
	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getProduct(@PathParam("id") int id) {
		/*JsonArrayBuilder jab = Json.createArrayBuilder();
		Product pd = dao.getProduct(id);
		List<Object[]> aanbiedingen = dao.getAllAanbiedingen();

		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("product_id", pd.getId());
		job.add("naam", pd.getNaam());
		job.add("omschrijving", pd.getOmschrijving());
		job.add("prijs", pd.getPrijs());
		job.add("aanbieding", (JsonValue) pd.getAanbieding());
		job.add("bestellingsRegel", (JsonValue) pd.getBestellingsregel());
		job.add("categorie", (JsonValue) pd.getCategorie());

		for (Object[] ab : aanbiedingen) {
			Number nummer = (Number) ab[0];
			if (nummer.intValue() == pd.getId()) { // als
				String totDatum = "" + ab[1];// aanbieding.product_id
				String vanDatum = "" + ab[2];
															// = pd.id
				job.add("aanbiedingId", nummer.intValue());
				job.add("totDatum", totDatum);
				job.add("vanDatum", vanDatum);

			}
		}
		jab.add(job);

		return jab.build().toString();*/
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		System.out.println(id+" de meegegeven ID");
		
		List<Object[]> list = new ArrayList<Object[]>();
		List<Object[]> aanbiedingen = new ArrayList<Object[]>();
		aanbiedingen.addAll(dao.getAllAanbiedingen());
		list.addAll(dao.getProduct(id));
		
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println(list.size());
		System.out.println(list.get(0));
		
		Object o = list.get(0);
		System.out.println(list.size());
		System.out.println(o.toString());
		Object[] c = (Object[]) o;
	
		JsonObjectBuilder job = Json.createObjectBuilder();
		Number o_id = (Number) c[0]; // Maak number van Object
		String naam = "" + c[1];
		String oms = "" + c[2];
		//Number prijs = (Number) c[3];
		//System.out.println(o_id + naam + oms);
		job.add("id", o_id.intValue()); // krijg int van Number
		job.add("naam", naam);
		job.add("omschrijving", oms);
		
		for (Object[] ab : aanbiedingen) {
			Number nummer = (Number) ab[0];
			if (nummer.intValue() == o_id.intValue()) { // als
				String totDatum = "" + ab[1];// aanbieding.product_id
				String vanDatum = "" + ab[2];
															// = pd.id
				job.add("aanbiedingId", nummer.intValue());
				job.add("totDatum", totDatum);
				job.add("vanDatum", vanDatum);
			}
		}
		
		jab.add(job);
		return jab.build().toString();
	}

//	// crUd
//
//	@PUT
//	@Path("{id}")
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String updateProduct(@FormParam("p_id") int p_id, @FormParam("c_id") int c_id,
//			@FormParam("naam") String naam, @FormParam("prijs") int prijs, @FormParam("omschrijving") String oms) {
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		Set<Product> producten = dao.getAllProducten();
//		for (Product pd : producten) {
//			//System.out.println(pd.getId()+ " " + p_id);
//			if (pd.getId() == p_id) {
//				pd.setCategorie(c_id); //Koen?
//				//pd.setId(p_id);
//				pd.setNaam(naam);
//				pd.setOmschrijving(oms);
//				pd.setPrijs(prijs);
//				dao.updateProduct(pd);
//				
//				job.add("id", p_id);
//				job.add("categorie", c_id);
//				job.add("naam", naam);
//				job.add("omschrijving", oms);
//				job.add("prijs", prijs);
//				break;
//
//			}
//			// throw new WebApplicationException("Customer not found!");
//		}
//		System.out.println(job.build().toString() + " build");
//		return job.build().toString();
//	}
//
//	// cruD
//	
//	@DELETE
//	@Path("{code}")
//	public Response deleteProduct(@PathParam("code") int code) {
//		System.out.println("deleted: " + code);
//		Product found = null;
//		for (Product pd : dao.getAllProducten()) {
//			if (pd.getId()== code) {
//				found = pd;
//				dao.deleteProduct(found.getId());
//				break;
//			}
//		}
//
//		if (found == null) {
//			return Response.status(Response.Status.NOT_FOUND).build();
//		} else {
//			return Response.ok().build();
//		}
//	}
//
//	@GET
//	@Path("/bycat/{id}")
//	@Produces("application/json")
//	public String getProductenVanCategorie(int id) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		Set<Product> producten;
//		producten = dao.getAllProductenVanCategorie(id);
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		for (Product pd : producten) {
//			job.add("product_id", pd.getiD());
//			job.add("naam", pd.getNaam());
//			job.add("omschrijving", pd.getOmschrijving());
//			job.add("prijs", pd.getPrijs());
//			job.add("aanbieding", (JsonValue) pd.getAanbieding());
//			job.add("bestellingsRegel", (JsonValue) pd.getBestellingsregel());
//			job.add("categorie", (JsonValue) pd.getCategorie());
//			jab.add(job);
//		}
//
//		return jab.build().toString();
//	}

}