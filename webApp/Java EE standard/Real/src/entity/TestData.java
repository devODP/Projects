package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TEST_DATA database table.
 * 
 */
@Entity
@Table(name="TEST_DATA")
@NamedQuery(name="TestData.findAll", query="SELECT t FROM TestData t")
public class TestData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private String salary;

	public TestData() {
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