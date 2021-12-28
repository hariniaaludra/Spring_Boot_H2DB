package com.aaludra.spring.jpa.h2.view;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


	@XmlRootElement(name = "products")
	@XmlAccessorType(XmlAccessType.FIELD)
	public class ProductViewData {
		
		
		
		 @XmlElement(name = "product")
		private List<Productviewoutput> productlist;
		
		public ProductViewData() {
			
		}

		public ProductViewData( List<Productviewoutput> productlist) {
			super();
			this. productlist =  productlist;
		}

		public List<Productviewoutput> getProductlist() {
			return productlist;
		}

		public void setProductlist(List<Productviewoutput> productlist) {
			this.productlist = productlist;
		}
	}


