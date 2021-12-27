package com.aaludra.spring.jpa.h2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.model.Customer;
import com.aaludra.spring.jpa.h2.repository.CustomerRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.Cjsoninput;
import com.aaludra.spring.jpa.h2.view.CustomerReverseDataXml;
import com.aaludra.spring.jpa.h2.view.CustomerViewInput;
import com.aaludra.spring.jpa.h2.view.CustomerViewOutput;
import com.aaludra.spring.jpa.h2.view.CustomerXml;
import com.aaludra.spring.jpa.h2.view.Customerinput;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerHandler {

	@Inject
	CustomerRepository customerRepository;

	public List<CustomerViewOutput> getAllcustomers() {

		List<Customer> customers = customerRepository.findAll();
		List<CustomerViewOutput> out = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerViewOutput customerViewOutput = this.buildResponseView(customer);
			out.add(customerViewOutput);
		}
		return out;
	}

	public Optional<CustomerViewOutput> getCustomerById(long id) {
		Optional<Customer> customerData = customerRepository.findById(id);
		CustomerViewOutput out = this.buildResponseView(customerData.get());
		return Optional.of(out);

	}

	public CustomerViewOutput createcustomer(CustomerViewInput customerviewin) {
		Customer customer = this.buildCustomer(customerviewin);
		CustomerViewOutput out = this.buildResponseView(customerRepository.save(customer));
		return out;

	}

	public long deletecustomer(long id) {
		customerRepository.deleteById(id);
		return id;
	}

	public ResponseEntity<HttpStatus> deleteAllCustomer() {
		customerRepository.deleteAll();
		return null;

	}

	public List<CustomerViewOutput> findBycustname() {
		List<Customer> customer = customerRepository.findBycustname("glad");
		List<CustomerViewOutput> out = new ArrayList<>();
		for (Customer cust : customer) {
			CustomerViewOutput customeroutput = this.buildResponseView(cust);
			out.add(customeroutput);
		}

		return out;

	}

	public CustomerViewOutput updateCustomer(long id, CustomerViewInput customerview) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			Customer cust = this.buildCustomer(customerview);
			cust.setId(id);
			CustomerViewOutput out = this.buildResponseView(customerRepository.save(cust));
			return out;
		}
		return null;
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

			customerRepository.save(new Customer(0, pr.getCustname(), pr.getCustId(), pr.getCity(),
					DateUtil.convertStringToDate(pr.getDob()), pr.getGstin(), pr.getStatus(), pr.getCreatedBy(),
					DateUtil.convertStringToTimestamp(pr.getCreatedDate()), pr.getUpdatedBy(),
					DateUtil.convertStringToTimestamp(pr.getUpdatedDate()), pr.getGender()));
			ulist.add(pr);

		}
	}

	public void testJsonToObject() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Cjsoninput customerview = objectMapper
				.readValue(new File("D:\\git\\New folder (4)\\Spring_Boot_H2DB\\customer.json"), Cjsoninput.class);
		List<CustomerXml> asd = new ArrayList<>();
		List<CustomerXml> jsonlList = customerview.getCustomerlist();

		System.out.println("asd:" + jsonlList.size());

		for (CustomerXml cust : jsonlList) {
			System.out.println(cust.getCustname() + " " + cust.getCustId() + " " + cust.getCity() + " " + cust.getDob()
					+ " " + cust.getGstin() + " " + cust.getStatus() + " " + cust.getCreatedBy() + " "
					+ cust.getCreatedDate() + " " + cust.getUpdatedBy() + " " + cust.getUpdatedDate() + " "
					+ cust.getGender());
			asd.add(cust);

			Customer custs = new Customer();
			custs.setStatus(cust.getStatus());

			customerRepository.save(new Customer(0, cust.getCustname(), cust.getCustId(), cust.getCity(),
					DateUtil.convertStringToDate(cust.getDob()), cust.getGstin(), cust.getStatus(), cust.getCreatedBy(),
					DateUtil.convertStringToTimestamp(cust.getCreatedDate()), cust.getUpdatedBy(),
					DateUtil.convertStringToTimestamp(cust.getUpdatedDate()), cust.getGender()));

		}

	}

	public Customer buildCustomer(CustomerViewInput customerviewin) {
		Customer tblpd = new Customer();

		tblpd.setCustname(customerviewin.getCustname());
		tblpd.setCustId(customerviewin.getCustId());
		tblpd.setCity(customerviewin.getCity());
		tblpd.setDob(DateUtil.convertStringToDate(customerviewin.getDob()));
		tblpd.setGstin(customerviewin.getGstin());
		tblpd.setStatus(customerviewin.getStatus());
		tblpd.setCreatedBy("Admin");
		tblpd.setCreatedDate(DateUtil.convertStringToTimestamp("1999-12-12"));
		tblpd.setUpdatedBy("Admin");
		tblpd.setUpdatedDate(DateUtil.convertStringToTimestamp("2000-12-12"));
		tblpd.setGender(customerviewin.getGender());
		return tblpd;

	}

	public CustomerViewOutput buildResponseView(Customer customer) {
		CustomerViewOutput customerViewOutput = new CustomerViewOutput();
		customerViewOutput.setId(Long.toString(customer.getId()));
		customerViewOutput.setCustname(customer.getCustname());
		customerViewOutput.setCustId(customer.getCustId());
		customerViewOutput.setCity(customer.getCity());
		customerViewOutput.setDob(customer.getDob().toString());
		customerViewOutput.setGstin(customer.getGstin());
		customerViewOutput.setStatus(customer.getStatus());
		customerViewOutput.setCreatedBy(customer.getCreatedBy());
		customerViewOutput.setCreatedDate(customer.getCreatedDate().toString());
		customerViewOutput.setUpdatedBy(customer.getUpdatedBy());
		customerViewOutput.setUpdatedDate(customer.getUpdatedDate().toString());
		customerViewOutput.setGender(customer.getGender());
		return customerViewOutput;

	}

	public void dbDataToJsonfile() throws SQLException, JsonGenerationException, JsonMappingException, IOException {

		List<CustomerViewOutput> ar = this.getAllcustomers();
		File jsonfile = new File("D:\\git\\New folder (4)\\Spring_Boot_H2DB\\CustomerReverse.json");
		ObjectMapper om = new ObjectMapper();
		om.writeValue(jsonfile, ar);

		System.out.println("Sucess!");
	}

	public void dBDataToxml() throws JAXBException, SQLException, IOException {

		CustomerReverseDataXml customerRev = new CustomerReverseDataXml(this.getAllcustomers());
		File file = new File("D:\\git\\New folder (4)\\Spring_Boot_H2DB\\CustomerReverse.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(CustomerReverseDataXml.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(customerRev, file);
	}

}
