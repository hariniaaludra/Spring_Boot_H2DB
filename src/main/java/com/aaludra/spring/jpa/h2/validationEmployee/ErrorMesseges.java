package com.aaludra.spring.jpa.h2.validationEmployee;

public class ErrorMesseges {
 int code;
 String messege;
public ErrorMesseges() {
	
}
public ErrorMesseges(int code, String messege) {
	
	this.code = code;
	this.messege = messege;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getMessege() {
	return messege;
}
public void setMessege(String messege) {
	this.messege = messege;
}
}
