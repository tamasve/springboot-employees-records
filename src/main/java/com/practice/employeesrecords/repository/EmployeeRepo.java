package com.practice.employeesrecords.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.employeesrecords.entity.Employee;

@Repository
public interface EmployeeRepo extends ListCrudRepository <Employee, Long>{
	
	List<Employee> findAllByResidenceIgnoreCase(String residence);

	List<Employee> findAllByResidenceIgnoreCaseContaining(String residence);
	
	@Query(value="SELECT * FROM employees WHERE upper(firstname) like upper(?1)", nativeQuery=true)
	List<Employee> findAllByFirstName(String name);
	
	List<Employee> findByFirstNameContainingIgnoreCase(String name);

	List<Employee> findByLastNameContainingIgnoreCase(String name);

}
