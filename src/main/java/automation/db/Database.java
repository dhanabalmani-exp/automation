package automation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import automation.properties.ApplicationProperties;

public class Database {

	private static Connection getConnection() {

		Connection con = null;
		try {
			Class.forName(ApplicationProperties.getInstance().getProperty("db.driver"));
			con = DriverManager.getConnection(ApplicationProperties.getInstance().getProperty("db.url"),
					ApplicationProperties.getInstance().getProperty("db.user").trim(),
					ApplicationProperties.getInstance().getProperty("db.user").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public static Set<String> getMember(final String packageName) {
		
		final Set<String> members = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SQL Query here" + packageName +" SQL Query here ");
			rs = ps.executeQuery();
			while(rs.next()) {
				members.add(rs.getString(1));
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(rs!=null) { try { rs.close(); } catch(Exception ex) { } }
			if(ps!=null) { try { ps.close(); } catch(Exception ex) { } }
			if(con!=null) { try { con.close(); } catch(Exception ex) { } }
		}
		
		return members;		
	}

}
