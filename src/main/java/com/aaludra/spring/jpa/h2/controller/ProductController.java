package com.aaludra.spring.jpa.h2.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.service.ProductHandler;
import com.aaludra.spring.jpa.h2.util.Dateconvert;
import com.aaludra.spring.jpa.h2.validation.ErrorMessages;
import com.aaludra.spring.jpa.h2.validation.Productvalidation;
import com.aaludra.spring.jpa.h2.view.Productinput;
import com.aaludra.spring.jpa.h2.view.Productsxml;
import com.aaludra.spring.jpa.h2.view.Productviewinput;
import com.aaludra.spring.jpa.h2.view.Productviewoutput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductHandler handler;

	@GetMapping("/products")
	public ResponseEntity<List<Productviewoutput>> getAllProduct(@RequestParam(required = false) String productname) {
		try {
			List<Productviewoutput> products = handler.getAllProduct();
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Productviewoutput> getProductById(@PathVariable("id") long id) {
		Optional<Productviewoutput> productData = handler.getProductById(id);

		if (productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody Productviewinput product) {

		try {
			Productvalidation prt = new Productvalidation();
			prt.Validat(product);
			Productviewoutput productobj = handler.createproduct(product);

			return new ResponseEntity<>(productobj, HttpStatus.CREATED);
		} catch (InvalidRequestException e) {
			return new ResponseEntity<>(new ErrorMessages(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Productviewoutput> updateProduct(@PathVariable("id") long id, @RequestBody Productviewinput product) {
		//Optional<Productviewoutput> productData = handler.getProductById(id);
		
			return new ResponseEntity<>(handler.updateProduct(id,product), HttpStatus.OK);
		
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

	@PostMapping("/products/process/xml")
	public ResponseEntity<Productinput> testXmlToObject() {
		try {
			handler.testXmlToObject();
		} catch (FileNotFoundException | JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

	@PostMapping("/products/process/json")
	public ResponseEntity<Productviewoutput> testJsonToObject() throws JsonParseException, JsonMappingException, IOException {
		try {
			handler.testJsonToObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	
	@GetMapping("/products/reverse/json")
	public ResponseEntity<Productviewoutput> databaseToJson() throws JsonParseException, JsonMappingException, IOException {
		try {
			handler.testJsonToObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	

	@GetMapping("/products/reverse/xml")
	public ResponseEntity<Productviewoutput> databaseToXml() {
		try {
			handler.testXmlToObject();
		} catch (FileNotFoundException | JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

	@GetMapping("/products/createdby")

	public ResponseEntity<List<Productviewoutput>> findByproductname() {
		try {
			List<Productviewoutput> products = handler.findByproductname();
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}