package Server_side;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthenticationScheme auth;
	private User_Info user;

	public login() {
		super();
		auth = new AuthenticationScheme();
		user = new User_Info();
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (user.getLockStatus()) {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			user = new User_Info(request.getRemoteAddr(), request.getRemotePort());
			boolean credentials = (username.equals("admin") && password.equals("admin"));
			boolean clientAddr = user.getAddr().equals(request.getRemoteAddr());

			if (credentials && (user.getLockStatus() == false) && clientAddr) {
				auth.setVisitedLogin(true);
				response.sendRedirect("client.html");
			} else {
				user.setAddrVoid();
				user.setLockStatus(true);
				response.sendRedirect("login.html");
			}
		} else if (user.getAddr().equals(request.getRemoteAddr()) && request.getParameter("username").equals("admin")
				&& request.getParameter("password").equals("admin")) {
			
			PrintWriter writer = response.getWriter();
			writer.println("Reminder: ");
			writer.println(
					"You already have logged in in another tab/window, please close this tab/window and continue your request there.");
		} else {
			response.sendRedirect("login.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		doGet(request, response);
	}
}
