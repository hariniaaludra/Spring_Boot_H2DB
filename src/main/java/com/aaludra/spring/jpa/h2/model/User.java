package com.aaludra.spring.jpa.h2.model;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="tbl_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name")
	public String username;
	
	@Column(name="display_name")
    private String displayname;
	
	@Column(name="password")
    private String password;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="phoneno")
	private Number phoneno;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_by")
	private String createdby;
	
	@Column(name="created_date")
	private Timestamp createddate;
	
	@Column(name="updated_by")
	private String updatedby;
	
	@Column(name="updated_date")
	private Timestamp updateddate;
	
	
	
	public User() {
		
	}

	public User(int id,String username,String displayname,String password,Date dob,
			Number phoneno,String status,String createdby,Timestamp createddate,String updatedby,Timestamp updateddate) {
		this.id=id;
		this.username=username;
		this.displayname=displayname;
		this.password=password;
		this.dob=dob;
		this.phoneno=phoneno;
		this.status=status;
		this.createdby=createdby;
		this.createddate=createddate;
		this.updatedby=updatedby;
		this.updateddate=updateddate;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Number getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(Number phoneno) {
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

	public Timestamp getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Timestamp getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}	
	
	public String toString() {
		return "User [id= " + id + ", username=" + username + ", displayname=" + displayname + ", password=" + password + 
				", dob=" + dob + ", phoneno="+ phoneno +", status="+ status + ", createdby=" + createdby +", createddate=" +
				createddate + ", updatedby=" + updatedby + ", updateddate=" + updateddate + "]";
				}
}
	
		