package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("customerlist")
public class Cjsoninput {
       private List<CustomerXml> customerlist;

       public Cjsoninput () {

       }

   public Cjsoninput(List<CustomerXml> customerlist) {
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



