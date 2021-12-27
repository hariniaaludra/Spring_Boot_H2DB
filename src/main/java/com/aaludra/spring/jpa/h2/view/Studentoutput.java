package com.aaludra.spring.jpa.h2.view;

import javax.xml.bind.annotation.XmlRootElement;

import com.aaludra.spring.jpa.h2.model.Student;

@XmlRootElement(name="student")
public class Studentoutput {
	private String id;
	private String studentname;
	private String rollnumber;
    private String course;
    private String degree;
    private String dob;
    private String status;
    private String createdby;
    private String createddate;
    private String updatedby;
    private String updateddate;
    
    public Studentoutput() {
    	super();
    }
   
	public Studentoutput(String id, String studentname, String rollnumber, String course, String degree, String dob,
			String status, String createdby, String createddate, String updatedby, String updateddate) {
		super();
		this.id=id;
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

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(String rollnumber) {
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
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

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

	@Override
	public String toString() {
		return "Studentoutput [studentname=" + studentname + ", rollnumber=" + rollnumber + ", course="
				+ course + ", degree=" + degree + ", dob=" + dob + ", status=" + status + ", createdby=" + createdby
				+ ", createddate=" + createddate + ", updatedby=" + updatedby + ", updateddate=" + updateddate + "]";
	}
}
     
 