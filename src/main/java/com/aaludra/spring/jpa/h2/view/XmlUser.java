package com.aaludra.spring.jpa.h2.view;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class XmlUser {
	@XmlAttribute(name = "username")
	private String username;
	@XmlAttribute(name = "displayname")
	private String displayname;
	@XmlAttribute(name = "password")
	private String password;
	@XmlAttribute(name = "dob")
	private Date dob;
	@XmlAttribute(name = "phoneno")
	private Number phoneno;
	@XmlAttribute(name = "status")
	private String status;
	@XmlAttribute(name = "createdby")
	private String createdby;
	@XmlAttribute(name = "createddate")
	private Timestamp createddate;
	@XmlAttribute(name = "updatedby")
	private String updatedby;
	@XmlAttribute(name = "updateddate")
	private Timestamp updateddate;

	public XmlUser() {
	}

	public XmlUser(String username, String displayname, String password, Date dob, Number phoneno, String status,
			String createdby, Timestamp createddate, String updatedby, Timestamp updateddate) {
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

	public String toString() {
		return "User [ username=" + username + ", displayname=" + displayname + ", password=" + password + ", dob="
				+ dob + ", phoneno=" + phoneno + ", status=" + status + ", createdby=" + createdby + ", createddate="
				+ createddate + ", updatedby=" + updatedby + ", updateddate=" + updateddate + "]";
	}

}
