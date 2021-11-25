package com.aaludra.spring.jpa.h2.controller;

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
import com.aaludra.spring.jpa.h2.service.StudentHandler;
import com.aaludra.spring.jpa.h2.view.Studentview;
import java.util.List;
import java.util.Optional;
import com.aaludra.spring.jpa.h2.util.DateUtil;

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

	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		try {

			Student students = studentHandler.createstudent(student);

			return new ResponseEntity<>(students, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Studentview studentview) {
		Optional<Student> studentData = studentHandler.getStudentById(id);
		if (studentData.isPresent()) {
			Student tblstudent = studentData.get();

			tblstudent.setStudentname(studentview.getStudentname());
			tblstudent.setRollnumber(Integer.parseInt(studentview.getRollnumber()));
			tblstudent.setCourse(studentview.getCourse());
			tblstudent.setDegree(studentview.getDegree());
			tblstudent.setDob(DateUtil.convertStringToDate(studentview.getDob()));
			tblstudent.setStatus(studentview.getStatus());
			tblstudent.setCreatedby(studentview.getCreatedby());
			tblstudent.setCreateddate(DateUtil.convertStringToTimestamp(studentview.getCreateddate()));
			tblstudent.setUpdatedby(studentview.getUpdatedby());
			tblstudent.setUpdateddate(DateUtil.convertStringToTimestamp(studentview.getUpdateddate()));

			return new ResponseEntity<>(studentHandler.updateStudent(tblstudent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

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
