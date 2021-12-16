package com.aaludra.spring.jpa.h2.view;

import java.util.List;

public class Studentinput {
	private List<Studentxml> studentlist;
	private int id;
	
	public Studentinput() {
		
	}
	public Studentinput(List<Studentxml> studentlist, int id) {
		super();
		this.studentlist = studentlist;
		this.id = id;
	}
	public List<Studentxml> getStudentlist() {
		return studentlist;
	}
	public void setStudentlist(List<Studentxml> studentlist) {
		this.studentlist = studentlist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}