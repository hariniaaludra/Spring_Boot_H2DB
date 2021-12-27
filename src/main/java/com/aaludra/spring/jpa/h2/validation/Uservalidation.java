package com.aaludra.spring.jpa.h2.validation;

import com.aaludra.spring.jpa.h2.enum1.StatusEnum;
import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.view.UserInputView;

public class Uservalidation {
	
	public void validate(UserInputView userview) throws InvalidRequestException {
		if (userview.getUsername() == null) {
			throw new InvalidRequestException("Username is mandatory");
		}
		if (userview.getUsername().length() > 15) {
			throw new InvalidRequestException("Username invalid length");
		}
		if (userview.getPassword().length() > 15) {
			throw new InvalidRequestException("password is invalid length");
		}
		if (userview.getPassword() == null) {
			throw new InvalidRequestException("Password is mandatory");
		}
		if (userview.getPhoneno().toString().length() < 10 || userview.getPhoneno().toString().length() > 10) {
			throw new InvalidRequestException("Phoneno is invalid length");
		}
		if (userview.getDisplayname() == null) {
			throw new InvalidRequestException("Displayname is mandatory");
		}

		/*
		 * if (userview.getUpdateddate() == null) { throw new
		 * InvalidRequestException("Updated date is mandatory"); } if
		 * (userview.getDisplayname().length() > 15) { throw new
		 * InvalidRequestException("Displayname is invalid length"); } if
		 * (userview.getCreatedby().length() > 20) { throw new
		 * InvalidRequestException("Createdby length is invalid"); } if
		 * (userview.getUpdatedby().length() > 20) { throw new
		 * InvalidRequestException("Updatedby length is invalid"); }
		 * 
		 * StatusEnum a = StatusEnum.check(userview.getStatus()); if (a == null) { throw
		 * new InvalidRequestException("Invalid Status"); }
		 */
	}

}
