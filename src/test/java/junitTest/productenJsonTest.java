package junitTest;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.junit.Test;

import domain.Categorie;
import domain.Product;

public class productenJsonTest {

	@Test
	public void test() {

		Categorie cat = new Categorie();
		Set<Categorie> cats = new HashSet<Categorie>();
		Product pd = new Product();
		Product pd2 = new Product();
		
		cat.setId(1);
		cat.setNaam("TestCat");
		cat.setOmschrijving("TestCatOms");
		
		cats.add(cat);
		
		pd.setId(1);
		pd.setNaam("TestProd1");
		pd.setCategorie(cats);
		
		pd2.setId(2);
		pd2.setNaam("TestProd2");
		pd2.setCategorie(cats);
		

		List<Product> producten = new ArrayList<Product>();
		producten.add(pd);
		producten.add(pd2);
		

		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Product cg : producten) {
			
			job.add("id", cg.getId()); // krijg int van Number
			job.add("naam", cg.getNaam());
			


			jab.add(job);
			jab.add(job);
			try {
		        JsonObject o = (job.build());
		    } catch (JsonException e) {
		        System.out.println(("Aanbiedingen has no valid json"));
		        fail("Not yet implemented");
		    }
			}
		System.out.println(("Producten werkt"));
		
	}


}
