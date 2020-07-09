package com.example.springrestdb.mapper;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.springrestdb.model.Employee;
import com.example.springrestdb.model.EmployeePrimaryKey;
import com.example.springrestdb.model.EmployeeResponse;
import com.example.springrestdb.model.ErrorResponse;
import com.example.springrestdb.service.EmployeeService;

@Component
public class EmployeeMapper {
	private static final Logger log = LoggerFactory.getLogger(EmployeeMapper.class);

	@Autowired
	EmployeeService employeeService;

	public List<EmployeeResponse> mapEmployeeData() {

		List<EmployeeResponse> empResponse = new ArrayList<EmployeeResponse>();
		List<Employee> empList = employeeService.getListAllEmployee();

		for (Employee emp : empList) {

			EmployeeResponse empResp = new EmployeeResponse();

			empResp.setId(emp.getEmployeePrimaryKey().getId());
			empResp.setLastname(emp.getEmployeePrimaryKey().getLastname());
			empResp.setFirstname(emp.getEmployeePrimaryKey().getFirstname());
			empResp.setDateofbirth(emp.getDateofbirth());
			log.info("Get list of all employees mapped : {}",empResp.toString());
			empResponse.add(empResp);

		}

		return empResponse;

	}
//fetch mapper
	public ResponseEntity<?> fetchRecordByIdMap(EmployeePrimaryKey empPkey) {
		log.info("IN FETCH METHOD MAPPER");
		Employee emp = employeeService.fetchRecordById(empPkey).getBody();
		if(emp!=null) {
		EmployeeResponse empResp = new EmployeeResponse();
		empResp.setId(emp.getEmployeePrimaryKey().getId());
		empResp.setLastname(emp.getEmployeePrimaryKey().getLastname());
		empResp.setFirstname(emp.getEmployeePrimaryKey().getFirstname());
		

		empResp.setDateofbirth(emp.getDateofbirth());
		
		log.info("Fetch Record response mapped : {}",empResp.toString());
		return new ResponseEntity<>(empResp,HttpStatus.OK);
		}
		ErrorResponse error = new ErrorResponse();
		error.setMessage("Record not found");
		error.setStatus("Failed");
		return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		

	}

}
