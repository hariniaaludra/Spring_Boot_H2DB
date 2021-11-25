package com.aaludra.spring.jpa.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.repository.ProductRepository;

@Service
public class ProductHandler {
	
	@Inject
	 ProductRepository productRepository;
	
	public List<Product> getAllProduct(String productname) {
		
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		
		return products;
	}
	public Optional<Product> getProductById(long id){
		Optional<Product> productData = productRepository.findById(id);
		return productData;
		
	}
	public  Product createproduct(Product product) {
		Product productobj = productRepository.save(product);
		return productobj;
	}
	
	public long deleteproduct(long id) {
		productRepository.deleteById(id);
		return id;
	}
	public ResponseEntity<HttpStatus> deleteAllProduct() {
		productRepository.deleteAll();
		return null;
		
	}
	public List<Product>findBycreatedby() {
		List<Product> product = productRepository.findBycreatedby("createdby");
		
		return product;
		
	}
	
	public   Product updateProduct(Product productobj) {
		productRepository.save(productobj);
		return productobj;
	}


	

	
}
