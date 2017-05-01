package DB_entries_randomization;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Generator {

	public static void main(String[] args) {
		ITableInfo tmp = new ITableInfo("", "");
		JFrame jFrame = new JFrame("Mouse Dragger");
		mySketch sk = new mySketch();
		GridBagConstraints gbc = new GridBagConstraints();

		jFrame.setLayout(new GridBagLayout());
		jFrame.setSize(900, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top = new JPanel();
		// add text area
		JLabel tableL = new JLabel("Enter table name:");
		JLabel attributesL = new JLabel("Fields of the table(comma separated):");
		JTextField table = new JTextField(10);
		JTextField attributes = new JTextField(10);
		top.add(tableL);
		top.add(table);
		top.add(attributesL);
		top.add(attributes);

		// button
		JButton creTable = new JButton("Create");		
		creTable.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		         ITableInfo tableInfo = new ITableInfo(table.getText(), attributes.getText());
		         String tableName = tmp.getTName();
		         String attrNames = tmp.getANames();
		         createTable(tableName, attrNames);
		    }
		}); 
		top.add(creTable);
		
		
		// add sketch area
		jFrame.setLayout(new BorderLayout());
		jFrame.add(top, BorderLayout.NORTH);
		jFrame.add(sk, BorderLayout.CENTER);

		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
	
	public static void createTable(String tableName, String attr) {		
		String createTable = "CREATE TABLE " + tableName + "(";
		String dropTable = "DROP TABLE " + tableName;
		
		DB_Connection conn = new DB_Connection();
		Statement stmt = conn.getStatement();

        String ls[] = attr.split(",");
        
        if(ls.length > 1){
        	createTable = createTable + ls[0] + " varchar (50) NOT NULL";
            for(int i = 1; i < ls.length; i++){
            	createTable = createTable + ", " + ls[i] + " varchar (50) NOT NULL";
            }
            createTable = createTable + ")";
        }else{
        	createTable = createTable + ls[0] + " varchar (50) NOT NULL)";
        }
	
        System.out.println(createTable);
        
		try {
			stmt.executeUpdate(createTable);
			stmt.close();
		} catch (SQLException se) {
			try {
				se.printStackTrace();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
