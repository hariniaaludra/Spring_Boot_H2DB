package com.aaludra.spring.jpa.h2.repository;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaludra.spring.jpa.h2.model.Employee;
import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.model.Tutorial;

public interface ProductRepository extends JpaRepository <Product, Long >{

 List<Product> findBycreatedbyContaining(String createdby);

	Iterable<Product> findByproductnameContaining(String productname);

	List<Product> findBycreatedby(boolean b);

	Product save(Product product);

}


