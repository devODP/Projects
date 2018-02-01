package rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import ejb.TableServiceHelperBean;
import entity.Tableet;

@Named
@Stateless
@RolesAllowed({"user"})
@Path("/table")
public class TableService {

	@Inject
	TableServiceHelperBean tsh;

	@GET
	@RolesAllowed({"user"})
	@Produces({ "application/xml", "application/json" })
	@Path("{action}")
	public Response addTable(final @PathParam("action") String action)
			throws URISyntaxException {
		List<Tableet> tables = tsh.getAll();
		java.net.URI location = null;

		switch (action) {
		case "add":
			Tableet table = new Tableet();
			table.setId("34");
			table.setName("Melanie");
			table.setSalary("40000");
			tsh.saveTable(table);
			location = new java.net.URI("../index.html?msg=a_user_added");
			break;

		case "check":
			System.out.println("Number of Entries inserted: " + tables.size());
			for (Tableet t : tables) {
				System.out.println("ID: " + t.getId() + " Name: " + t.getName() + " Salary: " + t.getSalary());
			}
			location = new java.net.URI("../index.html?msg=request_returned");
			break;

		case "delete":
			tsh.deleteTable(tables.get(tables.size() - 1));
			location = new java.net.URI("../index.html?msg=a_user_deleted");
			break;

		default:
			location = new java.net.URI("../index.html?msg=request_performed");
			break;
		}

		return Response.temporaryRedirect(location).build();
	}

}
