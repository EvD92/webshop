package security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BaseDao;

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
//		String query = "SELECT role FROM lid WHERE email = ? AND password = ?";
		String query = "SELECT NAAM FROM CATEGORIE WHERE CATEGORIE_ID = 6";
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			PreparedStatement pstmt = con.prepareStatement(query);
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
//				role = rs.getString("role");
				role = rs.getString("NAAM");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return role;
	}
	
//	String s = "select * from train where company is ?";
//
//	try {
//		Class.forName(DB_DRIV).newInstance();
//	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
//		e1.printStackTrace();
//	}
//
//	try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//			PreparedStatement stmt = conn.prepareStatement(s)) {
//
//		try (ResultSet rs = stmt.executeQuery()) {
//			while (rs.next()) {
//				// Train t = new Train();
//				// t.setName(rs.getString("name"));
//				// trains.add(t);
//			}
//		}
//	} catch (SQLException e) {
//		e.printStackTrace();
//		System.out.println("fail2");
//	}
//	return s;
	
	
}