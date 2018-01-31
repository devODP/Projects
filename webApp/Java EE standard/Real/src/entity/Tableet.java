package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TABLEET database table.
 * 
 */
@Entity
@Table(name="TABLEET")
@NamedQuery(name="Tableet.findAll", query="SELECT t FROM Tableet t")
public class Tableet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private String salary;

	public Tableet() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

}