package com.aaludra.spring.jpa.h2.view;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("employeejsonlist")
public class EmployeeJsonInput {
	
		private int id;
		private List<EmployeeList> employeejsonlist;
		
		public EmployeeJsonInput(int id, List<EmployeeList> employeejsonlist) {
			super();
			this.id = id;
			this.employeejsonlist = employeejsonlist;
		}
		public EmployeeJsonInput() {
			
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public List<EmployeeList> getEmployeejsonlist() {
			return employeejsonlist;
		}
		public void setEmployeejsonlist(List<EmployeeList> employeejsonlist) {
			this.employeejsonlist = employeejsonlist;
		}
		
		

}
