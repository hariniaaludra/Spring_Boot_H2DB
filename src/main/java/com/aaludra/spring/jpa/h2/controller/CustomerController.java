package com.aaludra.spring.jpa.h2.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBException;

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
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.validation.CustomerValidation;
import com.aaludra.spring.jpa.h2.validation.ErrorMessages;
import com.aaludra.spring.jpa.h2.view.CustomerViewInput;
import com.aaludra.spring.jpa.h2.view.CustomerViewOutput;
import com.aaludra.spring.jpa.h2.view.Customerinput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerHandler handler;

	@GetMapping("/customer")
	public ResponseEntity<List<CustomerViewOutput>> getAllcustomers(@RequestParam(required = false) String createdBy) {
		try {
			List<CustomerViewOutput> customers = handler.getAllcustomers();

			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerViewOutput> getCustomerById(@PathVariable("id") long id) {
		Optional<CustomerViewOutput> customerData = handler.getCustomerById(id);

		if (customerData.isPresent()) {
			return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/customers/process/json")
	public ResponseEntity<CustomerViewOutput> testJsonToObject()throws JsonParseException,JsonMappingException,IOException{
		try {
			handler.testJsonToObject();
			return new ResponseEntity<>(null,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/customer/reverse/json")
	public ResponseEntity<List<CustomerViewOutput>> objectToJson()throws JsonParseException,JsonMappingException,IOException {
		try {
			handler.dbDataToJsonfile();

			return new ResponseEntity<>(null,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/customer")
	public ResponseEntity<?> createcustomer(@RequestBody CustomerViewInput customerview) {
		
		try {
			CustomerValidation customerval=new CustomerValidation();
			customerval.validate(customerview);
			
			CustomerViewOutput customerobj = handler.createcustomer(customerview);
			
			

			return new ResponseEntity<>(customerobj, HttpStatus.CREATED);
			
		}catch(InvalidRequestException e) {
			return new ResponseEntity<>(new ErrorMessages(HttpStatus.BAD_REQUEST.value(),e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<CustomerViewOutput> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerViewInput customerview) {
		//Optional<Customer> customerData = handler.getCustomerById(id);


			return new ResponseEntity<>(handler.updateCustomer(id,customerview), HttpStatus.OK);
		
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
	@PostMapping("/customers/process/xml")
	public ResponseEntity<CustomerViewOutput> testXmlToObject(){
		try {
			handler.testXmlToObject();
		}catch(FileNotFoundException | JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
		
	}
	@GetMapping("/customer/reverse/xml")
	public ResponseEntity<List<CustomerViewOutput>> objectToXml()throws SQLException {
		try {
			handler.dBDataToxml();

			return new ResponseEntity<>(null,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customer/cust_name")

	public ResponseEntity<List<CustomerViewOutput>> findBycustname() {
		try {
			List<CustomerViewOutput> customer = handler.findBycustname();

			if (customer.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}