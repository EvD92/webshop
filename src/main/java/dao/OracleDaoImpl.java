package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.stringtemplate.v4.ST;

import domain.Attribute;
import domain.BusinessRule;
import nl.hu.pafr.model.Train;
import nl.hu.pafr.model.Wagon;

public class OracleDaoImpl extends BaseDao {
	
	@Override
	public ResponseBuilder attributeRule() {
		
		ArrayList<Wagon> wagons = new ArrayList<Wagon>();
		try {
			Class.forName(DB_DRIV).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("fail");
		}

		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

		PreparedStatement stmt = conn.prepareStatement("insert into wagon(name,seats,company) values(?, ?,?)");
		stmt.setString(1, w.getName());
		stmt.setInt(2, w.getSeats());
		stmt.setString(3, w.getCompany().getName());
		stmt.executeQuery();
		stmt.close();
		conn.close();

		ST.add("NAAM", b.getNaam());
		ST.add("OPERATOR", b.getOperator().getOperator());
		System.out.println("d");
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement stmt;
			stmt = conn.prepareStatement(ST.render());
			stmt.executeQuery();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return Response.status(404).entity(e.getMessage().toString());
		}
		return Response.status(200).entity("Succes.");

	}
	
	@Override
	public ArrayList<Train> getTrainsWithoutCompany() {
		ArrayList<Train> trains = new ArrayList<Train>();
		try {
			Class.forName(DB_DRIV).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("fail");
		}

		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement stmt = conn.prepareStatement("select * from train where company is ?");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Train t = new Train();
				t.setName(rs.getString("name"));
				trains.add(t);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("fail2");
		}
		return trains;
	}

}
