package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class DBInserirDados {

	public static void main(String[] args) {
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;

		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			
			/*
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " 
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Paul Blank");
			st.setString(2, "Paul@Gmail.com");
			st.setDate(3, new java.sql.Date(date.parse("08/05/1982").getTime()));
			st.setDouble(4, 4200.00);
			st.setInt(5, 3);
			*/
			
			st = conn.prepareStatement("insert into department (name) values ('Sexy'),('Util')",
					Statement.RETURN_GENERATED_KEYS);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
