package com.aaludra.spring.jpa.h2.exception;

public class InvalidRequestException extends Exception {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6811273463078818057L;

	public  InvalidRequestException (String str)  
    {  
        // calling the constructor of parent Exception  
        super(str);  
    }  
}
