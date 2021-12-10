package com.aaludra.spring.jpa.h2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.model.Employee;
import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.repository.ProductRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.util.Dateconvert;
import com.aaludra.spring.jpa.h2.view.Productinput;
import com.aaludra.spring.jpa.h2.view.Productsxml;
import com.aaludra.spring.jpa.h2.view.Productview;

@Service
public class ProductHandler {

	@Inject
	ProductRepository productRepository;

	public List<Product> getAllProduct(String productname) {

		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);

		return products;
	}

	public Optional<Product> getProductById(long id) {
		Optional<Product> productData = productRepository.findById(id);
		return productData;

	}

	public Product createproduct(Product product) {
		Product productobj =createproduct(product);
		
		 productobj = productRepository.save(product);

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

	public List<Product> findBycreatedby() {
		List<Product> product = productRepository.findBycreatedby("createdby");

		return product;

	}

	public Product updateProduct(Product productobj) {
		productRepository.save(productobj);
		return productobj;
	}

	public void testXmlToObject() throws JAXBException, FileNotFoundException {
		
		File file = new File("C:\\files\\New folder (2)\\Spring_Boot_H2DB\\src\\main\\java\\product.xml");  
        JAXBContext jaxbContext = JAXBContext.newInstance(Productinput.class); 
        
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Productinput que= (Productinput) jaxbUnmarshaller.unmarshal(file); 
        
        List<Productsxml> list=que.getProductlist();
       List<Productsxml> ulist= new ArrayList<>();
        
        
        
        for(Productsxml pr:list ) {
        System.out.println(pr.getId()+" "+pr.getProductname()+" "+pr.getProductcode()+" "+pr.getPrice()+" "+pr.getExpdate()+" "+pr.getMfgdate()+" "+pr.getStatus()+" "+pr.getCreatedby()+" "+pr.getCreateddate()+" "+pr.getUpdatedby()+" "+pr.getUpdateddate());  
         productRepository
				.save(new Product(pr.getProductname(), pr.getProductcode(),Double.parseDouble(pr.getPrice()),DateUtil.convertStringToDate(pr.getExpdate()),DateUtil.convertStringToDate(pr.getMfgdate()),pr.getStatus(),pr.getCreatedby(),DateUtil.convertStringToTimestamp(pr.getCreateddate()),
					 pr.getUpdatedby(),DateUtil.convertStringToTimestamp (pr.getUpdateddate()))); 
         ulist.add(pr);
	}
}}
