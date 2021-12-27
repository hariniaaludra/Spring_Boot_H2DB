package com.aaludra.spring.jpa.h2.view;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EmployeeDetailView {
	
	
	private String empName;
	private String empId;
	private String empPhonenumber;
	private String empAddress;
	private String empDoj;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;	private String empGrade;
	private String salary;
	
	public EmployeeDetailView() {
	
	}
	
	
	public EmployeeDetailView(String empName, String empId, String empPhonenumber, String empAddress, String empDoj,
			 String empGrade,
			String salary) {
		super();
		this.empName = empName;
		this.empId = empId;
		this.empPhonenumber = empPhonenumber;
		this.empAddress = empAddress;
		this.empDoj = empDoj;
		
		this.empGrade = empGrade;
		this.salary = salary;
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


	public String getEmpPhonenumber() {
		return empPhonenumber;
	}


	public void setEmpPhonenumber(String empPhonenumber) {
		this.empPhonenumber = empPhonenumber;
	}


	public String getEmpAddress() {
		return empAddress;
	}


	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}


	public String getEmpDoj() {
		return empDoj;
	}


	public void setEmpDoj(String empDoj) {
		this.empDoj = empDoj;
	}
	public String getEmpGrade() {
		return empGrade;
	}


	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}
	
}
