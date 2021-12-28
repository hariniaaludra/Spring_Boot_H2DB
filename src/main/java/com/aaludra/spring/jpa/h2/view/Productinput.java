package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name="products")
public class Productinput {
	private List<Productsxml> productlist;
	
	public Productinput() {
		
	}

	public Productinput(  List<Productsxml> productlist) {
		super();
		this. productlist =  productlist;
	}

	public List<Productsxml> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Productsxml> productlist) {
		this.productlist = productlist;
	}
}
