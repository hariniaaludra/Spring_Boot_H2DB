package com.aaludra.spring.jpa.h2.view;

import com.aaludra.spring.jpa.h2.model.User;

public class UserOutputView {
	private String username;
	private String displayname;
	private String password;
	private String dob;
	private String phoneno;
	private String status;
	private String createdby;
	private String createddate;
	private String updatedby;
	private String updateddate;
	public UserOutputView() {
		super();
	}

	/*
	 * public UserOutputView(User usr) { this.username = usr.getUsername();
	 * this.displayname =usr.getDisplayname(); this.password = usr.getPassword();
	 * this.dob =usr.getDob().toString(); this.phoneno =usr.getPhoneno().toString();
	 * this.status = usr.getStatus(); this.createdby = usr.getCreatedby();
	 * this.createddate = usr.getCreateddate().toString(); this.updatedby
	 * =usr.getUpdatedby(); this.updateddate =usr.getUpdateddate().toString();
	 * 
	 * }
	 */
	public UserOutputView(String username, String displayname, String password, String dob, String phoneno,
			String status, String createdby, String createddate, String updatedby, String updateddate) {
		super();
		this.username = username;
		this.displayname = displayname;
		this.password = password;
		this.dob = dob;
		this.phoneno = phoneno;
		this.status = status;
		this.createdby = createdby;
		this.createddate = createddate;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}
	
	public String toString() {
		return "User [ username=" + username + ", displayname=" + displayname + ", password=" + password
				+ ", dob=" + dob + ", phoneno=" + phoneno + ", status=" + status + ", createdby=" + createdby
				+ ", createddate=" + createddate + ", updatedby=" + updatedby + ", updateddate=" + updateddate + "]";
	}

}
