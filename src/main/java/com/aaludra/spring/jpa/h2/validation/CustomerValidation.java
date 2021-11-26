package com.aaludra.spring.jpa.h2.validationCustomer;

import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Customer;

public class CustomerValidation {
	enum GenderCondition { MALE,FEMALE};
     public void validate(Customer customer) throws InvalidRequestException{
    	 if(customer.getCustname()==null) {
    		 throw new InvalidRequestException("Name is Mandatory");
    	 }
    	 if((customer.getCustname().length()<=2) | (customer.getCustname().length()>200)){
    		 throw new InvalidRequestException("Invalid name");
    	 }
    	 if((customer.getCustId().length()<=14)|(customer.getCustId().length()>15)) {
    		 throw new InvalidRequestException("Invalid length custId");
    	 }
    	 if(customer.getCity()==null) {
    		 throw new InvalidRequestException("City is Mandatory");
    	 }
    	 if(customer.getStatus()==null) {
    		 throw new InvalidRequestException("status is Mandatory");
    	 }
    	 if(customer.getGstin()==null) {
    		 throw new InvalidRequestException("gstin is Mandatory");
    	 }
    	 if((customer.getGstin().length()<=14) |(customer.getGstin().length()>15)){
    		 throw new InvalidRequestException("Invalid gstin number");
    	 }
    	 if(customer.getDob()==null) {
    		 throw new InvalidRequestException("Dob is Mandatory");
    	 }
    	
    	 
    	 
     }
}
