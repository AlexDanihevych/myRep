package dice.com.FirstDIce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/dice";
	
	public final static String USER = "root";
	public final static String PASS = "Panasonic321";
	
	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	
	
	
	public static void connectToDB() throws Exception {
		
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();	
		String sql = "CREATE TABLE IF NOT EXISTS URL_list " + "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ " url VARCHAR(255), "  + " PRIMARY KEY (id))";

		stmt.executeUpdate(sql);
		System.out.println("Connected to the Database...");
		
		
	}
	
	public static void createTable() throws SQLException {
	
		String sql = "CREATE TABLE IF NOT EXISTS URL_list " + "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ " url VARCHAR(255), "  + " PRIMARY KEY (id))";
		stmt.executeUpdate(sql);
		System.out.println("Table was created...");
	}
	
	
	public static void addURLtoDB(String url) throws SQLException {
				
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO URL_list (url) values (?)");			
		pstmt.setString(1, url);
		pstmt.execute();					
	}
	
	public static String getUrlAt(int idx) throws SQLException {
		
		String sql = "SELECT url FROM URL_list WHERE id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return rs.getString("url");
	}
	
	public static void getAllUrls() throws SQLException {
		stmt = conn.createStatement();
		String query = "select count(*) from URL_list";
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		int count = rs.getInt(1);
	    System.out.println("There are  " +count + " urls in the table");
	    
	}

	
}

