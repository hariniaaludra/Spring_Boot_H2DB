package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="userinput")
public class Userinput {
	private int id;
	private List<Userslist> userslist;
	
	public Userinput() {
		
	}

	public Userinput(int id, List<Userslist> userslist) {
		super();
		this.id = id;
		this.userslist = userslist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Userslist> getUserslist() {
		return userslist;
	}

	public void setUserslist(List<Userslist> userslist) {
		this.userslist = userslist;
	}
	
}
	