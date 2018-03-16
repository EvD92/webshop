package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDaoExampleImpl extends BaseDaoExample implements BaseDaoExampleInt{
	
	@Override
	public String returnShitt() {

		String s = "select * from train where company is ?";

		try {
			Class.forName(DB_DRIV).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(s)) {

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					// Train t = new Train();
					// t.setName(rs.getString("name"));
					// trains.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("fail2");
		}
		return s;
	}


}
