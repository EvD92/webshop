package junitTest;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.junit.Test;

import domain.Categorie;

public class categorienJsonTest {

	@Test
	public void test() {

		Categorie cat1 = new Categorie();
		cat1.setId(1);
		cat1.setNaam("Testcat");
		cat1.setOmschrijving("Testcatoms");
		
		Categorie cat2= new Categorie();
		cat2.setId(1);
		cat2.setNaam("Testcat");
		cat2.setOmschrijving("Testcatoms");
		

		List<Categorie> categorien = new ArrayList<Categorie>();
		categorien.add(cat1);
		categorien.add(cat2);
		

		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Categorie cg : categorien) {
			
			job.add("id", cg.getId()); // krijg int van Number
			job.add("naam", cg.getNaam());
			job.add("omschrijving", cg.getOmschrijving());

			jab.add(job);
			jab.add(job);
			try {
		        JsonObject o = (job.build());
		    } catch (JsonException e) {
		        System.out.println(("Aanbiedingen has no valid json"));
		        fail("Not yet implemented");
		    }
			}
		System.out.println(("Categorien werkt"));
		
	}


}
