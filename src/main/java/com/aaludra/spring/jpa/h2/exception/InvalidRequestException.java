package com.aaludra.spring.jpa.h2.exception;

public class InvalidRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -529948751837522613L;

	public InvalidRequestException(String str) {
		// calling the constructor of parent Exception
		super(str);
	}

}
