package com.aaludra.spring.jpa.h2.model;

import javax.persistence.*;



import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "TBL_EMPLOYEE")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "EMP_NAME")	
	private String empName;
	@Column(name = "EMP_ID")
	private String empId;
	@Column(name = "PHONE_NUMBER")
	private int emPhonenumber;
	@Column(name = "ADDRESS")
	private String empAddress;
	@Column(name = "EMP_DOJ")
	private Date empDoj;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;
  
	

	public Employee() {
		
	}

	public Employee(String empName, String empId, int emPhonenumber, String empAddress, Date empDoj,
			String status, String createdBy, Timestamp createdDate, String updatedBy, Timestamp updatedDate,
			boolean b) {
		this.empName = empName;
		this.empId = empId;
		this.emPhonenumber = emPhonenumber;
		this.empAddress= empAddress;
		this.empDoj = empDoj;
		this.status= status;
		this.createdBy =  createdBy ;
		this.createdDate =  createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

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

	public String toString() {
		return "Employee[Id = " + id + ",EMP_NAME=" + empName + ",EMP_ID =" + empId + ", PHONE_NUMBER = "
				+ emPhonenumber + ",ADDRESS= " + empAddress + ",EMP_DOJ=" + empDoj + ",STATUS = " + status
				+ ",CREATED_BY = " + createdBy + ",CREATED_DATE = " + createdDate + ",UPDATED_BY =" + updatedBy
				+ "CREATED_DATE = " + createdDate + "]";

	}
}
