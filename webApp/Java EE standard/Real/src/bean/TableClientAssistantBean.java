package bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
}