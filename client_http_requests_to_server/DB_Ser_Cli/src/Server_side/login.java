package Server_side;

import java.io.IOException;

import javax.servlet.ServletException;
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

	public login() {
		super();
		auth = new AuthenticationScheme();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		User_Info user = new User_Info(request.getRemoteAddr(), request.getRemotePort());
		
		/* authentication */
		boolean credentials = (username.equals("admin") && password.equals("admin"));
		boolean clientAddr = user.getAddr().equals(request.getRemoteAddr());
		//
		
		if (credentials && (user.getLockStatus() == false) && clientAddr) {
			auth.setVisitedLogin(true);
			response.sendRedirect("client.html");
		} else {
			response.sendRedirect("login.html");
		}
	}

}
