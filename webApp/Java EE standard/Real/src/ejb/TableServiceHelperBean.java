package ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Tableet;

@LocalBean
@Stateless
public class TableServiceHelperBean {

	@PersistenceContext
	private EntityManager em;

	public void saveTable(Tableet table) {
		em.merge(table);
	}

	public void deleteTable(Tableet table) {
		em.remove(table);
	}
	
	public List<Tableet> getAll(){
		Query q = em.createQuery("SELECT s FROM Tableet s");
		return (List<Tableet>)q.getResultList();
	}
}
