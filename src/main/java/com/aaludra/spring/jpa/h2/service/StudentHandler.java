package com.aaludra.spring.jpa.h2.service;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.enum1.StatusEnum;
import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.repository.StudentRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.Studentinput;
import com.aaludra.spring.jpa.h2.view.Studentlist;
import com.aaludra.spring.jpa.h2.view.Studentoutput;
import com.aaludra.spring.jpa.h2.view.Studentoutputlist;
import com.aaludra.spring.jpa.h2.view.Studentview;
import com.aaludra.spring.jpa.h2.view.Studentxml;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
public class StudentHandler {
	@Inject

	StudentRepository studentRepository;

	public List<Studentoutput> getAllstudents() {
		List<Student> students = studentRepository.findAll();
		List<Studentoutput> stud = new ArrayList<>();
		for (Student student : students) {
			Studentoutput student1 = this.buildstudent1(student);
			stud.add(student1);
		}
		return stud;

	}

	public Optional<Studentoutput> getStudentById(long id) {
		Optional<Student> sts = studentRepository.findById(id);

		Studentoutput studentoutput = this.buildstudent1(sts.get());

		return Optional.of(studentoutput);

	}

	public long deletestudent(long id) {
		studentRepository.deleteById(id);
		return id;
	}

	public ResponseEntity<HttpStatus> deleteAllStudent() {
		studentRepository.deleteAll();
		return null;
	}

	public Studentoutput updateStudent(long id, Studentview studentview) {
		Optional<Student> studentData = studentRepository.findById((long) id);
		if (studentData.isPresent()) {
			Student students = (Student) this.buildStudent(studentview);
			students.setId(id);
			Studentoutput stdobj = this.buildstudent1(studentRepository.save(students));

			return stdobj;
		}
		return null;
	}

	public Studentoutput createstudent(Studentview studentview) {
		Student student = this.buildStudent(studentview);
		Studentoutput sts = this.buildstudent1(studentRepository.save(student));
		return sts;

	}

	public Student buildStudent(Studentview studentview) {
		Student tblstudent = new Student();

		tblstudent.setStudentname(studentview.getStudentname());
		tblstudent.setRollnumber(Integer.parseInt(studentview.getRollnumber()));
		tblstudent.setCourse(studentview.getCourse());
		tblstudent.setDegree(studentview.getDegree());
		tblstudent.setDob(DateUtil.convertStringToDate(studentview.getDob()));
		tblstudent.setStatus(studentview.getStatus());
		tblstudent.setCreatedby(studentview.getCreatedby());
		tblstudent.setCreateddate(DateUtil.getCurrentTimeStamp());
		tblstudent.setUpdatedby(studentview.getUpdatedby());
		tblstudent.setUpdateddate(DateUtil.getCurrentTimeStamp());

		return tblstudent;
	}

	public Studentoutput buildstudent1(Student student) {
		Studentoutput stl = new Studentoutput();
		stl.setId(Long.toString(student.getId()));
		stl.setStudentname(student.getStudentname());
		stl.setRollnumber(Integer.toString(student.getRollnumber()));
		stl.setCourse(student.getCourse());
		stl.setDegree(student.getDegree());
		stl.setDob(student.getDob().toString());
		stl.setStatus(student.getStatus());
		stl.setCreatedby(student.getCreatedby());
		stl.setCreateddate(student.getCreateddate().toString());
		stl.setUpdatedby(student.getUpdatedby());
		stl.setUpdateddate(student.getUpdateddate().toString());

		return stl;
	}

	public void testJsonToObject() throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		// read json file and convert to customer object
		Studentview studentview = null;

		Studentlist studentlists = objectMapper.readValue(new File("file.json"), Studentlist.class);
		List<Studentlist> studentlist = new ArrayList();
		List<Studentview> stdlist = studentlists.getStudentJsonlist();
		System.out.println("studentlist" + stdlist.size());

		for (Studentview stud : stdlist) {
			System.out.println(stud.getStudentname() + " " + stud.getRollnumber() + " " + stud.getCourse() + " "
					+ stud.getDegree() + " " + stud.getDob() + " " + stud.getStatus() + " " + "Admin" + " " + null + " "
					+ "Admin" + " " + null);

			Studentview obj = new Studentview(stud.getStudentname(), stud.getRollnumber(), stud.getCourse(),
					stud.getDegree(), stud.getDob(), StatusEnum.Active.name(), "Admin", stud.getCreateddate(), "Admin",
					stud.getUpdateddate());

			studentlist.add(studentlists);
			studentRepository.save(buildStudent(obj));
		}
	}

	public void testXmlToObject() throws JAXBException, FileNotFoundException {
		File file = new File("student.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Studentinput.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Studentinput que = (Studentinput) jaxbUnmarshaller.unmarshal(file);

		System.out.println(que.getId());
		List<Studentview> slist = new ArrayList<>();
		List<Studentxml> list = que.getStudentlist();

		for (Studentxml stu : list) {
			System.out.println(stu.getStudentname() + " " + stu.getRollnumber() + " " + stu.getCourse() + " "
					+ stu.getDegree() + " " + stu.getDob() + " " + stu.getStatus() + " " + stu.getCreatedby() + " "
					+ DateUtil.getCurrentTimeStamp() + " " + stu.getUpdatedby() + " " + DateUtil.getCurrentTimeStamp());

			Studentview obj = new Studentview(stu.getStudentname(), stu.getRollnumber(), stu.getCourse(),
					stu.getDegree(), stu.getDob(), StatusEnum.Active.name(), "Admin", stu.getCreateddate(), "Admin",
					stu.getUpdateddate());

			slist.add(obj);
			studentRepository.save(buildStudent(obj));

		}
	}

	public void testObjectToXml() throws JAXBException {
	    
	Studentoutputlist sts=new Studentoutputlist(getAllstudents());
		File file = new File("E:\\GIT\\Spring_Boot_H2DB\\reverseFile.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Studentoutputlist.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal( sts,file);

	}
    
	public void testObjectToJson() throws JsonProcessingException, JsonMappingException, IOException {
        
		List<Studentoutput> sts = this.getAllstudents();
		File jsonfile = new File("E:\\GIT\\Spring_Boot_H2DB\\reversefile.json");
		ObjectMapper objectmapper = new ObjectMapper();
		objectmapper.writeValue(jsonfile, sts);
	}
}
  
/*
 * studentview = objectMapper.readValue(new File("file.json"),
 * Studentview.class); Student student = this.buildStudent(studentview);
 * student.setCreatedby("Admin"); student.setUpdatedby("Admin");
 * student.setCreateddate(DateUtil.getCurrentTimeStamp());
 * student.setUpdateddate(DateUtil.getCurrentTimeStamp());
 */
// System.out.println(studentview);             