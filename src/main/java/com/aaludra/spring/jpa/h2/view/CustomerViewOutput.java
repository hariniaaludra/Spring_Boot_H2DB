package com.aaludra.spring.jpa.h2.view;

import javax.xml.bind.annotation.XmlRootElement;

import com.aaludra.spring.jpa.h2.model.Customer;
import com.aaludra.spring.jpa.h2.util.DateUtil;
@XmlRootElement(name = "customers")
public class CustomerViewOutput {
	private String id;
	private String custname;
	private String custId;
	private String city;
	private String dob;
	private String gstin;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private String gender;
	
	public CustomerViewOutput() {
		
	}
	
   
    public CustomerViewOutput(String id,String custname,String custId,String city,String dob,String Gstin,String status,String createdBy,String createdDate,
    		String updatedBy,String updatedDate,String gender)
    {
    	    this.id = id;
    		this.custname=custname;
    		this.custId=custId;
    		this.city=city;
    		this.dob=dob;
    		this.gstin=Gstin;
    		this.status=status;
    		this.createdBy=createdBy;
    		this.createdDate=createdDate;
    		this.updatedBy=updatedBy;
    		this.updatedDate=updatedDate;
    		this.gender=gender;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Customer [id="+id+",custname=" + custname + ", custId=" + custId + ", city=" + city + ", dob=" + dob
				+ ", Gstin=" + gstin + ", status=" + status + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", gender=" + gender
				+ "]";
	}
}
