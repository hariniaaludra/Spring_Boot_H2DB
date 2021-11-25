package com.aaludra.spring.jpa.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.aaludra.spring.jpa.h2.model.Employee;
import com.aaludra.spring.jpa.h2.repository.EmployeeRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;

@Service
public class EmployeeHandler {

	@Inject
	EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {
		return employeeRepository
				.save(new Employee(employee.getEmpName(), employee.getEmpId(), employee.getEmpPhonenumber(),
						employee.getEmpAddress(), employee.getEmpDoj(), employee.getStatus(), employee.getCreatedBy(),
						DateUtil.getCurrentTimeStamp(), employee.getUpdatedBy(), employee.getUpdatedDate()));
	}

	public ResponseEntity<HttpStatus> deleteAll() {
		employeeRepository.deleteAll();
		return null;
	}

	public Employee updateCustomer(Employee employeeObj) {
		employeeRepository.save(employeeObj);
		return employeeObj;
	}

	public long deleteEmployee(long id) {
		employeeRepository.deleteById(id);
		return id;
	}

	public Optional<Employee> getAllEmployeeById(long id) {
		Optional<Employee> employeeData = employeeRepository.findById(id);
		return employeeData;
	}

	public List<Employee> getAllEmployee(String createdBy) {
		List<Employee> listEmployee = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(listEmployee::add);
		return listEmployee;
	}

}
