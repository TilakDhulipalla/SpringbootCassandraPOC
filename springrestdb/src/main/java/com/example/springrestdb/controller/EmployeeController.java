package com.example.springrestdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestdb.mapper.EmployeeMapper;
import com.example.springrestdb.model.Employee;
import com.example.springrestdb.model.EmployeePrimaryKey;
import com.example.springrestdb.model.EmployeeResponse;
import com.example.springrestdb.model.EmployeeStatusResponse;
import com.example.springrestdb.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeMapper employeeMapper;


	@GetMapping("/employee")
	public List<EmployeeResponse> getAllEmployee() {

		return employeeMapper.mapEmployeeData();

	}

	@PostMapping("/employee")
	public EmployeeStatusResponse saveRecord(@RequestBody Employee emp) {
		return employeeService.saveRecordInDb(emp);
	}

	@GetMapping("/employee/id")
	public ResponseEntity<?> fetchRecord(@RequestBody EmployeePrimaryKey id) {

		return employeeMapper.fetchRecordByIdMap(id);
	}

	@DeleteMapping("/employee/delete")
	public EmployeeStatusResponse DeleteRecord(@RequestBody EmployeePrimaryKey id) {
		return employeeService.DeleteRecordById(id);

	}

	@PutMapping("/employee/update")
	public EmployeeStatusResponse UpdateRecord(@RequestBody Employee emp) {
		return employeeService.UpdateRecordInDb(emp);

	}

}
