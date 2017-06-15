package Server_side;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Connection {
	private Connection conn = null;
	private Statement stmt;
	
	public DB_Connection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db_demo;create=true");
				stmt = conn.createStatement();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}else{
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public Statement getStatement(){
		return stmt;
	}
	
	public void close() throws SQLException{
		conn.close();
	}
}
