package junitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.junit.Test;

import domain.Aanbieding;
import domain.Categorie;
import domain.Product;

public class aanbiedingenJsonTest {
	
	Categorie cat = new Categorie();
	Set<Categorie> cats = new HashSet<Categorie>();
	Product pd = new Product();
	List<Object[]> aanbiedingen = new ArrayList<Object[]>() ;
	List<Aanbieding> abs = new ArrayList<Aanbieding>();
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		System.out.println("Start");
		
		cat.setId(1);
		cat.setNaam("TestCat");
		cat.setOmschrijving("TestCatOms");
		
		cats.add(cat);
		
		pd.setId(1);
		pd.setCategorie(cats);
		
		System.out.println("test1");
		
		Aanbieding ab1 = new Aanbieding();
		
		ab1.setId(1);
		ab1.setProduct(pd);
		ab1.setTotDatum("15-2-2018");
		ab1.setVanDatum("12-1-2018");
		Aanbieding ab2 = new Aanbieding();
		ab2.setId(2);
		ab2.setProduct(pd);
		ab2.setTotDatum("19-2-2018");
		ab2.setVanDatum("16-1-2018");
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		abs.add(ab1);
		abs.add(ab2);
		//abs.toArray();
		aanbiedingen.add(abs.toArray());
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		for (Aanbieding ab : abs) {
			//System.out.println(ab[0].);
			Number numb = ab.getId();
			String s = "" + ab.getTotDatum();
			String ss= "" + ab.getVanDatum();
			String sss= "" + ab.getProduct();
 			
			job.add("id", numb.intValue() );
			job.add("van_datum", s);
			job.add("tot_datum", ss);
			job.add("product", sss);
			jab.add(job);
			try {
		        JsonObject o = (job.build());
		    } catch (JsonException e) {
		        System.out.println(("Aanbiedingen has no valid json"));
		        fail("Not yet implemented");
		    }
			}
		System.out.println(("Aanbiedingen werkt"));
		
	}


}
