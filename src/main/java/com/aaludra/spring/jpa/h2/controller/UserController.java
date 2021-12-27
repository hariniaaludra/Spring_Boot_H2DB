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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.service.UserHandler;
import com.aaludra.spring.jpa.h2.validation.ErrorMessages;
import com.aaludra.spring.jpa.h2.validation.Uservalidation;
import com.aaludra.spring.jpa.h2.view.UserInputView;
import com.aaludra.spring.jpa.h2.view.UserJsonToObj;
import com.aaludra.spring.jpa.h2.view.UserOutputView;
import com.aaludra.spring.jpa.h2.view.UserObjToXml;
import com.aaludra.spring.jpa.h2.view.UserXmlToObj;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserHandler userHandler;

	@GetMapping("/user")
	public ResponseEntity<List<UserOutputView>> getAllUser(@RequestParam(required = false) String username) {
		try {
			List<UserOutputView> list = userHandler.getAllUser();
			if (list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
			return new ResponseEntity<>(list, HttpStatus.OK);} 
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
		}
	

	@GetMapping("/user/{id}")
	public ResponseEntity<UserOutputView> getUserById(@PathVariable("id") long id) {
		Optional<UserOutputView> userData = userHandler.getUserById((int) id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else 
		{
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/user/process/xml")
	public ResponseEntity<UserXmlToObj> testXmlToObject() {
		try {
			userHandler.testXmlToObject();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null ;
	}
	
	@PostMapping("/user/process/json")
	public ResponseEntity<UserJsonToObj> testjsonToObject() {
		try {
			userHandler.testjsonToObject();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null ;
	}
	
	@PostMapping("/user/reverse/xml")
	public ResponseEntity<UserObjToXml> testObjectToXml() throws JsonMappingException, SQLException, IOException { 
		 try{
	         userHandler.testObjectToXml();
	        } catch (JAXBException e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
		 return null;
	}
	
	@PostMapping("/user/reverse/json")
	public ResponseEntity<UserJsonToObj> testObjectToJson() {
		try {
			userHandler.testObjectToJson();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null;
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody UserInputView userview) {
		try {
			Uservalidation u = new Uservalidation();
			u.validate(userview);
			UserOutputView userobject = userHandler.createUser(userview);
			return new ResponseEntity<>(userobject.toString(), HttpStatus.CREATED);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<>(new ErrorMessages(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<UserOutputView> updateUser(@PathVariable("id") Integer id, @RequestBody UserInputView userview) {
		
			return new ResponseEntity<>(userHandler.updateUser( userview,id), HttpStatus.OK);

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userHandler.deleteUserById((int) id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user")
	public ResponseEntity<HttpStatus> deleteAllUser() {
		try {
			userHandler.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
