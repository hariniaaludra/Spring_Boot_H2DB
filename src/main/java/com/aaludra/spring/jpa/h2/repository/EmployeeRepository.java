package com.aaludra.spring.jpa.h2.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaludra.spring.jpa.h2.model.Employee;


public interface EmployeeRepository extends JpaRepository <Employee, Long>{

	 List<Employee> findByempName(String empName);

	  List<Employee> findBycreatedByContaining(String createdBy);

}
