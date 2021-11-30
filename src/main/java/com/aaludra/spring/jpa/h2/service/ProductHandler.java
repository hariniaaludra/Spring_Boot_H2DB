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

	public Product testXmlToObject() throws JAXBException, FileNotFoundException {
		File file = new File("C:\\files\\New folder\\Spring_Boot_H2DB\\src\\main\\java\\product.xml");  
        JAXBContext jaxbContext = JAXBContext.newInstance(Productsxml.class);  
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Productsxml que= (Productsxml) jaxbUnmarshaller.unmarshal(file);  
        System.out.println(que.getId()+" "+que.getProductname()+" "+que.getProductcode()+" "+que.getPrice()+" "+que.getExpdate()+" "+que.getMfgdate()+" "+que.getStatus()+" "+que.getCreatedby()+" "+que.getCreateddate()+" "+que.getUpdatedby()+" "+que.getUpdateddate());  
        return productRepository
				.save(new Product(que.getProductname(), que.getProductcode(),Double.parseDouble(que.getPrice()),DateUtil.convertStringToDate(que.getExpdate()),DateUtil.convertStringToDate(que.getMfgdate()),que.getStatus(),que.getCreatedby(),DateUtil.convertStringToTimestamp(que.getCreateddate()),
					 que.getUpdatedby(),DateUtil.convertStringToTimestamp (que.getUpdateddate())));   		
	}
}
