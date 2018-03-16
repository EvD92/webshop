package restfull;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.OracleDao;
import domain.Categorie;

@Path("/categories")
public class categorie {
	OracleDao dao;
	ResponseBuilder rb = null;
	
//	Requested functions in application/json:
//		getAllCategories;
		
	@GET
	@RolesAllowed("guest")
	@Produces("text/html")
	public Response testing() {
    String s = "t werkte joepie";
    rb = Response.status(200).entity(s);
    return rb.build();
	}

	@GET
	@RolesAllowed("guest")
	  @Produces("application/json")
	  public String getCategorien() {
	    JsonArrayBuilder jab = Json.createArrayBuilder();
	    List<Categorie> categorien;
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
	
	
	
	
}