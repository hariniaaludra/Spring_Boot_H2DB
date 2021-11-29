package com.aaludra.spring.jpa.h2.view;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Userinput {
	private int id;
	private String username;
	private String displayname;
	private String password;
	private String dob;
	private String phoneno;
	public Userinput() {
		
	}
	public Userinput(int id, String username, String displayname, String password,String dob, String phoneno) {
		super();
		this.id = id;
		this.username = username;
		this.displayname = displayname;
		this.password = password;
		this.dob = dob;
		this.phoneno = phoneno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}	
}

	
