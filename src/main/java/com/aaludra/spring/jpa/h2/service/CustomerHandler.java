package com.aaludra.spring.jpa.h2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.aaludra.spring.jpa.h2.model.Customer;
import com.aaludra.spring.jpa.h2.repository.CustomerRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.CustomerView;
import com.aaludra.spring.jpa.h2.view.CustomerXml;
import com.aaludra.spring.jpa.h2.view.Customerinput;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerHandler {

	@Inject
	CustomerRepository customerRepository;

	public List<Customer> getAllcustomers() {

		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);

		return customers;
	}

	public Optional<Customer> getCustomerById(long id) {
		Optional<Customer> customerData = customerRepository.findById(id);
		return customerData;

	}

	public Customer createcustomer(Customer customer) {
		Customer customerobj = customerRepository
				.save(new Customer(customer.getCustname(), customer.getCustId(), customer.getCity(), customer.getDob(),
						customer.getGstin(), customer.getStatus(), customer.getCreatedBy(), customer.getCreatedDate(),
						customer.getUpdatedBy(), customer.getUpdatedDate(), customer.getGender()));
		return customerobj;
	}

	public long deletecustomer(long id) {
		customerRepository.deleteById(id);
		return id;
	}

	public ResponseEntity<HttpStatus> deleteAllCustomer() {
		customerRepository.deleteAll();
		return null;

	}

	public List<Customer> findBycustname() {
		List<Customer> customer = customerRepository.findBycustname("custname");

		return customer;

	}

	public Customer updateCustomer(Customer customerobj) {
		customerRepository.save(customerobj);
		return customerobj;
	}

	public void testXmlToObject() throws JAXBException, FileNotFoundException {

		File file = new File("D:\\git\\New folder (3)\\Spring_Boot_H2DB\\Customer.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Customerinput.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Customerinput que = (Customerinput) jaxbUnmarshaller.unmarshal(file);

		List<CustomerXml> list = que.getCustomerlist();
		List<CustomerXml> ulist = new ArrayList<>();

		for (CustomerXml pr : list) {
			System.out.println(pr.getId() + " " + pr.getCustname() + " " + pr.getCustId() + " " + pr.getCity() + " "
					+ pr.getDob() + " " + pr.getGstin() + " " + pr.getStatus() + " " + pr.getCreatedBy() + " "
					+ pr.getCreatedDate() + " " + pr.getUpdatedBy() + " " + pr.getUpdatedDate() + " " + pr.getGender());
			System.out.println(pr.getGstin());
			
			customerRepository.save(new Customer(pr.getCustname(), pr.getCustId(), pr.getCity(),
					DateUtil.convertStringToDate(pr.getDob()), pr.getGstin(), pr.getStatus(), pr.getCreatedBy(),
					DateUtil.convertStringToTimestamp(pr.getCreatedDate()), pr.getUpdatedBy(),
					DateUtil.convertStringToTimestamp(pr.getUpdatedDate()), pr.getGender()));
			ulist.add(pr);

		}
	}

	public void testJsonToObject() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		CustomerView customerView = null;
		try {
			customerView = objectMapper.readValue(new File("customer.json"), CustomerView.class);
			Customer customer = this.buildCustomer(customerView);
			customer.setCreatedBy("glad");
			customer.setUpdatedBy("glad");
			customer.setCreatedDate(DateUtil.getCurrentTimeStamp());
			customer.setUpdatedDate(DateUtil.getCurrentTimeStamp());
			System.out.println(customer);
			customerRepository.save(customer);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(customerView);
		
	}

	private Customer buildCustomer(CustomerView customerView) {
		Customer tblpd = new Customer();

		tblpd.setCustname(customerView.getCustname());
		tblpd.setCustId(customerView.getCustId());
		tblpd.setCity(customerView.getCity());
		tblpd.setDob(DateUtil.convertStringToDate(customerView.getDob()));
		tblpd.setGstin(customerView.getGstin());
		tblpd.setStatus(customerView.getStatus());
		tblpd.setCreatedBy(customerView.getCreatedBy());
		tblpd.setCreatedDate(DateUtil.convertStringToTimestamp(customerView.getCreatedDate()));
		tblpd.setUpdatedBy(customerView.getUpdatedBy());
		tblpd.setUpdatedDate(DateUtil.convertStringToTimestamp(customerView.getUpdatedDate()));
		tblpd.setGender(customerView.getGender());
		return tblpd;
		
	}

}
