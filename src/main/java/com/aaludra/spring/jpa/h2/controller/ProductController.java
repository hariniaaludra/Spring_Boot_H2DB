package com.aaludra.spring.jpa.h2.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.repository.ProductRepository;
import com.aaludra.spring.jpa.h2.view.Productview;
import com.aaludra.spring.jpa.h2.util.Dateconvert;
import com.aaludra.spring.jpa.h2.service.ProductHandler;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductHandler  handler;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false) String productname) {
		try {
			List<Product> products = handler.getAllProduct(productname);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		Optional<Product> productData = handler.getProductById(id);

		if (productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<Productview> createProduct(@RequestBody Product product) {
		
		try {
			Product productobj = handler.createproduct(product);
			Productview obj=new Productview();
			
			obj.setProductname(productobj.getProductname());
			
			obj.setUpdatedby(productobj.getUpdatedby());
			
			obj.setProductcode(productobj.getProductcode());
			
			obj.setPrice(Double.toString(productobj.getPrice()));
			
			obj.setStatus(productobj.getStatus());
			
			obj.setCreatedby(productobj.getCreatedby());
			
			obj.setCreateddate(productobj.getCreateddate().toString());
			
			obj.setUpdatedate(productobj.getUpdatedate().toString());
			
			obj.setMfgdate(Dateconvert.convertStringToDate(productobj.getMfgdate()));
			
			obj.setExpdate(Dateconvert.convertStringToDate(productobj.getExpdate()));

			return new ResponseEntity<>(obj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> productData = handler.getProductById(id);
		if (productData.isPresent()) {
			Product productobj = productData.get();
			productobj.setProductname(product.getProductname());
			productobj.setProductcode(product.getProductcode());
			productobj.setPrice((product.getPrice()));
			productobj.setExpdate(product.getExpdate());
			productobj.setMfgdate(product.getMfgdate());
			productobj.setStatus(product.getStatus());
			productobj.setCreatedby(product.getCreatedby());
			productobj.setCreateddate(product.getCreateddate());
			productobj.setUpdatedby(product.getUpdatedby());
			productobj.setUpdatedate(product.getUpdatedate());
			return new ResponseEntity<>(handler.updateProduct(productobj), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
		try {
			handler.deleteproduct(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllProducts() {
		try {
			handler.deleteAllProduct();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/products/createdby")

	public ResponseEntity<List<Product>> findBycreatedby() {
		try {
			List<Product> products = handler.findBycreatedby();
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}