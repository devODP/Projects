package core;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.ejb.LocalBean;

/**
 * Session Bean implementation class Upload
 */
@LocalBean
@Stateless
public class Upload {

	public Upload() {
		super();
	}

	public String toString() {
		return "Class Upload";
	}

	public void fileUpload(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html;charset=UTF-8");

		// Create path components to save the file
		final String path = Paths.get(".").toString();
		
		for (final Part filePart : request.getParts()) {
			if(!filePart.getName().equals("file")) break;
			
			final String fileName = getFileName(filePart);
			
			try (OutputStream out = new BufferedOutputStream(new FileOutputStream(path + "/" + fileName));
					InputStream fileContent = filePart.getInputStream()) {
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = fileContent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				System.out.println("New file " + fileName + " created at " + Paths.get(".").toRealPath());

				/*
				 * stores an array consisting of the path and the file name for
				 * the servlet "insert"
				 */
				String fullPath[] = new String[2];
				fullPath[0] = path;
				fullPath[1] = fileName;

				User_Info userInfo = new User_Info();

				userInfo.addFile(request.getUserPrincipal().getName(), fullPath);
			} catch (FileNotFoundException fne) {
				break;
			} 
		}

		response.sendRedirect("index.html");
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