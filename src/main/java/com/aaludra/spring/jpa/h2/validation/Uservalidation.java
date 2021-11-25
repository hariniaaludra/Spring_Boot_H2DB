package com.aaludra.spring.jpa.h2.validation;

import com.aaludra.spring.jpa.h2.enum1.StatusEnum;
import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.User;

public class Uservalidation {
	public void validate(User user) throws InvalidRequestException {
		if (user.getUsername() == null) {
			throw new InvalidRequestException("Username is mandatory");
		}
		if (user.getUsername().length() > 15) {
			throw new InvalidRequestException("Username invalid length");
		}
		if (user.getPassword().length() > 15) {
			throw new InvalidRequestException("password is invalid length");
		}
		if (user.getPassword() == null) {
			throw new InvalidRequestException("Password is mandatory");
		}
		if (user.getPhoneno().toString().length() < 10 || user.getPhoneno().toString().length() > 10) {
			throw new InvalidRequestException("Phoneno is invalid length");
		}
		if (user.getDisplayname() == null) {
			throw new InvalidRequestException("Displayname is mandatory");
		}

		if (user.getUpdateddate() == null) {
			throw new InvalidRequestException("Updated date is mandatory");
		}
		if (user.getDisplayname().length() > 15) {
			throw new InvalidRequestException("Displayname is invalid length");
		}
		if (user.getCreatedby().length() > 20) {
			throw new InvalidRequestException("Createdby length is invalid");
		}
		if (user.getUpdatedby().length() > 20) {
			throw new InvalidRequestException("Updatedby length is invalid");
		}

		StatusEnum a = StatusEnum.check(user.getStatus());
		if (a == null) {
			throw new InvalidRequestException("Invalid Status");
		}

	}
}
