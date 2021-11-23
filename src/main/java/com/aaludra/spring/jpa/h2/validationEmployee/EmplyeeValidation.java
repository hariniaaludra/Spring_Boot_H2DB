package com.aaludra.spring.jpa.h2.validationEmployee;
import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Employee;
public class EmplyeeValidation {
	public void Validat( Employee employee) throws InvalidRequestException {
		if(employee.getEmpName()==null) {
			throw new InvalidRequestException("Username mandatory");
		}
		if ((employee.getEmpName().length()<3)){
			throw new InvalidRequestException("invalid Username");
		}
		//int count =1;
		//if(employee.getEmpName().repeat(count) != null) {
			//throw new InvalidRequestException("UserName is Already exist");
		//}
	}
	
	}
	



