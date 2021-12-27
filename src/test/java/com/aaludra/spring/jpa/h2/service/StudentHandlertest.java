package com.aaludra.spring.jpa.h2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.repository.StudentRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.Studentoutput;
import com.aaludra.spring.jpa.h2.view.Studentview;

@ExtendWith(MockitoExtension.class)
class StudentHandlertest {
	@Mock
	private StudentRepository studentRepository=Mockito.mock(StudentRepository.class);
	@InjectMocks
	StudentHandler studenthandler;

	
	@Test
	void test() {
		Studentview student = new Studentview();
	
		student.setStudentname("Harini");
		student.setRollnumber("13");
		student.setCourse("CS");
		student.setDegree("BE");
		//student.setDob(DateUtil.convertStringToDate("1999-08-04"));
		student.setStatus("Active");
		student.setCreatedby("Admin");
		student.setCreateddate(null);
		student.setUpdatedby("Admin");
		student.setUpdateddate(null);

		
		List<Studentview> studentlist = new ArrayList<>();
		studentlist.add(student);
		//when(studentRepository.findAll()).thenReturn(studentlist);
		List<Studentoutput> students = studenthandler.getAllstudents();
		assertEquals(studentlist, students);
	}
	@Test
	void test1() {
		Student student = new Student();
		student.setId(0);
		student.setStudentname("Harini");
		student.setRollnumber(13);
		student.setCourse("CS");
		student.setDegree("BE");
		student.setDob(DateUtil.convertStringToDate("1999-08-04"));
		student.setStatus("Active");
		student.setCreatedby("Admin");
		student.setCreateddate(null);
		student.setUpdatedby("Admin");
		student.setUpdateddate(null);

		Student students = new Student();
		students.setId(1);
		students.setStudentname("Harini");
		students.setRollnumber(13);
		students.setCourse("CS");
		students.setDegree("BE");
		students.setDob(DateUtil.convertStringToDate("1999-08-04"));
		students.setStatus("Active");
		students.setCreatedby("Admin");
		students.setCreateddate(null);
		students.setUpdatedby("Admin");
		students.setUpdateddate(null);

		List<Student> studentlist = new ArrayList<>();
		studentlist.add(student);
		studentlist.add(students);
		when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(students));
		assertEquals(student,students);
	}
}


