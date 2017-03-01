package Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public Connection con;
	public ResultSet rs;
	public Statement st;
	public PreparedStatement pst;
	public String url = "jdbc:mysql://localhost:3306/hangman";
	public String user = "root";
	public String pass = "1234";
	
	public Connect() throws SQLException{
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public void closeConnection() throws SQLException {
		rs.close();
		pst.close();
		con.close();
	}
}
