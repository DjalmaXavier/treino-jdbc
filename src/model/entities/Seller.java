package model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Seller extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private LocalDateTime birthDate;
	private Double baseSalary;

	private Department department;

	public Seller(Integer id, String name, String email, LocalDateTime birthDate, Double baseSalary,
			Department department) {
		super(id);
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return "Id: " + id + ", Name: " + name + ", Email: " + email + ", Birth Date: " + birthDate.format(dtf)
				+ ", Base Salary: " + baseSalary + ", Department: " + department;
	}
}
