package com.aaludra.spring.jpa.h2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aaludra.spring.jpa.h2.model.Employee;
import com.aaludra.spring.jpa.h2.repository.EmployeeRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;

import com.aaludra.spring.jpa.h2.view.EmployeeDetailView;

import com.aaludra.spring.jpa.h2.view.EmployeeViewOut;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EmployeeHandlerTest {
	


	@Mock
	EmployeeRepository employeeRepository;

	//EmployeeDetailView employeeDetailsView;
	//EmployeeValidation employeeValidation;
	@InjectMocks
	EmployeeHandler empHandler;

	private Employee employee;
	private Employee employee1;
	private Employee employee2;
	private List<Employee> employeeList;
	//private EmployeeDetailView employeeDetailsView
		@BeforeEach
	public void setUp() {
		employeeList = new ArrayList<>();
		
		employee = new Employee("Gomathi", "EMP01", 881832998l, "Coimbatore", DateUtil.convertStringToDate("2021-12-29"), "Active","Admin",DateUtil.getCurrentTimeStamp(),"Admin",DateUtil.getCurrentTimeStamp(),"G1",10000);
		
	}	
	@Test
	
	public void getAllEmployeeTest() {
		
		employeeList.add(employee);
		when(employeeRepository.findAll()).thenReturn(employeeList);
		// assertThat(employeelist.size()).isGreaterThan(0);
		List<EmployeeViewOut> empviewOut = empHandler.getAllEmployee();
		assertEquals(empviewOut.get(0).getEmpName(),employeeList.get(0).getEmpName());
		
	}
	@Test
	public void getEmployeeById_test() {
	
	Mockito.when( employeeRepository.findById(any())).thenReturn(Optional.ofNullable(employee));
	Optional<EmployeeViewOut> empviewOut = empHandler.getEmployeeById(employee.getId());
	assertEquals(empviewOut.get().getEmpAddress(),Optional.ofNullable(employee).get().getEmpAddress());
    }
	
	@Test
	public void deleteByIdTest1() {
		int empId=1;
		empHandler.deleteEmployee(empId);
		verify(employeeRepository).deleteById(empId);
	
	
		
	}
	@Test
	public void deleteAllTest() {
	
		empHandler.deleteAll();
		verify(employeeRepository).deleteAll();
	
	}
@Test
public void createEmployeeSaveTest() {
EmployeeDetailView employeeView= new EmployeeDetailView("Gomathi", "EMP01", Long.toString(7835486l), "Coimbatore", "29-12-2021", "G1","10000");
when(employeeRepository.save(any())).thenReturn(employee);
EmployeeViewOut empviewOut = empHandler.createEmployee(employeeView);
assertThat(empHandler.createEmployee(employeeView).getEmpName()).isEqualTo(empviewOut.getEmpName());
}



@Test
  public void updateEmployeeSaveTest() {
	EmployeeDetailView employeeView= new EmployeeDetailView("abi", "EMP01","345", "Coimbatore","29-12-2021", "G1","10000");
	when( employeeRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(employee));
	when(employeeRepository.save(Mockito.any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
	Optional<EmployeeViewOut> empviewOut = Optional.ofNullable(empHandler.updateEmployee(employee.getId(), employeeView));
	assertThat(empHandler.updateEmployee(employee.getId(), employeeView).getEmpName()).isEqualTo(empviewOut.get().getEmpName());	
}


}
