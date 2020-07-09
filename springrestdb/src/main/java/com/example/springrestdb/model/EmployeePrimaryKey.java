package com.example.springrestdb.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.stereotype.Component;

@Component
@PrimaryKeyClass
public class EmployeePrimaryKey {

	@PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private Integer id;

	@PrimaryKeyColumn(name = "lastname", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String lastname;

	@PrimaryKeyColumn(name = "firstname", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private String firstname;
	

	public EmployeePrimaryKey(Integer id, String lastname, String firstname) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
	}

	public EmployeePrimaryKey() {
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	

}
