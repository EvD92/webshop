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
import javax.ws.rs.PathParam;
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
import domain.Categorie;
import domain.Product;

@Path("/categories")
public class categorie {
	OracleDao dao = new OracleDaoHibernate();
	ResponseBuilder rb = null;
	
//	Requested functions in application/json:
//		getAllCategories;
	@GET
	@RolesAllowed("guest")
	  @Produces("application/json")
	
	  public String getCategorien() {
		System.out.println("kak");//returned JSON, werkt
		List<Object[]> categorien = dao.getAllCategorien(); // Get alle categorien van DB
		System.out.println(categorien);
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
	      JsonObjectBuilder job = Json.createObjectBuilder();
	      for (Object[] cg : categorien) {
	    	  Number id = (Number) cg[0]; //Maak number van Object
	    	  String naam = "" + cg[1]; //Maak er n String van
	    	  String oms = "" + cg[2];	//Maak er n String van
	    	  
	        job.add("id", id.intValue()); //krijg int van Number
	        job.add("naam", naam);
	        job.add("omschrijving", oms);
	        
	        jab.add(job);
	      }

	    return jab.build().toString();
	  }
	
//	Crud
	@POST 													//Werkt naar DB
	@Produces("application/json")
	public String createCategorie(@PathParam("c_id") int c_id, @PathParam("naam") String naam, @PathParam("omschrijving") String oms) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Categorie cat = new Categorie();
		cat.setId(c_id); // Koen? cat id?
		cat.setNaam(naam);
		cat.setOmschrijving(oms);

		dao.createCategorie(cat);

		// return JSON nog nodig?
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("categorie_id", cat.getId());
		job.add("naam", cat.getNaam());
		job.add("oms", cat.getOmschrijving());
		jab.add(job);

		return jab.build().toString();
	}
//	
//	//cRud
	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getCategorie(@PathParam ("id") int id) {					//werkt weer
		JsonArrayBuilder jab = Json.createArrayBuilder();
		System.out.println(id+" de meegegeven ID");
		
		List<Object[]> list = new ArrayList<Object[]>();
		list.addAll(dao.getCategorie(id));
		
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println(list.size());
		System.out.println(list.get(0));
		
	Object o = list.get(0);
	Object[] c = (Object[]) o;
	
	JsonObjectBuilder job = Json.createObjectBuilder();
	Number o_id = (Number) c[0]; // Maak number van Object
	String naam = "" + c[1];
	String oms = "" + c[2];
	job.add("id", o_id.intValue()); // krijg int van Number
	job.add("naam", naam);
	job.add("omschrijving", oms);

		
		jab.add(job);
		return jab.build().toString();
	}
	
	//crUd
	@PUT
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String updateCategorie(@PathParam("c_id") int c_id, @PathParam("naam") String naam, @PathParam("omschrijving") String oms) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		List<Object[]> categorien = dao.getAllCategorien();
		for (Object[] cat : categorien) {
			Number numb = (Number) cat[0];
			if (numb.intValue() == c_id) {
				Categorie categorie = null;
				//categorie.setId(c_id);
				categorie.setNaam(naam);
				categorie.setOmschrijving(oms);
				dao.updateCategorie(categorie);
				
				job.add("categorie_id", c_id);
				job.add("naam", naam);
				job.add("omschrijving", oms);
				break;

			}
			// throw new WebApplicationException("Customer not found!");
		}
		//System.out.println(job.build().toString() + " build");
		return job.build().toString();
	}
	
	//cruD
	@DELETE
	@Path("{code}")
	public Response deleteCategorie(@PathParam("code") int code) {
		System.out.println("deleted: " + code);
		Number found = 0;
		for (Object[] cat : dao.getAllCategorien()) {
			Number numb = (Number) cat[0];
			if (numb.intValue() == code) {
				found = numb;
				dao.deleteProduct(found.intValue());
				break;
			}
		}

		return Response.ok().build();
	}
	
	
	
}