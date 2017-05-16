package Server_side;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Server_side.DB_Connection;


@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static User_Info user;

	public insert() {
		super();
		user = new User_Info();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (user.isFileEmpty() == true) {
			String path = user.getFileName()[0];
			String fileName = user.getFileName()[1];
			String tableName = fileName.substring(0, fileName.indexOf('.'));
			String attributes[];
			DB_Connection conn = new DB_Connection();
			Statement stmt = conn.getStatement();
			int row = 0;

			try (BufferedReader br = new BufferedReader(new FileReader(path + File.separator + fileName))) {
				String line = "";
				while ((line = br.readLine()) != null) {
					if (row == 0) {
						
						attributes = line.split(",");
						
						// Constructing the "CREATE TABLE...." statment for further queries to database
						String createTable = "CREATE TABLE " + tableName + "(";
						createTable = createTable + attributes[0] + " varchar (50) NOT NULL";

						if (attributes.length > 1) {
							for (int i = 1; i < attributes.length; i++) {
								createTable = createTable + ", " + attributes[i] + " varchar (50) NOT NULL";
							}
						}

						createTable = createTable + ")";
						//

						try {
							stmt.executeUpdate(createTable);
						} catch (SQLException se) {
							se.printStackTrace();
						}
						
						/*the data in .csv file will be processed 
						 *differently after row 0
						 */
						row = 1;
						
					} else {
						String entries[] = line.split(",");

						// Constructing the "INSERT" command
						String insertQuery = "INSERT INTO " + tableName + " values ('" + entries[0] + "'";

						if (entries.length > 1) {
							for (int i = 1; i < entries.length; i++) {
								insertQuery = insertQuery + ", '" + entries[i] + "'";
							}
						}
						
						insertQuery = insertQuery + ")";
						//						
						
						try {
							stmt.executeUpdate(insertQuery);
						} catch (SQLException se) {
							se.printStackTrace();
						}
					}
				}

				user.setFileEmpty(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		response.sendRedirect("client.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
