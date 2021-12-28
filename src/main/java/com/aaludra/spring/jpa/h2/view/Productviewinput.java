package com.aaludra.spring.jpa.h2.view;

public class Productviewinput {
	
    private String productname;

	private String productcode;
	
	private String price;
	
	private String expdate;
	
	private String mfgdate;
	
	private String status;

	public Productviewinput() {
		
	}

	public Productviewinput(String productname, String productcode, String price, String expdate, String mfgdate,
			String status) {
		super();
		this.productname = productname;
		this.productcode = productcode;
		this.price = price;
		this.expdate = expdate;
		this.mfgdate = mfgdate;
		this.status = status;
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
	
}
