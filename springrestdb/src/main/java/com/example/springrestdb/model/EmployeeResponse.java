package com.example.springrestdb.model;



import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeResponse {
	
	
	private Integer id;
	private String lastname;
	private String firstname;
	private LocalDate dateofbirth;
	
	


}
