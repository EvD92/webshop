package restfull;

import java.sql.SQLException;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.OracleDao;
import domain.Aanbieding;
import domain.Categorie;
import domain.Product;

@Path("/categories")
public class categorie {
	OracleDao dao;
	ResponseBuilder rb = null;
	
//	Requested functions in application/json:
//		getAllCategories;
	@GET
	@RolesAllowed("guest")
	  @Produces("application/json")
	  public String getCategorien() {
	    JsonArrayBuilder jab = Json.createArrayBuilder();
	    Set<Categorie> categorien;
	    categorien = dao.getAllCategorien();
	      JsonObjectBuilder job = Json.createObjectBuilder();
	      for (Categorie cg : categorien) {
	        job.add("id", cg.getId());
	        job.add("naam", cg.getNaam());
	        job.add("omschrijving", cg.getOmschrijving());
	        jab.add(job);
	      }

	    return jab.build().toString();
	  }
	
//	//Crud
//	@POST
//	@Produces("application/json")
//	public String createCategorie(@FormParam("c_id") int c_id, @FormParam("naam") String naam, @FormParam("omschrijving") String oms) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		Categorie cat = new Categorie();
//		cat.setCategorie(c_id); // Koen? cat id?
//		cat.setNaam(naam);
//		cat.setOmschrijving(oms);
//
//		dao.createCategorie(cat);
//
//		// return JSON nog nodig?
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		job.add("categorie_id", cat.getId());
//		job.add("naam", cat.getNaam());
//		job.add("oms", cat.getOmschrijving());
//		jab.add(job);
//
//		return jab.build().toString();
//	}
//	
//	//cRud
//	@GET
//	@Path("{id}")
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String getCategorie(int id) {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		Categorie cat = dao.getCategorie(id);
//		
//		JsonObjectBuilder job = Json.createObjectBuilder();
//
//		job.add("product_id", cat.getId());
//		job.add("naam", cat.getNaam());
//		job.add("omschrijving", cat.getOmschrijving());
//		
//		jab.add(job);
//		return jab.build().toString();
//	}
//	
//	//crUd
//	@PUT
//	@Path("{id}")
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String updateCategorie(@FormParam("c_id") int c_id, @FormParam("naam") String naam, @FormParam("omschrijving") String oms) {
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		Set<Categorie> categorien = dao.getAllCategorien();
//		for (Categorie cat : categorien) {
//			if (cat.getId() == c_id) {
//				cat.setNaam(naam);
//				cat.setOmschrijving(oms);
//				
//				dao.updateCategorie(cat);
//				
//				job.add("categorie_id", c_id);
//				job.add("naam", naam);
//				job.add("omschrijving", oms);
//				break;
//
//			}
//			// throw new WebApplicationException("Customer not found!");
//		}
//		System.out.println(job.build().toString() + " build");
//		return job.build().toString();
//	}
//	
//	//cruD
//	@DELETE
//	@Path("{code}")
//	public Response deleteCategorie(@PathParam("code") int code) {
//		System.out.println("deleted: " + code);
//		Categorie found = null;
//		for (Categorie cat : dao.getAllCategorien()) {
//			if (cat.getId()== code) {
//				found = cat;
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
//	
	
}