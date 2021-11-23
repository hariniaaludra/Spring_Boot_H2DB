package com.aaludra.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.aaludra.spring.jpa.h2.model.User;

import com.aaludra.spring.jpa.h2.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUser(@RequestParam(required = false) String username) {
		try {
			
			List<User> list = new ArrayList<User>();
			
			if (username == null) {
				userRepository.findAll().forEach(list::add);
			}
		
			if (list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> userData = userRepository.findById((int) id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			
			//validate
			
			User userobject = userRepository
					.save(new User(0, user.getUsername(), user.getDisplayname(), user.getPassword(), user.getDob(),user.getPhoneno(), user.getStatus(),
							user.getCreatedby(),user.getCreateddate(),user.getUpdatedby(),user.getUpdateddate()));
			return new ResponseEntity<>(userobject, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById((int) id);

		if (userData.isPresent()) {
			User userobj = userData.get();
			userobj.setUsername(user.getUsername());
			userobj.setDisplayname(user.getDisplayname());
			userobj.setPassword(user.getPassword());
			userobj.setDob(user.getDob());
			userobj.setPhoneno(user.getPhoneno());
			userobj.setStatus(user.getStatus());
			userobj.setCreatedby(user.getCreatedby());
			userobj.setCreateddate(user.getCreateddate());
			userobj.setUpdatedby(user.getUpdatedby());
			userobj.setUpdateddate(user.getUpdateddate());
			return new ResponseEntity<>(userRepository.save(userobj), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userRepository.deleteById((int) id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user")
	public ResponseEntity<HttpStatus> deleteAllUser() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
