package com.practice.employeesrecords.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.practice.employeesrecords.entity.Employee;
import com.practice.employeesrecords.repository.EmployeeRepo;
import com.practice.employeesrecords.repository.PagingEmployeeRepo;




@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// normal Repo of Employee
	
	private EmployeeRepo employeeRepo;
	
	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	// Paging and Sorting Repo of Employee
	
	private PagingEmployeeRepo pagingEmployeeRepo;
	
	@Autowired
	public void setEmployeeRepo(PagingEmployeeRepo pagingEmployeeRepo) {
		this.pagingEmployeeRepo = pagingEmployeeRepo;
	}
	
	
	// ** QUERIES ** //

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}
	

	// get all employees but with paging, only 15 on a page (in this version it is still hardcoded)...
	@Override
	public List<Employee> findEmployeesOnPages(int pageNumber) {
		return pagingEmployeeRepo.findAll( PageRequest.of(pageNumber, 15) ).toList();
	}
	
	// get all employees with paging and sorting, only 15 on a page (in this version it is still hardcoded)...
	@Override
	public List<Employee> findEmployeesOnPagesSorted(int pageNumber, String field, boolean ascending) {
		return ascending ? pagingEmployeeRepo.findAll( PageRequest.of(pageNumber, 15, Sort.by(field).ascending()) ).toList()
					: pagingEmployeeRepo.findAll( PageRequest.of(pageNumber, 15, Sort.by(field).descending()) ).toList();
	}
	
	// query the number of pages - only occasionally
	@Override
	public int countOfEmployeePages() {
		return pagingEmployeeRepo.findAll( PageRequest.of(0, 15) ).getTotalPages();
	}
	

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}


	@Override
	public List<Employee> findEmployeesByResidence(String residence) {
		List<Employee> employees = employeeRepo.findAllByResidenceIgnoreCase(residence);
		return employees.size() != 0 ? employees : employeeRepo.findAllByResidenceIgnoreCaseContaining(residence);
	}


	// search employees by name fragments either in first or in last names...
	@Override
	public List<Employee> findEmployeesByName(String name) {
		List<Employee> list = employeeRepo.findByFirstNameContainingIgnoreCase(name);
		list.addAll(employeeRepo.findByLastNameContainingIgnoreCase(name));
		return list;
	}


}
