package com.aaludra.spring.jpa.h2.view;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("userslist")
public class UserJsoninput {
		
		private List<Userslist> userslist;
		
		public UserJsoninput () {
			
		}

		public UserJsoninput(List<Userslist> userslist) {
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
		

