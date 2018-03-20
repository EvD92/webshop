package security;

import java.security.Key;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Klant;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String email, @FormParam("password") String password) {
		System.out.println(email);
		System.out.println(password);
		try {
			// Authenticate the user against the database
			UserDao dao = new UserDao();
			String role = dao.findRoleForEmailAndPassword(email, password);
			if (role == null) {
				throw new IllegalArgumentException("No user found!");
			}
			Klant k = dao.getKlant(email);
			// Issue a token for the user
			Calendar expiration = Calendar.getInstance();
			expiration.add(Calendar.MINUTE, 30);
			String token = Jwts.builder().setSubject(email).claim("role", role).setExpiration(expiration.getTime())
					.signWith(SignatureAlgorithm.HS512, key).compact();
			// Return the token on the response
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("token", token);
			job.add("naam", k.getNaam());
			job.add("straat", k.getAdres().getStraat());
			job.add("straatnummer", k.getAdres().getStraatNummer());
			
			
			return Response.ok(job.build().toString()).build();
		} catch (JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}