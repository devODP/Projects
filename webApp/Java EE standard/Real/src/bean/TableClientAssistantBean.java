package bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import ejb.TableServiceHelperBean;
import ejb.TableServiceHelperBean;
import entity.Tableet;
/**
 *
 * @author Owen
 */
@Stateless
public class TableClientAssistantBean {

	@Inject
	TableServiceHelperBean tsh;
	
	public List<Tableet> getAllTables(){
		return tsh.getAll();
	}
	
	public void add() {
		Tableet table = new Tableet();
		table.setId("34"); 
		table.setName("Melanie");
		table.setSalary("40000");
		
		tsh.saveTable(table);
	}
}