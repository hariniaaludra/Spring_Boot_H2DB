package com.aaludra.spring.jpa.h2.view;

public class UserInputView {
	private String username;
	private String displayname;
	private String password;
	private String dob;
	private String phoneno;
	
	public UserInputView() {
		
	}

 public UserInputView(String username, String displayname, String password, String dob, String phoneno, String status,
			String createdby, String createddate, String updatedby, String updateddate) {
		super();
		this.username = username;
		this.displayname = displayname;
		this.password = password;
		this.dob = dob;
		this.phoneno = phoneno;
		
		
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


	public String toString() {
		return "User [ username=" + username + ", displayname=" + displayname + ", password=" + password
				+ ", dob=" + dob + ", phoneno=" + phoneno + "]";
	}
}
