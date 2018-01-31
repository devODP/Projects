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
	private Set<Session> __attendanceList__ = new CopyOnWriteArraySet<>();

	public Attendance() {
		super();
	}

	@OnOpen
	public void checkIn(final Session session) throws IOException, EncodeException {
		__attendanceList__.add(session);
		session.getBasicRemote().sendObject("Client ID " + session.getId() + " Welcome.");
	}

	@OnClose
	public void checkOut(final Session session) {
		__attendanceList__.remove(session);
	}

	@OnMessage
	public void broacast(final String Message, final Session session) throws IOException, EncodeException {
		SQLChecker sC = new SQLChecker(new StringBuilder(Message));
		System.out.println("SQL injection? : " + sC.isSQLInjection());
		System.out.println("send pressed");
	}
}
