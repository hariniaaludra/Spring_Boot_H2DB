package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("employeeInputObjtoJson")
public class EmployeeInputObjtoJson {
	private int id;
	private List<EmployeeList> employeejsonlist;
	public EmployeeInputObjtoJson(int id, List<EmployeeList> employeejsonlist) {
		super();
		this.id = id;
		this.employeejsonlist = employeejsonlist;
	}
	public EmployeeInputObjtoJson() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<EmployeeList> getEmployeejsonlist() {
		return employeejsonlist;
	}
	public void setEmployeejsonlist(List<EmployeeList> employeejsonlist) {
		this.employeejsonlist = employeejsonlist;
	}
	

}
