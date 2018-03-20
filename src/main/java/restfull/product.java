package restfull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.OracleDao;
import dao.OracleDaoHibernate;
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
			
			
			job.add("id", id.intValue());
			job.add("naam", naam);
			job.add("omschrijving", oms);
			job.add("prijs", prijs.intValue());
	        
			jab.add(job);
		}

		return jab.build().toString();
	}
//
//	// Crud
	@POST
	//@GET
	@Path("/create")
	//@Path("/create/{id}/{prijs}/{omschrijving}/{naam}")
	@RolesAllowed("guest")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createProduct(@FormParam("id") int p_id,
			@FormParam("naam") String naam, @FormParam("prijs") int prijs, @FormParam("omschrijving") String oms) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Product pd = new Product();
		Categorie cg = new Categorie();
		Object[] o = dao.getCategorie(1).get(0);
		Number id = (Number) o[0];
		String Naam = ""+o[1];
		String omschrijving = ""+ o[2];		
		
		cg.setId(id.intValue());
		cg.setNaam(Naam);
		cg.setOmschrijving(omschrijving);
		
		pd.setId(p_id);
		
		Set<Categorie> dit = new HashSet<Categorie>(); 
		dit.add(cg);
		pd.setCategorie(dit); 
		pd.setNaam(naam);
		pd.setOmschrijving(oms);
		pd.setPrijs(prijs);

		dao.createProduct(pd);

		// return JSON nog nodig?
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("product_id", pd.getId());
		job.add("categorie_id", 1);
		job.add("naam", pd.getNaam());
		job.add("oms", pd.getOmschrijving());
		//jab.add(job);

		return job.build().toString();
	}
//
// cRud
	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getProduct(@PathParam("id") int id) {
		
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		System.out.println(id+" de meegegeven ID");
		
		List<Object[]> list = new ArrayList<Object[]>();
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
		Number prijs = (Number) c[3];
		job.add("id", o_id.intValue()); // krijg int van Number
		job.add("naam", naam);
		job.add("omschrijving", oms);
		job.add("prijs", prijs.intValue());
		

		
		
		return job.build().toString();
	}

//	// crUd

	@POST
	@Path("/{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String updateProduct(@PathParam("id") int p_id, @FormParam("naam") String naam, 
			@FormParam("prijs") int prijs, @FormParam("omschrijving") String oms) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		List<Object[]> producten = dao.getProduct(p_id);
		Product pdn = new Product();
		for (Object[] pd : producten) {
			pdn.setId(p_id);
			System.out.println(p_id);
			pdn.setNaam(naam);
			System.out.println(naam);
			pdn.setOmschrijving(oms);
			System.out.println(oms);
			pdn.setPrijs(prijs);
			System.out.println(prijs);
			System.out.println(pdn.getNaam());
			break;	
				

			}
		System.out.println("dao update call");
			dao.updateProduct(pdn);
			System.out.println("dao call finish!");
		
			job.add("id", p_id);
			//job.add("categorie", ca_id.intValue());
			job.add("naam", naam);
			job.add("omschrijving", oms);
			job.add("prijs", prijs);
		return job.build().toString();
	}
//
//	// cruD
	
	@DELETE
	@Path("{id}")
	public String deleteProduct(@PathParam("id") int id) {
		System.out.println(id);
		dao.deleteProduct(id);
		return "gelukt";
	}

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