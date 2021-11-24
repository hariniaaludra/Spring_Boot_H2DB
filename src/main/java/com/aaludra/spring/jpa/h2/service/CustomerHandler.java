package com.aaludra.spring.jpa.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.model.Customer;
import com.aaludra.spring.jpa.h2.repository.CustomerRepository;

@Service
public class CustomerHandler {
	
	@Inject
	 CustomerRepository customerRepository;
	
	public List<Customer> getAllcustomers(String createdBy) {
		
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		
		return customers;
	}
	public Optional<Customer> getCustomerById(long id){
		Optional<Customer> customerData = customerRepository.findById(id);
		return customerData;
		
	}
	public  Customer createcustomer(Customer customer) {
		Customer customerobj = customerRepository.save(new Customer(customer.getCustname(), customer.getCustId(), customer.getCity(),
				customer.getDob(), customer.getGstin(), customer.getStatus(), customer.getCreatedBy(),
				customer.getCreatedDate(), customer.getUpdatedBy(), customer.getUpdatedDate()));
		return customerobj;
	}
	
	public long deletecustomer(long id) {
		customerRepository.deleteById(id);
		return id;
	}
	public ResponseEntity<HttpStatus> deleteAllCustomer() {
		customerRepository.deleteAll();
		return null;
		
	}
	public List<Customer>findBycustname() {
		List<Customer> customer = customerRepository.findBycustname("custname");
		
		return customer;
		
	}
	
	public   Customer updateCustomer(Customer customerobj) {
		customerRepository.save(customerobj);
		return customerobj;
	}
	

	
}

	


	