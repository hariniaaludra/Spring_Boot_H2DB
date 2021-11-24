package com.aaludra.spring.jpa.h2.validationEmployee;

import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Employee;

public class EmplyeeValidation {
	public void Validat(Employee employee) throws InvalidRequestException {
		if (employee.getEmpName() == null) {
			throw new InvalidRequestException("Employee Name mandatory");
		}
		if ((employee.getEmpName().length() < 3) | (employee.getEmpName().length() > 255)) {
			throw new InvalidRequestException("invalid employeename ");
		}
		if (employee.getEmpId() == null) {
			throw new InvalidRequestException(" EmployeeId mandatory");
		}
		if ((employee.getEmpId().length() <= 2) | (employee.getEmpId().length() > 255)) {
			throw new InvalidRequestException("invalid EmployeeId ");
		}

		if ((employee.getEmpPhonenumber().toString().length() > 10)
				| (employee.getEmpPhonenumber().toString().length() < 10)) {
			throw new InvalidRequestException("invalid Phone number");
		}
		if (employee.getEmpDoj().before(employee.getCreatedDate())) {

			throw new InvalidRequestException("invalid date");
		}
		if (employee.getEmpDoj().equals(employee.getCreatedDate())) {

			throw new InvalidRequestException("invalid date");
		}
	}

}
