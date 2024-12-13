package model.entities;

import java.io.Serializable;

public class Department extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	
	public Department(Integer id) {
		super(id);
	}

	public Department(Integer id, String name) {
		super(id);
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Id: " + id + " - Name: " + name;
	}
}
