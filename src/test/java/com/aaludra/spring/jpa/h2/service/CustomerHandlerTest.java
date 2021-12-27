package com.aaludra.spring.jpa.h2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.LongAccumulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.aaludra.spring.jpa.h2.model.Customer;
import com.aaludra.spring.jpa.h2.repository.CustomerRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.CustomerViewInput;
import com.aaludra.spring.jpa.h2.view.CustomerViewOutput;

@ExtendWith(MockitoExtension.class)
class CustomerHandlerTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerHandler handler;
	private CustomerViewInput customerview1;

	private Customer customer;
	private Customer customer1;
	private Customer customer2;
	List<Customer> customerList = new ArrayList<>();
	List<CustomerViewInput> cust = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		customerview1 = new CustomerViewInput("0","glad" , "123456789012345","chennai", "2000-12-12", "123456789012345","paid", "male");
		
		customer = new Customer(0, "glad", "123456789012345", "chennai", DateUtil.convertStringToDate("2000-12-12"),
				"123456789012345", "paid", "ram", DateUtil.convertStringToTimestamp("2000-12-12"), "raj",
				DateUtil.convertStringToTimestamp("2000-12-12"), "male");
		customer1 = new Customer(1, "surya", "123456789012345", "chennai", DateUtil.convertStringToDate("2000-12-12"),
				"123456789012345", "paid", "ram", DateUtil.convertStringToTimestamp("2000-12-12"), "raj",
				DateUtil.convertStringToTimestamp("2000-12-12"), "male");
		customer2 = new Customer(2, "raj", "123456789012345", "chennai", DateUtil.convertStringToDate("2000-12-12"),
				"123456789012345", "paid", "ram", DateUtil.convertStringToTimestamp("2000-12-12"), "raj",
				DateUtil.convertStringToTimestamp("2000-12-12"), "male");
		customerList.add(customer);
		customerList.add(customer1);
		customerList.add(customer2);
		cust.add(customerview1);
		

	}

	@Test
	public void getAllcustomersTest() {
		when(customerRepository.findAll()).thenReturn(customerList);
		List<CustomerViewOutput> customerli = handler.getAllcustomers();
		//assertThat(customerli.size()).isGreaterThan(2);
		 assertEquals(customerli.get(0).getCustname(), customerList.get(0).getCustname());
	}

	@Test
	public void getCustomerByIdTest() {
		when(customerRepository.findById(0L)).thenReturn(Optional.ofNullable(customer));
		Optional<CustomerViewOutput> customerli = handler.getCustomerById(customer.getId());
		assertEquals(customerli.get().getCustname(), customer.getCustname());
	}

	@Test
	public void deletecustomeridTest() {
		long a = 0;
		handler.deletecustomer(a);
		verify(customerRepository, times(1)).deleteById(a);

	}

	@Test
	public void findBycustnameTest() {
		when(customerRepository.findBycustname("glad")).thenReturn(customerList);
		List<CustomerViewOutput> out = handler.findBycustname();
		assertThat(out.get(0).getCustname()).isEqualTo(customer.getCustname());

	}

	@Test
	public void createcustomerTest() {
		customerview1 = new CustomerViewInput("1","glad" , "123456789012345","chennai", "2000-12-12", "123456789012345","paid", "male");
		when(customerRepository.save(Mockito.any())).thenReturn(customer);
		CustomerViewOutput customerview = handler.createcustomer(customerview1);
		assertThat(handler.createcustomer(customerview1).getCustname()).isEqualTo(customerview.getCustname());
		
	}

	@Test
	public void updateCustomerTest() {
		customerview1 = new CustomerViewInput("1","glad" , "123456789012345","chennai", "2000-12-12", "123456789012345","paid", "male");
		when(customerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(customer));
		when(customerRepository.save(Mockito.any())).thenReturn(customer);
		Optional<CustomerViewOutput> custlist = Optional.ofNullable(handler.updateCustomer(customer.getId(), customerview1));
		assertThat(handler.updateCustomer(customer.getId(), customerview1).getCustname()).isEqualTo(custlist.get().getCustname());
	}
	@Test
	public void deleteAllTest() {
		handler.deleteAllCustomer();
		verify(customerRepository,times(1)).deleteAll();
	}

}
