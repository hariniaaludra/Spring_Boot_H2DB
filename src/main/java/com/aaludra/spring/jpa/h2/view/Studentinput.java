package com.aaludra.spring.jpa.h2.view;

import java.util.List;

public class Studentinput {
	private List<Studentxml> studentlist;

	public Studentinput() {
		super();

	}

	public Studentinput(List<Studentxml> studentlist) {
		super();
		this.studentlist = studentlist;
	}

	public List<Studentxml> getStudentlist() {
		return studentlist;
	}

	public void setStudentlist(List<Studentxml> studentlist) {
		this.studentlist = studentlist;
	}

}
