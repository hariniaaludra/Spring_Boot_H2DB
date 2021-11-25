package com.aaludra.spring.jpa.h2.controller;

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

import com.aaludra.spring.jpa.h2.validationEmployee.EmplyeeValidation;
import com.aaludra.spring.jpa.h2.validationEmployee.ErrorMesseges;
import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeHandler empHandler;

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(required = false) String createdBy) {
		try {
			List<Employee> listEmployee = empHandler.getAllEmployee(createdBy);
			if (listEmployee.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getAllEmployeeById(@PathVariable("id") long id) {
		Optional<Employee> employeeData = empHandler.getAllEmployeeById(id);
		if (employeeData.isPresent()) {

			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/employee")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {

		try {
			
			EmplyeeValidation emp = new EmplyeeValidation();
			emp.Validat(employee);
			Employee employeeObj = empHandler.createEmployee(employee);
			return new ResponseEntity<>(employeeObj, HttpStatus.CREATED);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<>(new ErrorMesseges(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		
		Optional<Employee> employeeData = empHandler.getAllEmployeeById(id);

		if (employeeData.isPresent()) {
			Employee employeeObj = employeeData.get();
			employeeObj.setEmpName(employee.getEmpName());
			employeeObj.setEmpId(employee.getEmpId());
			employeeObj.setEmpPhonenumber(employee.getEmpPhonenumber());
			employeeObj.setEmpAddress(employee.getEmpAddress());
			employeeObj.setEmpDoj(employee.getEmpDoj());
			employeeObj.setStatus(employee.getStatus());
			employeeObj.setCreatedBy(employee.getCreatedBy());
			employeeObj.setCreatedDate(employee.getCreatedDate());
			employeeObj.setUpdatedBy(employee.getUpdatedBy());
			employeeObj.setUpdatedDate(employee.getUpdatedDate());
			return new ResponseEntity<>(empHandler.updateCustomer(employeeObj), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
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
