package com.aaludra.spring.jpa.h2.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.service.ProductHandler;
import com.aaludra.spring.jpa.h2.util.DateUtil;

class ProductControllerTest {
	@Autowired
	ProductHandler handler;

	@Test
 public void productListTest() {
      //  ProductController prtControler = new ProductController();
      // assertEquals(prtControler.getAllProduct("rice"),prtControler.getAllProduct(" raj"));	

}
}
