package com.aaludra.spring.jpa.h2.validation;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.aaludra.spring.jpa.h2.enum1.EmployeeEnum;
import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Employee;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.util.Dateconvert;
import com.aaludra.spring.jpa.h2.view.EmployeeDetailView;

public class EmployeeValidation {
	public void validate(EmployeeDetailView employee) throws InvalidRequestException {
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

		if ((employee.getEmpPhonenumber().length() > 10)
				| (employee.getEmpPhonenumber().length() < 10)) {
			throw new InvalidRequestException("invalid Phone number");
		}
		// (employee.getEmpDoj().before(employee.getCreatedDate()))

		if  (DateUtil.convertStringToDate(employee.getEmpDoj())
						.before(DateUtil.getCurrentTimeStamp())) {
			throw new InvalidRequestException("invalid date");
		}
		EmployeeEnum eeGrade = EmployeeEnum.checkgrade(employee.getEmpGrade());
		if (eeGrade == null) {
			throw new InvalidRequestException("Invalid Grade");

		}
		if (eeGrade != null && employee.getSalary()!=null) {
			int salary = Integer.valueOf(employee.getSalary());
		
			if(salary<eeGrade.minSalary || salary>eeGrade.maxSalary) {
			throw new InvalidRequestException("Invalid Salary");
			}
		}

	}

}
