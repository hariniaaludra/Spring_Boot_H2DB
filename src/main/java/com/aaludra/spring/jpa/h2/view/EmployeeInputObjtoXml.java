package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeInputObjtoXml {
	
	@XmlElement(name="employeelistout")
	private List<EmployeeViewOut> employeelist;
	public EmployeeInputObjtoXml( List<EmployeeViewOut> employeelist) {
		this.employeelist = employeelist;
	}
	public EmployeeInputObjtoXml() {
		super();
	}
	
	
	public List<EmployeeViewOut> getEmployeelist() {
		return employeelist;
	}
	public void setEmployeelist(List<EmployeeViewOut> employeelist) {
		this.employeelist = employeelist;
	}
	
}
