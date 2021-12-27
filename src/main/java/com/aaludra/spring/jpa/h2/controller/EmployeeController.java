package com.aaludra.spring.jpa.h2.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.aaludra.spring.jpa.h2.model.Employee;

import com.aaludra.spring.jpa.h2.repository.EmployeeRepository;
import com.aaludra.spring.jpa.h2.service.EmployeeHandler;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.validation.EmployeeValidation;
import com.aaludra.spring.jpa.h2.validation.ErrorMessages;
import com.aaludra.spring.jpa.h2.view.EmployeeDetailView;
import com.aaludra.spring.jpa.h2.view.EmployeeInput;
import com.aaludra.spring.jpa.h2.view.EmployeeInputObjtoJson;
import com.aaludra.spring.jpa.h2.view.EmployeeInputObjtoXml;
import com.aaludra.spring.jpa.h2.view.EmployeeViewOut;
import com.aaludra.spring.jpa.h2.view.Userinput;
import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeHandler empHandler;

	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeViewOut>> getAllEmployee(@RequestParam(required = false) String createdBy) {
		try {
			List<EmployeeViewOut> listEmployee = empHandler.getAllEmployee();
			if (listEmployee.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeViewOut> getEmployeeById(@PathVariable("id") int id) {
		Optional<EmployeeViewOut> employeeData = empHandler.getEmployeeById(id);
		if (employeeData.isPresent()) {

			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
		
	@PostMapping("/employee/process/xml")
	public ResponseEntity<EmployeeInput> testXmlToObject() {
		try {
			empHandler.testXmlToObject();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null ;
	}
	@GetMapping("/employee/reverse/xml")
	public ResponseEntity<EmployeeViewOut> testObjectToXml() {
		try {
			empHandler.testObjectToXml();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null ;
	}
	@PostMapping("/employee/process/json")
	public ResponseEntity<EmployeeInput> testJsonToObject() {
		try {
			empHandler.testJsonToObject1();
			return new ResponseEntity<>(null,HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
	}
	@GetMapping("/employee/reverse/json")
	public ResponseEntity<EmployeeViewOut> testObjectToJson() {
		try {
			empHandler.testObjectToJson();
			return new ResponseEntity<>(null,HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
	}

	
	@PostMapping("/employee")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDetailView employeeDetailsView) {

	
		try {
			ResponseEntity<List<EmployeeViewOut>> emplouyeeout = this.getAllEmployee("");
			EmployeeValidation emp = new EmployeeValidation();
			emp.validate(employeeDetailsView);
			EmployeeViewOut employeeObj = empHandler.createEmployee(employeeDetailsView);
			return new ResponseEntity<>(employeeObj, HttpStatus.CREATED);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<>(new ErrorMessages(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/employee/{id}")
	public ResponseEntity<EmployeeViewOut> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDetailView employeeDetailsView) {	
			return new ResponseEntity<>(empHandler.updateEmployee(id,employeeDetailsView), HttpStatus.OK);
		//} else {
		//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") int id) {
		try {
			empHandler.deleteEmployee(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employee")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			
			empHandler.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
}
