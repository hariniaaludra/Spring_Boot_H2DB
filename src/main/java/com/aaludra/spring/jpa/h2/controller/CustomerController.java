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

import com.aaludra.spring.jpa.h2.model.Customer;

import com.aaludra.spring.jpa.h2.service.CustomerHandler;

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
	public ResponseEntity<Customer> createcustomer(@RequestBody Customer customer) {
		try {
			Customer customerobj = handler.createcustomer(customer);

			return new ResponseEntity<>(customerobj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		Optional<Customer> customerData = handler.getCustomerById(id);

		if (customerData.isPresent()) {
			Customer customerobj = customerData.get();
			customerobj.setCustname(customer.getCustname());
			customerobj.setCustId(customer.getCustId());
			customerobj.setCity(customer.getCity());
			customerobj.setDob(customer.getDob());
			customerobj.setGstin(customer.getGstin());
			customerobj.setStatus(customer.getStatus());
			customerobj.setCreatedBy(customer.getCreatedBy());
			customerobj.setCreatedDate(customer.getCreatedDate());
			customerobj.setUpdatedBy(customer.getUpdatedBy());
			customerobj.setUpdatedDate(customer.getUpdatedDate());
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