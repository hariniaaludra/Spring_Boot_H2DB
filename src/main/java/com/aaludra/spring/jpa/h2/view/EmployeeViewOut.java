package com.aaludra.spring.jpa.h2.view;
import javax.xml.bind.annotation.XmlRootElement;


import com.aaludra.spring.jpa.h2.model.Employee;
//@XmlRootElement(name="employeeinputobjtoxml")
public class EmployeeViewOut {
	
	private String empName;
	private String empId;
	private String empPhonenumber;
	private String empAddress;
	private String empDoj;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private String empGrade;
	private String salary;
	
	public EmployeeViewOut(String empName, String empId, String empPhonenumber, String empAddress, String empDoj,
			String status, String createdBy, String createdDate, String updatedBy, String updatedDate, String empGrade,
			String salary) {
		super();
		this.empName = empName;
		this.empId = empId;
		this.empPhonenumber = empPhonenumber;
		this.empAddress = empAddress;
		this.empDoj = empDoj;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.empGrade = empGrade;
		this.salary = salary;
	}
	public EmployeeViewOut() {
		super();
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
