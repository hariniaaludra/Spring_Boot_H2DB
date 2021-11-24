package com.aaludra.spring.jpa.h2.model;

import javax.persistence.*;


import java.sql.Timestamp;
import java.sql.Date;

@Entity
@Table(name = "tbl_student")

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "student_name")
	private String studentname;

	@Column(name = "roll_number")
	private int rollnumber;

	@Column(name = "course")
	private String course;

	@Column(name = "degree")
	private String degree;

	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_by")
	private String createdby;
	
	@Column(name = "created_date")
	private Timestamp createddate;
	
	@Column(name = "updated_by")
	private String updatedby;
	
	@Column(name = "updated_date")
	private Timestamp updateddate;

	public Student() {

	}

	public Student(int id, String studentname, int rollnumber, String course, String degree, Date dob, String status,
			String createdby, Timestamp createddate, String updatedby, Timestamp updateddate) {
		super();
		this.id = id;
		this.studentname = studentname;
		this.rollnumber = rollnumber;
		this.course = course;
		this.degree = degree;
		this.dob = dob;
		this.status = status;
		this.createdby = createdby;
		this.createddate = createddate;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Timestamp getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentname=" + studentname + ", rollnumber=" + rollnumber + ", course="
				+ course + ", degree=" + degree + ", dob=" + dob + ", status=" + status + ", createdby=" + createdby
				+ ", createddate=" + createddate + ", updatedby=" + updatedby + ", updateddate=" + updateddate + "]";
	}
}
	

	