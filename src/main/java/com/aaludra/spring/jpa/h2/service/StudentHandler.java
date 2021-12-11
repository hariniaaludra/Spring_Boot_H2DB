package com.aaludra.spring.jpa.h2.service;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.repository.StudentRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;

import java.util.List;
import java.util.Optional;

@Service
public class StudentHandler {
	@Inject
	StudentRepository studentRepository;

	public List<Student> getAllstudents(String course) {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return students;
	}

	public Optional<Student> getStudentById(long id) {

		return studentRepository.findById(id);

	}

	public Student createstudent(Student student) {

		return studentRepository.save(new Student(0, student.getStudentname(), student.getRollnumber(),
				student.getCourse(), student.getDegree(), student.getDob(), student.getStatus(), student.getCreatedby(),
				DateUtil.getCurrentTimeStamp(), student.getUpdatedby(), student.getCreateddate()));

	}

	public long deletestudent(long id) {
		studentRepository.deleteById(id);
		return id;
	}

	public ResponseEntity<HttpStatus> deleteAllStudent() {
		studentRepository.deleteAll();
		return null;

	}

	public Student updateStudent(Student studentHandler) {
		studentRepository.save(studentHandler);
		return studentHandler;
	}
}
