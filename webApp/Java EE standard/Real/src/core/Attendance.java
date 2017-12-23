package core;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/attendance")
public class Attendance {
	private Set<Session> attendanceList = new CopyOnWriteArraySet<>();
	
	public Attendance(){}
	
	@OnOpen
	public void checkIn(final Session session) throws IOException, EncodeException{
		attendanceList.add(session);
		session.getBasicRemote().sendObject("Client ID " + session.getId() + " Welcome.");
	}
	
	@OnClose
	public void checkOut(final Session session){
		attendanceList.remove(session);
	}

	@OnMessage
	public void broacast(final String Message, final Session session) 
			throws IOException, EncodeException{
		System.out.println("send pressed");
	}
}
