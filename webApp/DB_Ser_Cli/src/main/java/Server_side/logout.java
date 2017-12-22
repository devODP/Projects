package Server_side;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 

		// User information to be cleared out 
		AuthenticationScheme auth = new AuthenticationScheme();;
		User_Info user = new User_Info();
		//
		
		PrintWriter out = response.getWriter();
		if (request.getRemoteAddr().equals(user.getAddr())) {
			user.setLockStatus(true);
			user.setAddrVoid();
			user.setPortVoid();
			auth.setFreshUser(true);
			response.sendRedirect("login.html");
		} else {
			out.println("Someone is logged in as an administrator before you do.");
			out.println("Please come back in a moment");
		}
	}
}
