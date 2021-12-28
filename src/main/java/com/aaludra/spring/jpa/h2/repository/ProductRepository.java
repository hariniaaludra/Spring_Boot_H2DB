package com.aaludra.spring.jpa.h2.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaludra.spring.jpa.h2.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long >{

	List<Product> findByproductnameContaining(String createdby );

	List<Product> findByproductname(String productname);


}


