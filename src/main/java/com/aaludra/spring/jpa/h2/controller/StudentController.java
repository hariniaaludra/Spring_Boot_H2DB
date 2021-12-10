package com.aaludra.spring.jpa.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.service.StudentHandler;
import com.aaludra.spring.jpa.h2.view.Studentinput;
import com.aaludra.spring.jpa.h2.view.Studentview;
import com.aaludra.spring.jpa.h2.view.Userinput;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.validation.ErrorMessages;
import com.aaludra.spring.jpa.h2.validation.StudentValidation;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class StudentController {

	
	@Autowired
	StudentHandler studentHandler;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String course) {

		try {
			List<Student> students = studentHandler.getAllstudents(course);

			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
		Optional<Student> studentData = studentHandler.getStudentById(id);
		if (studentData.isPresent()) {
			
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/user/process/xml")
	public ResponseEntity<Studentinput> testXmlToObject() {
		try {
			studentHandler.testXmlToObject();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null ;
	}
	@PostMapping("/students/process/json")
		public ResponseEntity<Studentview> testJsonToObject()  {
			try {
				studentHandler.testJsonToObject();
				return new ResponseEntity<>(null, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PostMapping("/students")

	public ResponseEntity<?> createStudent(@RequestBody Studentview student) {
		try {
			
			StudentValidation stu = new StudentValidation();
			stu.validate(student);
			Student students = studentHandler.createstudent(student);
			return new ResponseEntity<>(students, HttpStatus.CREATED);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<>(new ErrorMessages(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Studentview studentview) {
		Optional<Student> studentData = studentHandler.getStudentById(id);
			return new ResponseEntity<>(studentHandler.updateStudent(id, studentview), HttpStatus.OK);
		}
	//	} else {
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
		
	

	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
		try {
			studentHandler.deletestudent(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/students")
	public ResponseEntity<HttpStatus> deleteAllStudents() {
		try {
			studentHandler.deleteAllStudent();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
