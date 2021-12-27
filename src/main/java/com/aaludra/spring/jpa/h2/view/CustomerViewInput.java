package com.aaludra.spring.jpa.h2.view;

public class CustomerViewInput {
	private String id;
	private String custname;
	private String custId;
	private String city;
	private String dob;
	private String gstin;
	private String status;
	private String gender;
	
	public  CustomerViewInput() {
		
	}

	public CustomerViewInput(String id,String custname, String custId, String city, String dob, String gstin, String status,
			String gender) {
		super();
		this.id = id;
		this.custname = custname;
		this.custId = custId;
		this.city = city;
		this.dob = dob;
		this.gstin = gstin;
		this.status = status;
		this.gender = gender;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "CustomerViewInput [id=" + id + ", custname=" + custname + ", custId=" + custId + ", city=" + city
				+ ", dob=" + dob + ", gstin=" + gstin + ", status=" + status + ", gender=" + gender + "]";
	}
	
	
	
	
}
