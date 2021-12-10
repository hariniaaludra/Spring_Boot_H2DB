package com.aaludra.spring.jpa.h2.view;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;  

public class Productsxml {  
	private String id;
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

public Productsxml() {
	
}

public Productsxml(String productname, String productcode, String price, String expdate, String mfgdate, String status,
String createdby, String createddate, String updatedby, String updatedate) {  
    super();  
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
public String getId() {  
    return id;  
}  
public void setId(String id) {  
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
public String getUpdateddate() {
	return updateddate;
}
public void setUpdateddate(String updateddate) {
	this.updateddate = updateddate;
}  
/*@Override
public String toString() {
	return "Product [Id=" + id + ",ProductName=" + productname + ", ProductCode=" + productcode + ", price=" + price
			+ ",expdate=" + expdate + ", mfgdate=" + mfgdate + ",Status=" + status + ",Createdby=" + createdby
			+ ", createddate=" + createddate + ",updatedby=" + updatedby + ", Updateddate" + updateddate + "]";*/
 
}  
