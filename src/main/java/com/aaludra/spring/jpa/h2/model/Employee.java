package com.aaludra.spring.jpa.h2.model;

import java.sql.Date;

public class Employee {
    private int id;
	private String empName;
	private String empId;
	private int emPhonenumber;
	private String empAddress;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getEmPhonenumber() {
		return emPhonenumber;
	}
	public void setEmPhonenumber(int emPhonenumber) {
		this.emPhonenumber = emPhonenumber;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public Date getEmpDoj() {
		return empDoj;
	}
	public void setEmpDoj(Date empDoj) {
		this.empDoj = empDoj;
	}
	private Date empDoj;
	
}
	


