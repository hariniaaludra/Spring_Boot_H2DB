package com.aaludra.spring.jpa.h2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.repository.StudentRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String course) {
		try {
			List<Student> students = new ArrayList<>();

			if (course == null)
				studentRepository.findAll().forEach(students::add);
			else
				studentRepository.findBycourseContaining(course).forEach(students::add);
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
		Optional<Student> studentData = studentRepository.findById(id);
		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/students")
	public  ResponseEntity<Student> createStudent(@RequestBody Student student) {
		try {
			Student students = studentRepository
					.save(new Student( 0, student.getStudentname(),student.getRollnumber(),student.getCourse(),student.getDegree(),
							student.getDob(),student.getStatus(),student.getCreatedby(),student.getCreateddate(),
							student.getUpdatedby(),student.getUpdateddate()));
			return new ResponseEntity<>(students, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
		Optional<Student> studentData = studentRepository.findById(id);

		if  (studentData.isPresent()) {
			Student tblstudent = studentData.get();
			tblstudent.setStudentname(student.getStudentname());
			tblstudent.setRollnumber(student.getRollnumber());
			tblstudent.setCourse(student.getCourse());
			tblstudent.setDegree(student.getDegree());
			tblstudent.setDob(student.getDob());
			tblstudent.setStatus(student.getStatus());
			tblstudent.setCreatedby(student.getCreatedby());
			tblstudent.setCreateddate(student.getCreateddate());
			tblstudent.setUpdatedby(student.getUpdatedby());
			tblstudent.setUpdateddate(student.getUpdateddate());

			return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
		try {
			studentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/students")
	public ResponseEntity<HttpStatus> deleteAllStudents() {
		try {
			studentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
