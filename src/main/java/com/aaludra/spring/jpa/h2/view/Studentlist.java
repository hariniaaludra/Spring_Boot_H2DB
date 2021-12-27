package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("studentJsonlist")
public class Studentlist {
	private List<Studentview> studentJsonlist;
	private int id;
	
	public Studentlist() {
		
	}

	public Studentlist(List<Studentview> studentJsonlist, int id) {
		super();
		this.studentJsonlist = studentJsonlist;
		this.id = id;
	}

	public List<Studentview> getStudentJsonlist() {
		return studentJsonlist;
	}
 
	public void setStudentJsonlist(List<Studentview> studentJsonlist) {
		this.studentJsonlist = studentJsonlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}