//package restfull;
//
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.security.RolesAllowed;
//import javax.json.Json;
//import javax.json.JsonArrayBuilder;
//import javax.json.JsonObjectBuilder;
//import javax.json.JsonValue;
//import javax.ws.rs.GET;
//import javax.ws.rs.Produces;
//
//import dao.OracleDao;
//import domain.Aanbieding;
//import domain.Categorie;
//
//public class aanbieding {
//	OracleDao dao;
//
//	@GET
//	@RolesAllowed("guest")
//	@Produces("application/json")
//	public String getAanbiedingen() {
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		Set<Aanbieding> aanbiedingen;
//		aanbiedingen = dao.getAllAanbiedingen();
//		JsonObjectBuilder job = Json.createObjectBuilder();
//		for (Aanbieding ab : aanbiedingen) {
//			job.add("id", ab.getiD());
//			job.add("naam", ab.getTotDatum());
//			job.add("omschrijving", ab.getVanDatum());
//			job.add("omschrijving", (JsonValue) ab.getProduct());
//			jab.add(job);
//		}
//
//		return jab.build().toString();
//	}
//
//}
