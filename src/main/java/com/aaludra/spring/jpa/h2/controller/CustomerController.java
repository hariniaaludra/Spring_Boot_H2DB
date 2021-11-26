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

import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Customer;

import com.aaludra.spring.jpa.h2.service.CustomerHandler;
import com.aaludra.spring.jpa.h2.util.CustDateUtils;

import com.aaludra.spring.jpa.h2.validationCustomer.CustomerValidation;

import com.aaludra.spring.jpa.h2.validationCustomer.ErrorMessages;
import com.aaludra.spring.jpa.h2.view.CustomerView;


@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerHandler handler;

	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllcustomers(@RequestParam(required = false) String createdBy) {
		try {
			List<Customer> customers = handler.getAllcustomers(createdBy);

			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
		Optional<Customer> customerData = handler.getCustomerById(id);

		if (customerData.isPresent()) {
			return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/customer")
	public ResponseEntity<?> createcustomer(@RequestBody Customer customer) {
		
		try {
			CustomerValidation customerval=new CustomerValidation();
			customerval.validate(customer);
			
			Customer customerobj = handler.createcustomer(customer);
			CustomerView viewobj=new CustomerView();
			
			viewobj.setCustname(customerobj.getCustname());
			viewobj.setCustId(customerobj.getCustId());
			viewobj.setCity(customerobj.getCity());
			viewobj.setDob(CustDateUtils.convertstringtodate(customerobj.getDob()));
			viewobj.setGstin(customerobj.getGstin());
			viewobj.setStatus(customerobj.getStatus());
			viewobj.setCreatedBy(customerobj.getCreatedBy());
			viewobj.setCreatedDate(customerobj.getCreatedDate().toString());
			viewobj.setUpdatedBy(customerobj.getUpdatedBy());
			viewobj.setUpdatedDate(customerobj.getUpdatedDate().toString());
			
			

			return new ResponseEntity<>(customerobj, HttpStatus.CREATED);
			
		}catch(InvalidRequestException e) {
			return new ResponseEntity<>(new ErrorMessages(HttpStatus.BAD_REQUEST.value(),e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerView customerview) {
		Optional<Customer> customerData = handler.getCustomerById(id);

		if (customerData.isPresent()) {
			Customer customerobj = customerData.get();
			customerobj.setCustname(customerview.getCustname());
			customerobj.setCustId( customerview.getCustId());
			customerobj.setCity(customerview.getCity());
			customerobj.setDob(CustDateUtils.convertStringToDate(customerview.getDob()));
			customerobj.setGstin(customerview.getGstin());
			customerobj.setStatus(customerview.getStatus());
			customerobj.setCreatedBy(customerview.getCreatedBy());
			customerobj.setCreatedDate(CustDateUtils.convertStringToTimestamp(customerview.getCreatedDate()));
			customerobj.setUpdatedBy(customerview.getUpdatedBy());
			customerobj.setUpdatedDate(CustDateUtils.convertStringToTimestamp(customerview.getUpdatedDate()));
			
			return new ResponseEntity<>(handler.updateCustomer(customerobj), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<HttpStatus> deletecustomer(@PathVariable("id") long id) {
		try {
			handler.deletecustomer(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/customer")
	public ResponseEntity<HttpStatus> deleteAllCustomer() {
		try {
			handler.deleteAllCustomer();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/customer/cust_name")

	public ResponseEntity<List<Customer>> findBycustname() {
		try {
			List<Customer> customer = handler.findBycustname();

			if (customer.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}