package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")

public class Customerinput {
	private List<CustomerXml> customerlist;
	
	public Customerinput() {
		
	}

	public Customerinput(List<CustomerXml> customerlist) {
		super();
		this.customerlist = customerlist;
	}

	public List<CustomerXml> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<CustomerXml> customerlist) {
		this.customerlist = customerlist;
	}
	
	

}
