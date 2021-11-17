package com.aaludra.spring.jpa.h2.model;

import javax.persistence.*;
import java.sql.*;

public class Product {
	    private int id;
		private String productname;
		private String productcode;
		private double price;
		private Date expdate;
        private Date mfgdate;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
		}
		public String getProductcode() {
			return productcode;
		}
		public void setProductcode(String productcode) {
			this.productcode = productcode;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Date getExpdate() {
			return expdate;
		}
		public void setExpdate(Date expdate) {
			this.expdate = expdate;
		}
		public Date getMfgdate() {
			return mfgdate;
		}
		public void setMfgdate(Date mfgdate) {
			this.mfgdate = mfgdate;
		}

}
