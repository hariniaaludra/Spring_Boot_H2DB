package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("userslist")
public class UserObjToJson {
	private List<Userslist> userslist;

	public UserObjToJson() {
		super();
	}

	public UserObjToJson(List<Userslist> userslist) {
		super();
		this.userslist = userslist;
	}

	public List<Userslist> getUserslist() {
		return userslist;
	}

	public void setUserslist(List<Userslist> userslist) {
		this.userslist = userslist;
	}
	
}
	