package DB_entries_randomization;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class mySketch extends Component implements MouseListener, MouseMotionListener {
	int curX = -1, curY = -1;
	ITableInfo tableInfo;
	
	public mySketch() {
		addMouseListener(this);
		addMouseMotionListener(this);
		tableInfo = new ITableInfo();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		Point p = event.getPoint();
		curX = p.x;
		curY = p.y;
		if (curY > 150) {
			this.getGraphics().drawString(
					"Drag your mouse around the space on the screen to generate data entries for database", 34, 56);

			this.getGraphics().setColor(Color.red);
			this.getGraphics().fillOval(curX, curY, 10, 10);
			String id = Integer.toString(curX) + Integer.toString(curY);
			DB_Connection conn = new DB_Connection();
			Statement stmt = conn.getStatement();
			String tableName = tableInfo.getTName();
			String ls[] = tableInfo.getANames().split(",");
			
			//forming the insert command
			String insertQuery = "INSERT INTO " + tableName + " values ('" + id + "'";
			for(int i = 0; i < ls.length - 1; i++){
				insertQuery =  insertQuery + ", 'Dummy'";
			}
			insertQuery = insertQuery + ")";
				
			try {
				stmt.executeUpdate(insertQuery);

				// rs.close();
				stmt.close();
				//
			} catch (SQLException se) {
				try {
					se.printStackTrace();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics graphic) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
