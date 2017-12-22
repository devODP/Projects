package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logout
 */

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LogoutServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		
		if(request.getContentType() == null){
			response.sendRedirect("index.html");
		}
		else if(request.getContentType().contains("application/x-www-form-urlencoded")){
			request.logout();
			response.sendRedirect("index.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		doGet(request, response);
	}
}
