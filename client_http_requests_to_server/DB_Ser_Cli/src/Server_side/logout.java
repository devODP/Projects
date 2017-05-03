package Server_side;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public logout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User_Info user = new User_Info();
		System.out.println(request.getRemoteAddr() + " " + request.getRemotePort());
		if (request.getRemoteAddr().equals(user.getAddr()) && request.getRemotePort() == user.getPortNumber()) {
			user.setLockStatus(true);
			response.sendRedirect("login.html"); 
		} else {
			PrintWriter out = response.getWriter();
			out.println("Someone is logined as an admin");
		}
	}

}
