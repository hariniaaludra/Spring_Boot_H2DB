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

import com.aaludra.spring.jpa.h2.model.Product;
import com.aaludra.spring.jpa.h2.repository.ProductRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.Pjsoninput;
import com.aaludra.spring.jpa.h2.view.ProductViewData;
import com.aaludra.spring.jpa.h2.view.Productinput;
import com.aaludra.spring.jpa.h2.view.Productsxml;
import com.aaludra.spring.jpa.h2.view.Productviewinput;
import com.aaludra.spring.jpa.h2.view.Productviewoutput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductHandler {

	@Inject
	ProductRepository productRepository;

	public List<Productviewoutput> getAllProduct() {
		List<Product> product = productRepository.findAll();
		List<Productviewoutput> out=new ArrayList<>();
		for (Product prd:product) {
			Productviewoutput output=this.buildResponseView(prd);
			out.add(output);
		}
		
		return out ;
	}


	public Optional<Productviewoutput> getProductById(long id) {
		Optional<Product> productData = productRepository.findById(id);
	Productviewoutput out =this.buildResponseView(productData.get());
		return Optional.of(out);

	}





	public Productviewoutput createproduct(Productviewinput productviewinput) {

		Product productobj = this.buildProduct(productviewinput);
		Productviewoutput out = this.buildResponseView( productRepository.save(productobj));
		return out;

	}

	public long deleteproduct(long id) {
		productRepository.deleteById(id);
		return id;
	}

	public ResponseEntity<HttpStatus> deleteAllProduct() {
		productRepository.deleteAll();
		return null;

	}

	public List<Productviewoutput> findByproductname() {
		List<Product> product = productRepository.findByproductname("wheat");
		List<Productviewoutput> out=new ArrayList<>();
		for(Product prd:product) {
			Productviewoutput output=this.buildResponseView(prd);
			out.add(output);
			
		}

		return out;

	}

	public Productviewoutput updateProduct(long id, Productviewinput productobj) {
		Optional<Product> productData = productRepository.findById(id);
		if (productData.isPresent()) {
			Product product = this.buildProduct(productobj);
			product.setId(id);
			Productviewoutput out= this.buildResponseView(productRepository.save(product));
			return out;
		}
		return null;
	} 

	public Product buildProduct(Productviewinput Productview) {
		Product tblpd = new Product();

		tblpd.setProductname(Productview.getProductname());
		tblpd.setProductcode((Productview.getProductcode()));
		tblpd.setPrice(Double.parseDouble(Productview.getPrice()));
		tblpd.setExpdate(DateUtil.convertStringToDate(Productview.getExpdate()));
		tblpd.setMfgdate(DateUtil.convertStringToDate(Productview.getMfgdate()));
		tblpd.setStatus(Productview.getStatus());
		tblpd.setCreatedby("suriya");
		tblpd.setCreateddate(DateUtil.convertStringToTimestamp("2020-10-11"));
		tblpd.setUpdatedby("suriya");
		tblpd.setUpdatedate(DateUtil.convertStringToTimestamp("2020-10-11"));

		return tblpd;
	}

	public Productviewoutput buildResponseView(Product product) 
	{
	
		Productviewoutput productViewOutput=new Productviewoutput();
		
		productViewOutput.setId(Long.toString(product.getId()));
			
		productViewOutput.setProductname(product.getProductname()) ;
				
		productViewOutput.setProductcode(product.getProductcode());
				
		productViewOutput.setPrice(Double.toString(product.getPrice())) ;
				
		productViewOutput.setExpdate(product.getExpdate().toString()) ;
				
		productViewOutput.setMfgdate(product.getMfgdate().toString());
				
		productViewOutput.setStatus(product.getStatus());
				
		productViewOutput.setCreatedby( product.getCreatedby());
				
		productViewOutput.setCreateddate(product.getCreateddate().toString());
				
		productViewOutput.setUpdatedby(product.getUpdatedby());
				
		productViewOutput.setUpdatedate(product.getUpdatedate().toString());
				
			
		return productViewOutput;
	}
	public void testXmlToObject() throws JAXBException, FileNotFoundException {

		File file = new File("C:\\files\\New folder (2)\\Spring_Boot_H2DB\\src\\main\\java\\product.xml");
	
		JAXBContext jaxbContext = JAXBContext.newInstance(Productinput.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Productinput que = (Productinput) jaxbUnmarshaller.unmarshal(file);

		List<Productsxml> list = que.getProductlist();
		List<Productsxml> ulist = new ArrayList<>();

		for (Productsxml pr : list) {
			System.out.println(pr.getId() + " " + pr.getProductname() + " " + pr.getProductcode() + " " + pr.getPrice()
					+ " " + pr.getExpdate() + " " + pr.getMfgdate() + " " + pr.getStatus() + " " + pr.getCreatedby()
					+ " " + pr.getCreateddate() + " " + pr.getUpdatedby() + " " + pr.getUpdateddate());
			productRepository.save(new Product(0, pr.getProductname(), pr.getProductcode(),
					Double.parseDouble(pr.getPrice()), DateUtil.convertStringToDate(pr.getExpdate()),
					DateUtil.convertStringToDate(pr.getMfgdate()), pr.getStatus(), pr.getCreatedby(),
					DateUtil.convertStringToTimestamp(pr.getCreateddate()), pr.getUpdatedby(),
					DateUtil.convertStringToTimestamp(pr.getUpdateddate())));
			ulist.add(pr);
		}
	}
//	public void testJsonToObject() throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		Productview productview=new Productview() ;
//		
//		try {
//			productview= mapper.readValue(new File("product.json"),Productview.class);
//			Product product=this.buildProduct(productview);
//			product.setCreatedby("Admin");
//			product.setUpdatedby("Admin");
//			product.setCreateddate(DateUtil.getCurrentTimeStamp());
//			product.setUpdatedate(DateUtil.getCurrentTimeStamp());
//	            System.out.println(product);
//	            productRepository.save(product);
//	        }catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        System.out.println(productview);
//	        
	// }

	public void testJsonToObject() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Pjsoninput productview = objectMapper
				.readValue(new File("C:\\files\\New folder (2)\\Spring_Boot_H2DB\\product.json"), Pjsoninput.class);
		List<Productsxml> asd = new ArrayList<>();
		List<Productsxml> jsonlList = productview.getProductlist();

		System.out.println("asd:" + jsonlList.size());

		for (Productsxml prd : jsonlList) {
			System.out.println(prd.getProductname() + " " + prd.getProductcode() + " " + prd.getPrice() + " "
					+ prd.getExpdate() + " " + prd.getMfgdate() + " " + prd.getStatus() + " " + prd.getCreatedby() + " "
					+ prd.getCreateddate() + " " + prd.getUpdatedby() + " " + prd.getUpdateddate());
			asd.add(prd);

			Product prds = new Product();
			prds.setStatus(prd.getStatus());

			productRepository.save(new Product(0, prd.getProductname(), prd.getProductcode(),
					Double.parseDouble(prd.getPrice()), DateUtil.convertStringToDate(prd.getExpdate()),
					DateUtil.convertStringToDate(prd.getMfgdate()), prd.getStatus(), prd.getCreatedby(),
					DateUtil.convertStringToTimestamp(prd.getCreateddate()), prd.getUpdatedby(),
					DateUtil.convertStringToTimestamp(prd.getUpdateddate())));

		}

	}

	public void databaseToJson()throws JsonParseException, JsonMappingException, IOException, SQLException  {
		
		
		List<Productviewoutput> arl=this.getAllProduct();
             File jsonfile=new File("C:\\files\\New folder (2)\\Spring_Boot_H2DB\\reverse.json");
             
             ObjectMapper om=new ObjectMapper();
             om.writeValue(jsonfile, arl);
            
             System.out.println("Done!");
            
         }
	


 public   void databaseToXml() throws JAXBException, FileNotFoundException, SQLException {
	
	 ProductViewData productlist=new ProductViewData(getAllProduct());

	 
         JAXBContext jaxbContext = JAXBContext.newInstance(ProductViewData.class);
         Marshaller marshaller = jaxbContext.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         File file = new File("C:\\files\\New folder (2)\\Spring_Boot_H2DB\\databasetoxml.xml");
          marshaller.marshal(productlist, file);
        

        
  }
}


