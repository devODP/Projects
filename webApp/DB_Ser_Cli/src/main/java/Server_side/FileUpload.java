package Server_side;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(FileUpload.class.getCanonicalName());
	private static AuthenticationScheme auth;

	public FileUpload() {
		super();
		auth = new AuthenticationScheme();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User_Info user = new User_Info();

		response.setContentType("text/html;charset=UTF-8");

		// Create path components to save the file
		final String path = this.getServletContext().getRealPath("/");
		final Part filePart = request.getPart("file");
		final String fileName = getFileName(filePart);

		final PrintWriter writer = response.getWriter();

		if (user.getAddr().equals(request.getRemoteAddr()) && auth.getReturnedFromUpload() == false) {
			try(OutputStream out = new BufferedOutputStream (new FileOutputStream(path + File.separator + fileName));

				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = fileContent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
					//out.flush();
				}
				writer.println("New file " + fileName + " created at " + path);
				LOGGER.log(Level.INFO, "File {0} being uploaded to {1}", new Object[] { fileName, path });

				auth.setReturnedFromUpload(true);
				
				/* stores an array consisting of the path and 
				 * the file name for the servlet "insert"
				 */
				String fullPath[] = new String[2];
				fullPath[0] = path;
				fullPath[1] = fileName;
				user.setFileName(fullPath);
				user.setFileEmpty(false);
				user.setMessage("File " + fileName + " is uploaded.");
				
				response.sendRedirect("client.html");
			} catch (FileNotFoundException fne) {
				LOGGER.log(Level.INFO, "No file is selected");
				LOGGER.log(Level.SEVERE, "Problems during file upload. Error:{0}", new Object[] { fne.getMessage() });
				user.setMessage("Error: File " + fileName + " is not uploaded.");
				response.sendRedirect("client.html");
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}