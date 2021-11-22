package com.aaludra.spring.jpa.h2.model;

import javax.persistence.*;
import java.sql.*;
@Entity
@Table(name = "TBL_PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	@Column(name = "PRODUCT_NAME")
		private String productname;
	@Column(name = "PRODUCT_CODE")
		private String productcode;
	@Column(name = "PRICE")
		private double price;
	@Column(name = "EXP_DATE")
		private Date expdate;
	@Column(name = "MFG_DATE")
        private Date mfgdate;
	@Column(name = "STATUS")
	private String status;
@Column(name = "CREATED_BY")
	private String createdby;
@Column(name = "CREATED_DATE")
	private Timestamp createddate;
@Column(name = "UPDATED_BY")
    private String updatedby;
@Column(name = "UPDATED_DATE")
private Timestamp updateddate;
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
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCreatedby() {
			return createdby;
		}
		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}
		public Timestamp getCreateddate() {
			return createddate;
		}
		public void setCreateddate(Timestamp createddate) {
			this.createddate = createddate;
		}
		public String getUpdatedby() {
			return updatedby;
		}
		public void setUpdatedby(String updatedby) {
			this.updatedby = updatedby;
		}
		public Timestamp getUpdatedate() {
			return updateddate;
		}
		public void setUpdatedate(Timestamp updatedate) {
			this.updateddate = updatedate;
		}
		@Override
		public String toString() {
			return "Product [Id=" + id + ",ProductName=" + productname + ", ProductCode=" + productcode + ", price=" + price +",expdate=" + expdate + ", mfgdate=" + mfgdate + ",Status=" + status + ",Createdby=" + createdby + ", createddate=" + createddate +",updatedby=" + updatedby + ", Updateddate" + updateddate +  "]";
		}

}
