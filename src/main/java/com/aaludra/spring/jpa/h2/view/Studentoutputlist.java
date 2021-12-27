package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Studentoutputlist {
	@XmlElement(name="studentlist")
	
	private List<Studentoutput> studentlist;
	
	public Studentoutputlist() {
		
	}

	public Studentoutputlist(List<Studentoutput> studentlist) {
		super();
		this.studentlist = studentlist;
	}

	public List<Studentoutput> getStudentlist() {
		return studentlist;
	}

	public void setStudentlist(List<Studentoutput> studentlist) {
		this.studentlist = studentlist;
	}
	
	

}
    