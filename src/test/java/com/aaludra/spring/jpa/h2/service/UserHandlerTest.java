package com.aaludra.spring.jpa.h2.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.util.DateUtil;

class UserHandlerTest {
	 
	UserHandler userhandler = new UserHandler();

	@Test
	public void Test1() {
		userhandler.getAllUser();
		List<User> list = new ArrayList<>();
		list.get(1).setUsername("sam");
		list.get(0).setDisplayname("sam");
		list.get(2).setPassword("abcd");
		list.get(3).setDob(DateUtil.convertStringToDate("2021-09-12"));
		list.get(4).setPhoneno((long) 1234567892);
		list.get(5).setStatus("Active");
		list.get(6).setCreatedby("aaa");
		list.get(7).setCreateddate(DateUtil.getCurrentTimeStamp());
		list.get(8).setUpdatedby("bbb");
		list.get(9).setUpdateddate(DateUtil.convertStringToTimestamp("2021-09-12"));
		
		
		
		
	
	}

	//public void getIdTest() {
		
	}
//}
