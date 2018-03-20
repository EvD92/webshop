package security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BaseDao;
import domain.Adres;
import domain.Klant;

public class UserDao extends security.BaseDao {
	public String findRoleForUsernameAndPassword(String username, String password) {
		String role = null;
		String query = "SELECT role FROM useraccount WHERE username = ? AND password = ?";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				role = rs.getString("role");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return role;
	}

	public String findRoleForEmailAndPassword(String email, String password) {
		String role = null;
		// String query = "SELECT role FROM lid WHERE email = ? AND password =
		// ?";
//		String query = "SELECT NAAM FROM CATEGORIE WHERE CATEGORIE_ID = 1";
		String query = "select ROLE from klant where EMAIL = ? and PASSWORD = ?";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			PreparedStatement pstmt = con.prepareStatement(query);
			 pstmt.setString(1, email);
			 pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				// role = rs.getString("role");
				role = rs.getString("ROLE");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return role;
	}

	public Klant getKlant(String email) {
		Klant k = new Klant();
		Adres a = new Adres();
		System.out.println(email + "getklantemail");
		String query = "select k.naam, a.straat, a.straatnummer from adres a, klant k where k.klant_id = a.klant_id and email = ?";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			// pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				k.setNaam(rs.getString("NAAM"));
				a.setStraat(rs.getString("STRAAT"));
				a.setStraatNummer(rs.getInt("STRAATNUMMER"));
				k.setAdres(a);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return k;

	}

}