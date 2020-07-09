package com.example.springrestdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springrestdb.model.Employee;
import com.example.springrestdb.model.EmployeePrimaryKey;
import com.example.springrestdb.model.EmployeeStatusResponse;
import com.example.springrestdb.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getListAllEmployee() {

		log.info("entered");
		List<Employee> lc = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(lc::add);
		return lc;

	}

	public EmployeeStatusResponse saveRecordInDb(Employee emp) {
		EmployeeStatusResponse empStatusObj = new EmployeeStatusResponse();
		try {
			log.info("IN SAVE METHOD");
			employeeRepository.save(emp);
			empStatusObj.setId(emp.getEmployeePrimaryKey().getId());
			empStatusObj.setStatus("Success");
			return empStatusObj;
		} catch (Exception e) {
			empStatusObj.setId(emp.getEmployeePrimaryKey().getId());
			empStatusObj.setStatus("Failed");
			return empStatusObj;
		}

	}

	public ResponseEntity<Employee> fetchRecordById(EmployeePrimaryKey id) {
		try {
			Optional<Employee> getEmpData = employeeRepository.findEmp(id.getId(), id.getLastname(), id.getFirstname());

			log.info("EmpByID fetched from DB ");
			return new ResponseEntity<>(getEmpData.get(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	public EmployeeStatusResponse DeleteRecordById(EmployeePrimaryKey id) {
		EmployeeStatusResponse empStatusObj = new EmployeeStatusResponse();

		try {
			employeeRepository.deleteEmp(id.getId(), id.getLastname(), id.getFirstname());
			empStatusObj.setId(id.getId());
			empStatusObj.setStatus("Success");
			return empStatusObj;
		} catch (Exception e) {
			empStatusObj.setId(id.getId());
			empStatusObj.setStatus("Failed");
			return empStatusObj;
		}
	}

	public EmployeeStatusResponse UpdateRecordInDb(Employee emp) {
		EmployeeStatusResponse empStatusObj = new EmployeeStatusResponse();
		try {
			Employee empById = fetchRecordById(emp.getEmployeePrimaryKey()).getBody();
			empById.setEmployeePrimaryKey(emp.getEmployeePrimaryKey());
			empById.setDateofbirth(emp.getDateofbirth());
			employeeRepository.save(empById);

			empStatusObj.setId(emp.getEmployeePrimaryKey().getId());
			empStatusObj.setStatus("Success");
			return empStatusObj;
		} catch (Exception e) {
			empStatusObj.setId(emp.getEmployeePrimaryKey().getId());
			empStatusObj.setStatus("Failed");
			return empStatusObj;
		}

	}
}
