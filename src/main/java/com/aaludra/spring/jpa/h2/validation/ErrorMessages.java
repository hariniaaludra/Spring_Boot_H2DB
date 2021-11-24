package com.aaludra.spring.jpa.h2.validation;

public class ErrorMessages {
	int code;
	String massege;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMassege() {
		return massege;
	}

	public void setMassege(String massege) {
		this.massege = massege;
	}

	public ErrorMessages(int code, String massege) {

		this.code = code;
		this.massege = massege;
	}

	public ErrorMessages() {

	}

}
