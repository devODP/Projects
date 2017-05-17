package Server_side;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/index")
public class Reception extends HttpServlet {
	private final static Logger LOGGER = Logger.getLogger(FileUpload.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	private static AuthenticationScheme auth;
	private static User_Info user;
	private static String currentClient = "";

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
		try {
			if (user.getLockStatus() == false && auth.getVisitedLogin() == true) {
				auth.setVisitedLogin(false);
				session.getBasicRemote().sendText("Welcome");
			} else if (user.getLockStatus() == false && auth.getReturnedFromUpload() == true) {
				auth.setReturnedFromUpload(false);
				session.getBasicRemote().sendText("file Uploaded");
			} else if (user.getLockStatus() == true && auth.getVisitedLogin() == false) {
				session.getBasicRemote().sendText("Invalid Login");
			} else if (session.getOpenSessions().size() > 1) {
				session.getBasicRemote().sendText("Invalid Login");
			}
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, "Problem occured in greetings.", new Object[] { ioe.getMessage() });
		}
	}

	@OnMessage
	public String getCommand(String data) {
		/* The client's IP address is sent to this servlet when
		 * when client.html is accessed, thus the IP address 
		 * will be validated
		 */
		if (patternMatcher(data)) {
			currentClient = data;
			if (!currentClient.equals(user.getAddr())) {
				return "Invalid Login";
			} else {
				return "Visiting IP matches login IP";
			}
		} else {
			String message;
			String entries = "";
			String SQLQuery = data.toUpperCase();
			DB_Connection conn = new DB_Connection();
			Statement stmt = conn.getStatement();
			ArrayList<Object> records = new ArrayList<>();

			try {
				if (SQLQuery.contains("CREATE")) {
					stmt.executeUpdate(SQLQuery);
					return "Table created";
				} else if (SQLQuery.contains("INSERT")) {
					stmt.executeUpdate(SQLQuery);
					return "Values inserted";
				} else if (SQLQuery.contains("DROP")) {
					stmt.executeUpdate(SQLQuery);
					return "Table dropped";
				} else if (SQLQuery.contains("SELECT")) {
					
					//Preparing for retrieving data from database
					ResultSet rs = stmt.executeQuery(SQLQuery);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();

					//reading result set column by column
					while (rs.next()) {
						String line = "";
						for (int i = 1; i <= columnsNumber; i++) {
							line = line + rs.getString(i) + " ";
						}
						records.add(line);
					}
					//
					
					rs.close();
					stmt.close();

					//Send customized message to client and display it on client.html
					for (int i = 0; i < records.size(); i++) {
						entries = entries + '\n' + records.get(i);
					}
					message = "\n\n" + "The query '" + data + "' is sent to database." + '\n'
							+ "Database replies with the following: " + '\n' + entries;
					
					return message;
				}else{
					return "Command is not recognized by database";
				}
			} catch (SQLException se) {
				try {
					stmt.close();
					LOGGER.log(Level.SEVERE, "Problem with SQL command.", new Object[] { se.getMessage() });
					return se.getMessage();
				} catch (SQLException e) {
					LOGGER.log(Level.SEVERE, "Problem in cleaning up environment.", new Object[] { e.getMessage() });
					return e.getMessage();
				}
			}
		}
	}

	public boolean patternMatcher(String ip) {
		Pattern pattern;
		Matcher matcher;
		String IPADDRESS_PATTERN = 
				"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." 
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." 
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

		pattern = Pattern.compile(IPADDRESS_PATTERN);

		matcher = pattern.matcher(ip);
		return matcher.matches();
	}
}
