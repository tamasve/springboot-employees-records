package com.practice.employeesrecords.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.practice.employeesrecords.entity.Employee;

@Repository
public interface PagingEmployeeRepo extends PagingAndSortingRepository<Employee, Long> {

}
