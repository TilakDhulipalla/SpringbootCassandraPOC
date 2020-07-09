package com.example.springrestdb.model;




import java.time.LocalDate;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;



@Table("employee")
public class Employee {


	@PrimaryKey
	private EmployeePrimaryKey employeePrimaryKey;

	@Column("dateofbirth")
	private LocalDate dateofbirth;

	public Employee(EmployeePrimaryKey employeePrimaryKey, LocalDate dateofbirth) {
		super();
		this.employeePrimaryKey = employeePrimaryKey;
		this.dateofbirth = dateofbirth;
	}

	public Employee(EmployeePrimaryKey employeePrimaryKey) {

		this.employeePrimaryKey = employeePrimaryKey;
	}

	public Employee() {
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public EmployeePrimaryKey getEmployeePrimaryKey() {
		return employeePrimaryKey;
	}

	public void setEmployeePrimaryKey(EmployeePrimaryKey employeePrimaryKey) {
		this.employeePrimaryKey = employeePrimaryKey;
	}

}
