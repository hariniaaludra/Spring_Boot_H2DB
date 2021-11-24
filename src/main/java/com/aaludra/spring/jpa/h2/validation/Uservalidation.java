package com.aaludra.spring.jpa.h2.validation;

import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.User;

public class Uservalidation {
	public void validate(User user) throws InvalidRequestException {
		if (user.getUsername() == null) {
			throw new InvalidRequestException("Username is mandatory");
		}
		if (user.getUsername().length() > 15) {
			throw new InvalidRequestException ("Username invalid length");
		}
		if (user.getPassword().length()>15){
			throw new InvalidRequestException ("password is invalid length");
		}
	}
}

	
