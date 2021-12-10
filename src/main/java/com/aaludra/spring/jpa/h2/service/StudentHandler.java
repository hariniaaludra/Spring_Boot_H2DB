package com.aaludra.spring.jpa.h2.service;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.enum1.StatusEnum;
import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.repository.StudentRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.Studentinput;
import com.aaludra.spring.jpa.h2.view.Studentview;
import com.aaludra.spring.jpa.h2.view.Studentxml;
import com.aaludra.spring.jpa.h2.view.Userinput;
import com.aaludra.spring.jpa.h2.view.Userslist;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	public Student updateStudent(long id, Studentview studentview) {
		Optional<Student> studentData = studentRepository.findById((long) id);
		if (studentData.isPresent()) {
			Student student = this.buildStudent(studentview);
			student.setId(id);
			studentRepository.save(student);
			return student;
		}

		return null;
	}

	public Student createstudent(Studentview studentview) {
		Student student = this.buildStudent(studentview);
		return studentRepository.save(new Student(0, student.getStudentname(), student.getRollnumber(),
				student.getCourse(), student.getDegree(), student.getDob(), student.getStatus(), student.getCreatedby(),
				DateUtil.getCurrentTimeStamp(), student.getUpdatedby(), student.getCreateddate()));

	}

	private Student buildStudent(Studentview studentview) {
		Student tblstudent = new Student();

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

		return tblstudent;
	}

	public void testJsonToObject() {

		ObjectMapper objectMapper = new ObjectMapper();

		// read json file and convert to customer object
		Studentview studentview = null;
		try {
			studentview = objectMapper.readValue(new File("file.json"), Studentview.class);
			Student student = this.buildStudent(studentview);
			student.setCreatedby("Admin");
			student.setUpdatedby("Admin");
			student.setCreateddate(DateUtil.getCurrentTimeStamp());
			student.setUpdateddate(DateUtil.getCurrentTimeStamp());
		System.out.println(student);
			studentRepository.save(student);
		

		} catch (IOException e) {
			
			e.printStackTrace();
		}

		System.out.println(studentview);

	}
	public void testXmlToObject() throws JAXBException, FileNotFoundException {
		File file = new File("studentinput.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Studentinput.class);
		
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Studentinput std = (Studentinput) jaxbUnmarshaller.unmarshal(file);
		
		List<Studentxml> list = std.getStudentlist();
		List<Studentxml> slist=new ArrayList<>();
		for (Studentxml stu : list) {
			System.out.println(stu.getStudentname() + " " + stu.getRollnumber() + " " + stu.getCourse() + " " + stu.getDegree() + " "
					+ stu.getDob() + " " + stu.getStatus() + " " + stu.getCreatedby() + " " + stu.getCreateddate()
					+ " " + stu.getUpdatedby() + " " + stu.getUpdateddate());
			
			 studentRepository.save( new Student());
				slist.add(stu);

		}

	}
	}


