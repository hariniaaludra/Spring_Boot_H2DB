package com.aaludra.spring.jpa.h2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.repository.ProductRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.Productviewinput;
import com.aaludra.spring.jpa.h2.view.Productviewoutput;

@ExtendWith(MockitoExtension.class)
class ProductHandlerTest2 {
	@Mock
	private ProductRepository productRepository;
	
	@Autowired
	Productviewoutput out;

	@InjectMocks
	ProductHandler handler;
	private Product product1;
	private Product product2;
	List<Product> productList;
	Productviewinput piv;

	@BeforeEach
	public void setup() {
		productList = new ArrayList<>();
		product1 = new Product(0, "wheat", "Id101", 100.2, DateUtil.convertStringToDate("2000-10-11"),
				DateUtil.convertStringToDate("2001-10-11"), "fiber", "suriya",
				DateUtil.convertStringToTimestamp("2000-10-11"), "suriya",
				DateUtil.convertStringToTimestamp("2000-10-11"));
		product2 = new Product(1, "rice", "Id101", 100.2, DateUtil.convertStringToDate("2000-10-11"),
				DateUtil.convertStringToDate("2001-10-11"), "fiber", "suriya",
				DateUtil.convertStringToTimestamp("2000-10-11"), "suriya",
				DateUtil.convertStringToTimestamp("2000-10-11"));

		productList.add(product1);
		productList.add(product2);

	}

	@Test
	public void getAll() {

		when(productRepository.findAll()).thenReturn(productList);
		List<Productviewoutput> productsli = handler.getAllProduct();
		System.out.println(handler.getAllProduct());
		System.out.println(productList.get(0).getProductname() + " = " + productsli.get(0).getProductname());
		System.out.println(productList);

		assertThat(productsli.get(0).getProductcode()).isEqualTo(productList.get(0).getProductcode());
		assertEquals(productList.get(1).getProductname(), productsli.get(1).getProductname());
		// assertThat(productsli.size()).isGreaterThan(0);
	}

	@Test
	public void getById() {

		when(productRepository.findById(0L)).thenReturn(Optional.ofNullable(product1));
		Optional<Productviewoutput> productsli = handler.getProductById(product1.getId());
		// assertThat(handler.getProductById(product1.getId())).isEqualTo(Optional.ofNullable(product1));
		assertEquals(productsli.get().getProductname(), product1.getProductname());
	}

	@Test
	public void deleteProductId() {

		long a = 0L;
		handler.deleteproduct(a);
		verify(productRepository, times(1)).deleteById(a);
	}

	@Test
	public void deleteAll() {
		handler.deleteAllProduct();
		verify(productRepository, times(1)).deleteAll();

	}

	@Test
	public void findByName() {
		when(productRepository.findByproductname("wheat")).thenReturn(productList);
		List<Productviewoutput> out = handler.findByproductname();
		assertThat(out.get(0).getProductname()).isEqualTo(product1.getProductname());

	}

	@Test
	public void saveProductall() {
		piv = new Productviewinput("wheat", "Id103", "100.2", "2000-10-11", "2001-10-11", "fiber");
		when(productRepository.save(any())).thenReturn(product1);
		Productviewoutput out = handler.createproduct(piv);
		System.out.println(out);
		assertThat(handler.createproduct(piv).getProductname()).isEqualTo(out.getProductname());

	}

	@Test
	public void update()  {
		
		piv = new Productviewinput("rice", "Id104", "100.6", "2000-10-12", "2001-10-11", "fiber");
		
		when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product1));
		//when(productRepository.save(any())).thenReturn(out.getProductname());
		
		when(productRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
		
		Optional<Productviewoutput> productslli = Optional.ofNullable(handler.updateProduct(product1.getId(), piv));

		assertThat(handler.updateProduct(product1.getId(), piv).getProductname()).isEqualTo(productslli.get().getProductname());

		System.out.println(handler.updateProduct(product1.getId(), piv).getProductname() + "= "+ productslli.get().getProductname());
		System.out.println(handler.updateProduct(product1.getId(), piv));

	}

	//@Test
	//public void 
}
