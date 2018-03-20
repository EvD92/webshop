package restfull;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dao.OracleDao;
import dao.OracleDaoHibernate;


@Path("/aanbiedingen")
public class aanbieding {
	OracleDao dao = new OracleDaoHibernate();
	
	@GET
	@Path("{id}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String getAanbieding(@PathParam ("id") int id) {					//werkt weer
		//JsonArrayBuilder jab = Json.createArrayBuilder();
		System.out.println(id+" de meegegeven ID");
		
		List<Object[]> list = new ArrayList<Object[]>();
		list.addAll(dao.getAanbieding(id));
		
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println(list.size());
		System.out.println(list.get(0));
		
	Object o = list.get(0);
	Object[] c = (Object[]) o;
	
	JsonObjectBuilder job = Json.createObjectBuilder();
	Number numb = (Number)c[0];
	String s = "" + c[1];
	String ss= "" + c[2];
	String sss= "" + c[3];
	String ssss = "" + c[4];
		
	job.add("id", numb.intValue() );
	job.add("van_datum", s);
	job.add("tot_datum", ss);
	job.add("product_id", sss);
	job.add("aanbiedingsprijs", ssss);

		
		
		return job.build().toString();
	}
	

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
			String ssss = "" + ab[4];
 			
			job.add("id", numb.intValue() );
			job.add("van_datum", s);
			job.add("tot_datum", ss);
			job.add("product_id", sss);
			job.add("aanbiedingsprijs", ssss);
			
			jab.add(job);
		}

		return jab.build().toString();
	}

}
