package com.aaludra.spring.jpa.h2.view;

public class CustomerView {
	private String custname;
	private String custId;
	private String city;
	private String dob;
	private String Gstin;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private String gender;
	
    public CustomerView() {
    	
    }
    public CustomerView(String custname,String custId,String city,String dob,String Gstin,String status,String createdBy,String createdDate,
    		String updatedBy,String updatedDate,String gender)
    {
    		this.custname=custname;
    		this.custId=custId;
    		this.city=city;
    		this.dob=dob;
    		this.Gstin=Gstin;
    		this.status=status;
    		this.createdBy=createdBy;
    		this.createdDate=createdDate;
    		this.updatedBy=updatedBy;
    		this.updatedDate=updatedDate;
    		this.gender=gender;
    		}
    
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Override
	public String toString() {
		return "CustomerView [custname=" + custname + ", custId=" + custId + ", city=" + city + ", dob=" + dob
				+ ", Gstin=" + Gstin + ", status=" + status + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", gender=" + gender
				+ "]";
	}
}
