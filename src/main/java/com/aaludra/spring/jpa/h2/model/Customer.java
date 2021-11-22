package com.aaludra.spring.jpa.h2.model;

import java.security.Timestamp;
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
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_date")
	private Timestamp updatedDate;
	
	public Customer(long id, String cust_name,long cust_Id, String city,Date dob,String Gstin,String status,String createdBy,Timestamp createdDate,String updatedBy,Timestamp updatedDate) {
		this.id=id;
		this.cust_name=cust_name;
		this.cust_Id=cust_Id;
		this.city=city;
		this.dob=dob;
		this.Gstin=Gstin;
		this.status=status;
		this.createdBy=createdBy;
		this.createdDate=createdDate;
		this.updatedBy=updatedBy;
		this.updatedDate=updatedDate;
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

	public long getCust_Id() {
		return cust_Id;
	}

	public void setCust_Id(long cust_Id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cust_name=" + cust_name + ", cust_Id=" + cust_Id + ", city=" + city + ", dob="
				+ dob + ", Gstin=" + Gstin + ", status=" + status + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}


	
}
