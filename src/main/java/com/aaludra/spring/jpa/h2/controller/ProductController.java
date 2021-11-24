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

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false) String productname) {
		try {
			List<Product> products = new ArrayList<>();

			if (productname == null)
				productRepository.findAll().forEach(products::add);

			else
				productRepository.findByproductnameContaining(productname).forEach(products::add);

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
		Optional<Product> productData = productRepository.findById(id);

		if (productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<Productview> createProduct(@RequestBody Product product) {
		
		try {
			Product productobj = productRepository
					.save(new Product(product.getProductname(), product.getProductcode(), product.getPrice(),
							product.getExpdate(), product.getMfgdate(), product.getStatus(), product.getCreatedby(),
							product.getCreateddate(), product.getUpdatedby(), product.getUpdatedate()));
			Productview obj=new Productview();
			obj.setProductname(productobj.getProductname());
			obj.setUpdatedby(productobj.getUpdatedby());
			obj.setProductcode(productobj.getProductcode());
			obj.setPrice(Double.toString(productobj.getPrice()));
			obj.setStatus(productobj.getStatus());
			obj.setCreatedby(productobj.getCreatedby());
			obj.setCreateddate(productobj.getCreateddate().toString());
			obj.setUpdatedate(productobj.getUpdatedate().toString());
			obj.setMfgdate(productobj.getMfgdate().toString());
			obj.setExpdate(productobj.getExpdate().toString());

			return new ResponseEntity<>(obj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> productData = productRepository.findById(id);
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
			return new ResponseEntity<>(productRepository.save(productobj), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllProducts() {
		try {
			productRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/products/createdby")

	public ResponseEntity<List<Product>> findBycreatedby() {
		try {
			List<Product> products = productRepository.findBycreatedby(true);
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}