package core;


import java.io.IOException;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	Upload up;

	public FileUploadServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getContentType());
		
		if(request.getContentType() == null){
			response.sendRedirect("index.html");
		}
		else if(request.getContentType().contains("multipart/form-data")){
			up.fileUpload(request, response);
		}
			
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
}