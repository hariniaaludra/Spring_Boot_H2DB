package com.aaludra.spring.jpa.h2.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaludra.spring.jpa.h2.model.Employee;
import com.aaludra.spring.jpa.h2.model.Tutorial;

public interface EmployeeRepository extends JpaRepository <Employee, Integer>{

	 List<Employee> findByempName(String empName);

	  List<Employee> findBycreatedByContaining(String createdBy);

}
