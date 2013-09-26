package ejemplo.accesojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoJDBC {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/capacitacion";
		String user = "root";
		String password = "";
		
		// DataSource ds = DriverManager.getConnection(url, user, password);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			// conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery( "SELECT * FROM alumno" );
			
			
			int numColumns = rs.getMetaData().getColumnCount();
			for ( int i = 1 ; i <= numColumns ; i++ ) {
				System.out.print(rs.getMetaData().getColumnName(i) + "\t");
			}
			
			System.out.println("");
			
			while ( rs.next() ) {
				for ( int i = 1 ; i <= numColumns ; i++ ) {
					// Column numbers start at 1.
					// Also there are many methods on the result set to return
					//  the column as a particular type. Refer to the Sun documentation 
					//  for the list of valid conversions.
					System.out.print(rs.getObject(i) + "\t" );
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); }
			catch (Exception ignore)
			{ /* Propagate the original exceptioninstead of this one that you may want just logged */ }
			try { stmt.close(); } 
			catch (Exception ignore) {
				/* Propagate the original exceptioninstead of this one that you may want just logged */ 
			}
			//It's important to close the connection when you are done with it
			try { conn.close(); } catch (Exception ignore) { 
				/* Propagate the original exception
		instead of this one that you may want just logged */ 
			}
		}
	}
}
