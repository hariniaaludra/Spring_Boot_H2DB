package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserObjToXml {
	@XmlElement(name="user")
	private List<UserOutputView> userslist;
	
	public UserObjToXml() {
		
	}

	public UserObjToXml(List<UserOutputView> userslist) {
		super();
		this.userslist = userslist;
	}
	
	public List<UserOutputView> getUserslist() {
		return userslist;
	}

	public void setUserslist(List<UserOutputView> userslist) {
		this.userslist = userslist;
	}
	
}
	