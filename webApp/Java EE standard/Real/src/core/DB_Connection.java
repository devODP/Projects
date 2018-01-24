package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DB_Connection implements AutoCloseable{
	private Connection __conn__ = null;
	private Statement __stmt__;
	
	public DB_Connection() {
		if (__conn__ == null) {
			try {
				__conn__ = DriverManager.getConnection("jdbc:derby://localhost:1527/db_demo;create=true");
				__stmt__ = __conn__.createStatement();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}else{
			try {
				__stmt__ = __conn__.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection(){
		return __conn__;
	}
	
	public Statement getStatement(){
		return __stmt__;
	}
	
	public void close() throws SQLException{
		__conn__.close();
	}
}
