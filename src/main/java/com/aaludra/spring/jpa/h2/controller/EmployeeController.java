package com.aaludra.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aaludra.spring.jpa.h2.model.Employee;
import com.aaludra.spring.jpa.h2.repository.EmployeeRepository;
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@GetMapping("/TBL_EMPLOYEE")
 public ResponseEntity<List<Employee>>getAllEmployee(@RequestParam(required=false)String createdBy){
	 try {
	 List<Employee>TBL_EMPLOYEE = new ArrayList<Employee>();
			 if (createdBy== null ) 
				 employeeRepository.findAll().forEach(TBL_EMPLOYEE::add);
			  else
				  employeeRepository.findBycreatedByContaining(createdBy).forEach(TBL_EMPLOYEE::add);
			 if(TBL_EMPLOYEE.isEmpty()) {
				 return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
			 }
			 return new ResponseEntity<>(HttpStatus.OK);
	 }catch(Exception e) {
		 
	 }
	return null;
 }

}
