package com.practice.employeesrecords.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.employeesrecords.entity.Employee;
import com.practice.employeesrecords.service.EmployeeService;



@RestController
public class EmployeeRestController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	
	@GetMapping("/json")
	public List<Employee> restBasic() {
		return employeeService.getAllEmployees();
	}

}
