package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class DBAtualizandoDados {

	public static void main(String[] args) {

		Connection conn = null;

		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("UPDATE seller " 
					+ "SET DepartmentId = ? " 
					+ "WHERE " 
					+ "(Name = ?)");
			st.setInt(1, 2);
			st.setString(2, "Carl Purple");
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
			
		}
		catch (SQLException e) {
			throw new DbException ("Error: " + e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
