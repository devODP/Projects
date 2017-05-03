package Server_side;

import java.io.IOException;
import java.io.PrintWriter;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(request.getRemoteAddr() + " " + request.getRemotePort());

		User_Info user = new User_Info(request.getRemoteAddr(), request.getRemotePort());
		/* authentication */
		boolean credentials = (username.equals("admin") && password.equals("admin"));
		boolean clientAddr = (user.getAddr().equals(request.getRemoteAddr())
				&& user.getPortNumber() == request.getRemotePort());
		//
		
		if (credentials && (user.getLockStatus() == false) && clientAddr) {
			response.sendRedirect("client.html");
		} else {
			request.setAttribute("error", "Unknown user, please try again");
			PrintWriter out = response.getWriter();
			out.println("Error: either username or password is incorrect or someone is logined as an admin");
			response.sendRedirect("login.html");
		}
	}

}
