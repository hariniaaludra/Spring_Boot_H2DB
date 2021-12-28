package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("productlist")
public class Pjsoninput {
	private List<Productsxml> productlist;
	
	public Pjsoninput () {
		
	}

	public Pjsoninput( List<Productsxml> productlist) {
		super();
		this.productlist = productlist;
	}

	public List<Productsxml> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Productsxml> productlist) {
		this.productlist = productlist;
	}
	
	
	
}