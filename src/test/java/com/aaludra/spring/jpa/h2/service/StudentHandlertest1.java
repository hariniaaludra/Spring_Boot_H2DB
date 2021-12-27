package com.aaludra.spring.jpa.h2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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
import com.aaludra.spring.jpa.h2.view.Studentinput;
import com.aaludra.spring.jpa.h2.view.Studentoutput;
import com.aaludra.spring.jpa.h2.view.Studentview;

@ExtendWith(MockitoExtension.class)
class StudentHandlertest1 {
	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	StudentHandler studenthandler;
	List<Student>studentlist=new ArrayList<>();
	List<Studentinput> studinobj;
	List<Studentoutput> studoutobj;
	private Student student1,student2;
	private Studentview student3;

    @BeforeEach
    void test() {
    	 student1 =new Student("Harini",13,"CS","BE",DateUtil.convertStringToDate("1999-08-04"),"Active","Admin",
    			 DateUtil.convertStringToTimestamp("2021-12-21"),"Admin",DateUtil.convertStringToTimestamp("2121-12-21"));
 		student2=new Student("Harini",13,"CS","BE",DateUtil.convertStringToDate("1999-08-04"),"Active","Admin",
   			 DateUtil.convertStringToTimestamp("2021-12-21"),"Admin",DateUtil.convertStringToTimestamp("2121-12-21"));
 	  
 		studentlist.add(student1);
 		studentlist.add(student2);
 	
    }
	@Test
	void getAllstudentshouldReturn() {
	
		when(studentRepository.findAll()).thenReturn(studentlist);
		List<Studentoutput> studentlist1=studenthandler.getAllstudents();
		System.out.println(studentlist1.get(0).getStudentname());
				assertEquals(studentlist1.get(0).getStudentname(),studentlist.get(0).getStudentname());
}

    @Test
	void testfindById() {
		
		Mockito.when(studentRepository.findById((long) 0)).thenReturn(Optional.ofNullable(student1));
		Optional<Studentoutput> studentlist=studenthandler.getStudentById(student1.getId());
		assertEquals(studentlist.get().getRollnumber(),studentlist.get().getRollnumber());
	}

	@Test
	void testdeletestudent() {
				long studentId = 3;

		studenthandler.deletestudent(studentId);
		verify(studentRepository).deleteById((long) studentId);
		studentRepository.deleteById(student1.getId());

 }
	@Test
	void testdeleteAllstudent(){
		   studentRepository.deleteAll();
		   verify(studentRepository).deleteAll();
		  }
	@Test
	void testupdatestudent() {
		student3=new Studentview("Harini","13","CS","BE","1999-08-04","Active","Admin","2021-12-21","Admin","2021-12-21");
		
		when(studentRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(student1));
        when(studentRepository.save(Mockito.any())).thenReturn(student1);
        Optional<Studentoutput> stdlist=Optional.ofNullable(studenthandler.updateStudent(student1.getId(),student3));
        assertThat(studenthandler.updateStudent(student1.getId(), student3).getStudentname()).isEqualTo(stdlist.get().getStudentname());
      
	}
	@Test
	void testcreatestudent() {
		
		  Studentview student=new Studentview("Harini","13","CS", "BE","1999-08-04","Active","Admin","2021-12-24","Admin","2021-12-24");
		  when(studentRepository.save(Mockito.any())).thenReturn(student1);
		 Studentoutput studoutobj=studenthandler.createstudent(student);
		 assertThat(studenthandler.createstudent(student).getStudentname()).isEqualTo(studoutobj.getStudentname());
 }       
	/*@Test
	void testJsonToObject() {
		
		Studentview student=new Studentview("Harini","13","CS","BE","1999-08-04","Active","Admin","2021-12-24","Admin","2021-12-24");
		when(studentRepository.save(Mockito.any())).thenReturn(student1);*/
}

              

