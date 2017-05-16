package Server_side;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import Server_side.EmployeeDTO;

@ServerEndpoint("/index")
public class Reception extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthenticationScheme auth;
	private User_Info user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reception() {
		super();
		auth = new AuthenticationScheme();
		user = new User_Info();
	}

	@OnOpen
	public void Greetings(Session session) {

		User_Info user;

		try {
			user = new User_Info();
			if (user.getLockStatus() == false && auth.getVisitedLogin()) {
				auth.setVisitedLogin(false);
				session.getBasicRemote().sendText("Welcome");
			}else if (auth.getReturnedFromUpload()== true && user.getLockStatus() == false) {
				auth.setReturnedFromUpload(false);
				session.getBasicRemote().sendText("returnedFromUpload");
			}else if(user.getLockStatus() && auth.getVisitedLogin()==false){
				session.getBasicRemote().sendText("Invalid Login");
			}else if(session.getOpenSessions().size()>1){
				session.getBasicRemote().sendText("Invalid Login");
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	@OnMessage
	public String getSymbol(String sqlCommand) {
		String message;
		String entries = "";
		DB_Connection conn = new DB_Connection();
		Statement stmt = conn.getStatement();

		ArrayList<EmployeeDTO> employees = new ArrayList<>();
		ArrayList<String> records = new ArrayList<>();

		// Connection conn = null;
		String createTable_query = "CREATE TABLE Employee( EMPNO int NOT NULL, ENAME varchar (50) NOT NULL, JOB_TITLE varchar (150) NOT NULL)";
		String insertQuery = "INSERT INTO Employee values (001, 'Dummy One', 'Software Engineer'), (002, 'Dummy Two', 'Software Developer'), (003, 'Dummy Three', 'Software Architect')";
		String dropQuery = "DROP TABLE Employee";

		try {
			stmt.executeUpdate(createTable_query);
			stmt.executeUpdate(insertQuery);

			// execute query from client
			ResultSet rs = stmt.executeQuery(sqlCommand);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			if (sqlCommand.toUpperCase().contains("EMPLOYEE")) {
				while (rs.next()) {
					EmployeeDTO currentEmp = new EmployeeDTO();
					currentEmp.setEmpNo(rs.getInt("EMPNO"));
					currentEmp.setEName(rs.getString("ENAME"));
					currentEmp.setJobTitle(rs.getString("JOB_TITLE"));
					employees.add(currentEmp);
				}
			} else {
				while (rs.next()) {
					String line = "";
					for (int i = 1; i <= columnsNumber; i++) {
						line = line + rs.getString(i) + " ";
					}
					records.add(line);
				}
			}
			// drop the table from database
			stmt.executeUpdate(dropQuery);

			// cleaning up environment
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			try {
				se.printStackTrace();
				stmt.executeUpdate(dropQuery);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "The query is not valid.";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (sqlCommand.toUpperCase().contains("EMPLOYEE")) {
			for (int i = 0; i < employees.size(); i++) {
				entries = entries + '\n' + employees.get(i).getEmpNo() + " " + employees.get(i).getEName() + " "
						+ employees.get(i).getJobTitle();
			}
			message = "\n\n" + "The query '" + sqlCommand + "' is sent to database." + '\n'
					+ "Database replies with the following: " + '\n' + entries;

			return message;
		} else {
			for (int i = 0; i < records.size(); i++) {
				entries = entries + '\n' + records.get(i);
			}
			message = "\n\n" + "The query '" + sqlCommand + "' is sent to database." + '\n'
					+ "Database replies with the following: " + '\n' + entries;
			return message;
		}
	}
}