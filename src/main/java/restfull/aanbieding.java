package restfull;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dao.OracleDao;
import dao.OracleDaoHibernate;
import domain.Aanbieding;
import domain.Categorie;


@Path("/aanbiedingen")
public class aanbieding {
	OracleDao dao = new OracleDaoHibernate();

	@GET
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getAanbiedingen() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Object[]> aanbiedingen;
		aanbiedingen = dao.getAllAanbiedingen();
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Object[] ab : aanbiedingen) {
			Number numb = (Number)ab[0];
			String s = "" + ab[1];
			String ss= "" + ab[2];
			String sss= "" + ab[3];
			
			job.add("id", numb.intValue() );
			job.add("van_datum", s);
			job.add("tot_datum", ss);
			job.add("product_id", sss);
			
//			job.add("id", ab.getId());
//			job.add("naam", ab.getTotDatum());
//			job.add("omschrijving", ab.getVanDatum());
//			job.add("omschrijving", (JsonValue) ab.getProduct());
			jab.add(job);
		}

		return jab.build().toString();
	}

}
