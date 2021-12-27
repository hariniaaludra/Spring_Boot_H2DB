package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerReverseDataXml {
	
	@XmlElement(name = "customer")
	private List<CustomerViewOutput> customerList;
	
	public CustomerReverseDataXml() {
		
	}

	public CustomerReverseDataXml(List<CustomerViewOutput> customerList) {
		super();
		this.customerList = customerList;
	}

	public List<CustomerViewOutput> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerViewOutput> customerList) {
		this.customerList = customerList;
	}
	

}
