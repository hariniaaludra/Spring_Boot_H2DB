package com.aaludra.spring.jpa.h2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaludra.spring.jpa.h2.model.Customer;

import com.aaludra.spring.jpa.h2.view.CustomerViewOutput;


public interface CustomerRepository extends JpaRepository <Customer, Long>{
	List<Customer> findBycustname(String custname);
	List<Customer> findBycreatedByContaining(String createdBy);
	
	
	
	
	

}
