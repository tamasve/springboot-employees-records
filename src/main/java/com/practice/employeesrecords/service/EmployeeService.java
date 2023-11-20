package com.practice.employeesrecords.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.practice.employeesrecords.entity.Employee;



public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee saveEmployee(Employee employee);
	
	public List<Employee> findEmployeesByResidence(String residence);

	public List<Employee> findEmployeesByName(String name);
	
	public List<Employee> findEmployeesOnPages(int pageNumber);
	
	public List<Employee> findEmployeesOnPagesSorted(int pageNumber, String field, boolean ascending);
	
	public int countOfEmployeePages();

}
