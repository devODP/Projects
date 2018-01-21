package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session Bean implementation class Insert
 */
@LocalBean
@Stateless
public class Insert {

	public Insert() {
	}

	public String toString() {
		return "Class Insert";
	}

	public void fileInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User_Info user = new User_Info();

		for (int i = 0; i < user.getFiles().size(); i++) {
			try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db_demo;create=true");
					Statement stmt = conn.createStatement();
					BufferedReader br = new BufferedReader(
							new FileReader(user.getFiles().get(i)[0] + "/" + user.getFiles().get(i)[1]))) {

				addFileToDB(user.getFiles().get(i), br, conn, stmt);
				System.out.println(user.getFiles().get(i)[1] + " is uploaded.");
			} catch (IOException eIO) {
				eIO.printStackTrace();
			} catch (SQLException eSQL) {
				eSQL.printStackTrace();
			}
		}
		user.setFilesEmpty();
		response.sendRedirect("index.html");
	}

	private void addFileToDB(String[] fileUploaded, BufferedReader br, Connection conn, Statement stmt)
			throws IOException, SQLException {
		StringBuilder[] ls = new StringBuilder[2];
		ls[1] = new StringBuilder("");

		String fileName = fileUploaded[1];
		String tableName = fileName.substring(0, fileName.indexOf('.'));

		int row = 0;
		String line = "";

		while ((line = br.readLine()) != null) {
			if (row == 0) {

				// table attributes starts from row 0 of the excel file
				String[] attributes = line.split(",");
				createTable(ls, attributes, tableName);

				try {
					stmt.executeUpdate(ls[0].toString());
				} catch (SQLException se) {
					throw se;
				}
				row++;

			} else {

				// table entries starts from row 1 of the excel file
				String entries[] = line.split(",");
				createEntries(ls, entries, tableName);

				try {
					stmt.executeUpdate(ls[1].toString());
				} catch (SQLException se) {
					throw se;
				}
			}
		}
	}

	// Constructing the "CREATE TABLE...." statment for further queries to
	// database
	private void createTable(StringBuilder[] ls, String[] attributes, String tableName) {
		ls[0] = new StringBuilder("CREATE TABLE " + tableName + "(");
		ls[0].append(attributes[0] + " varchar (50) NOT NULL");

		if (attributes.length > 1) {
			for (int i = 1; i < attributes.length; i++) {
				ls[0].append(", " + attributes[i] + " varchar (50) NOT NULL");
			}
		}

		ls[0].append(")");
	}

	// Constructing the "INSERT" command
	private void createEntries(StringBuilder[] ls, String[] entries, String tableName) {
		ls[1] = new StringBuilder("INSERT INTO " + tableName + " values ('" + entries[0] + "'");

		if (entries.length > 1) {
			for (int i = 1; i < entries.length; i++) {
				ls[1].append(", '" + entries[i] + "'");
			}
		}

		ls[1].append(")");
	}
}