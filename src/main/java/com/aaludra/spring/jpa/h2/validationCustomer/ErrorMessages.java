package com.aaludra.spring.jpa.h2.validationCustomer;

public class ErrorMessages {
	int code;
	String messeges;
	
	public ErrorMessages(int value, String message) {
		
	}
	public void ErrorMesseges(int code,String messeges) {
	this.code=code;
	this.messeges=messeges;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMesseges() {
		return messeges;
	}
	public void setMesseges(String messeges) {
		this.messeges = messeges;
	}
	

}
