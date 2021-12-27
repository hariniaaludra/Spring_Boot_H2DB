package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="employeeInput")

public class EmployeeInput {
	private int id;
	private List<EmployeeList> employeelist;
	public EmployeeInput() {
	
	}
	public EmployeeInput(int id, List<EmployeeList> employeelist) {
		super();
		this.id = id;
		this.employeelist = employeelist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<EmployeeList> getEmployeelist() {
		return employeelist;
	}
	public void setEmployeelist(List<EmployeeList> employeelist) {
		this.employeelist = employeelist;
	}


}
