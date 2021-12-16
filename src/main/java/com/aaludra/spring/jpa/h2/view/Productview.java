package com.aaludra.spring.jpa.h2.view;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Productview {
	private String productname;
	
	private String productcode;
	
	private String price;
	
	private String expdate;
	
	private String mfgdate;
	
	private String status;
	
	private String createdby;
	
	private String createddate;
	
	private String updatedby;
	
	private String updateddate;

	public Productview() {
		
	}

	public Productview(String productname, String productcode, String price, String expdate, String mfgdate,
			String status, String createdby, String createddate, String updatedby, String updatedate) {
		
		this.productname = productname;
		
		this.productcode = productcode;
		
		this.price = price;
		
		this.expdate = expdate;
		
		this.mfgdate = mfgdate;
		
		this.status = status;
		
		this.createdby = createdby;
		
		this.createddate = createddate;
		
		this.updatedby = updatedby;
		
		this.updateddate = updatedate;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public String getMfgdate() {
		return mfgdate;
	}

	public void setMfgdate(String mfgdate) {
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

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getUpdatedate() {
		return updateddate;
	}

	public void setUpdatedate(String updatedate) {
		this.updateddate = updatedate;
	}
	@Override
	public  String toString() {
		return "Product [ProductName=" + productname + ", ProductCode=" + productcode + ", price=" + price + ",expdate="
				+ expdate + ", mfgdate=" + mfgdate + ",Status=" + status + ",Createdby=" + createdby + ", createddate="
				+ createddate + ",updatedby=" + updatedby + ", Updateddate" + updateddate + "]";
	}
}
