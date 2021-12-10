package com.aaludra.spring.jpa.h2.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.util.DateUtil;

class ProductHandlerTest {
	@Autowired
	ProductHandler handler;
	
	@Test
	void test () {
		handler.getAllProduct("");
		List<Product> products = new ArrayList<>();
		
		products.get(0).setProductname("rice");
		
		products.get(1).setProductcode("ID101");
		
		products.get(2).setPrice(100.00);
		
		products.get(3).setMfgdate(DateUtil.convertStringToDate("2021-12-01"));
		
		products.get(4).setExpdate(DateUtil.convertStringToDate("2022-12-01"));
		
		products.get(5).setStatus("ID101");
		
		products.get(6).setCreatedby("suriya");
		
		products.get(7).setCreateddate(DateUtil.convertStringToTimestamp("2021-12-01"));
		
		products.get(8).setUpdatedby("suriya");
		
		products.get(9).setUpdatedate(DateUtil.convertStringToTimestamp("2021-12-01"));
		
		
		
		
		
		
	}

}
