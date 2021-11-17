package com.aaludra.spring.jpa.h2.model;

import java.sql.Date;

import javax.persistence.*;

public class Customer {
	private long id;
	private String cust_name;
	private long cust_Id;
	private String city;
	private Date dob;
	private String Gstin;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public long getCust_id() {
		return cust_Id;
	}

	public void setCust_id(long cust_Id) {
		this.cust_Id = cust_Id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGstin() {
		return Gstin;
	}

	public void setGstin(String gstin) {
		Gstin = gstin;
	}
}
