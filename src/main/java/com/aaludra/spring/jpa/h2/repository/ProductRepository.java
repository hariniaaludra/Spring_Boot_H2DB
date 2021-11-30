package com.aaludra.spring.jpa.h2.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aaludra.spring.jpa.h2.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long >{

 //List<Product> findBycreatedbyContaining(String createdby);

	List<Product> findByproductnameContaining(String productname);

	//List<Product> findBycreatedby(String createdby);

	List<Product> findBycreatedby(String createdby);

	Product save(String string);

//List<Product> findBycreatedby(boolean b);

	//Product save(Productview productview);

}


