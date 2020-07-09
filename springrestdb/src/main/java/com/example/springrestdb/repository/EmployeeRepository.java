package com.example.springrestdb.repository;





import java.util.Optional;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.springrestdb.model.Employee;
import com.example.springrestdb.model.EmployeePrimaryKey;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, EmployeePrimaryKey> {
	
	@Query(
		      "select * from employee where id=:id and lastname=:lastname and firstname=:firstname allow filtering")
		  Optional<Employee> findEmp(
		      @Param("id") Integer id, @Param("lastname") String lastname,@Param("firstname") String firstname);
		
	@Query(
	      "delete from employee where id=:id and lastname=:lastname and firstname=:firstname ")
	  void deleteEmp(
			  @Param("id") Integer id, @Param("lastname") String lastname,@Param("firstname") String firstname);
	
}