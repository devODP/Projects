package core;


import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject Insert insert;
	
	public InsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getContentType() == null){
			response.sendRedirect("index.html");
		}
		else if(request.getContentType().contains("multipart/form-data")){
			insert.fileInsert(request, response);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
