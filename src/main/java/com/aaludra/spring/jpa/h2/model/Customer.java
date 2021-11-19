package com.aaludra.spring.jpa.h2.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table (name = "Customer") 
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "cust_name")
	private String cust_name;
	
	@Column(name = "cust_Id")
	private long cust_Id;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "Gstin")
	private String Gstin;
	
	public Customer(long id, String cust_name,long cust_Id, String city,Date dob,String Gstin) {
		this.id=id;
		this.cust_name=cust_name;
		this.cust_Id=cust_Id;
		this.city=city;
		this.dob=dob;
		this.Gstin=Gstin;
	}


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


	@Override
	public String toString() {
		return "Customer [id=" + id + ", cust_name=" + cust_name + ", cust_Id=" + cust_Id + ", city=" + city + ", dob="
				+ dob + ", Gstin=" + Gstin + "]";
	}

	
	
}
