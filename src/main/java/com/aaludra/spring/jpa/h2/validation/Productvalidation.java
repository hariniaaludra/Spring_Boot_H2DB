package com.aaludra.spring.jpa.h2.validation;

import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.view.Productviewinput;
import com.aaludra.spring.jpa.h2.view.Productviewoutput;

public class Productvalidation {
	public void Validat(Productviewinput product) throws InvalidRequestException {
		if (product.getProductname() == null) {
			throw new InvalidRequestException("product Name mandatory");
		}
		if ((product.getProductname().length() < 3) | (product.getProductname().length() > 25)) {
			throw new InvalidRequestException("invalid productname ");
		}
		if (product.getProductcode() == null) {
			throw new InvalidRequestException("productcode mandatory");
		}
		if ((product.getProductcode().length() <= 2) | (product.getProductcode().length() > 25)) {
			throw new InvalidRequestException("invalid productcode");
		}

		if (product.getExpdate() == null) {
			throw new InvalidRequestException("expdate is  mandatory");
		}
		if (product.getMfgdate()== null) {

			throw new InvalidRequestException("mfgdate is mandatory");
		}
	}

}
