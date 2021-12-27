package com.aaludra.spring.jpa.h2.enum1;

public enum EmployeeEnum {
 G1("Grade1", 10000,20000),
 G2("Grade2",20000,40000),
 G3("Grade3",40000,100000);
	public String grade;
	public int minSalary,maxSalary;
	
	private EmployeeEnum(String grade, int minSalary, int maxSalary) {
		this.grade = grade;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public static EmployeeEnum checkgrade(String gradeFind) {
		for (EmployeeEnum empGrade : EmployeeEnum.values()) {

			if (empGrade.grade.equals(gradeFind)) {
				return empGrade;
			}
		}
		return null;           
		
	}

	
	}

