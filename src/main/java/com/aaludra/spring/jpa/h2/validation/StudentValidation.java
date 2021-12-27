package com.aaludra.spring.jpa.h2.validation;

import com.aaludra.spring.jpa.h2.enum1.CourseEnum;
import com.aaludra.spring.jpa.h2.enum1.DegreeEnum;
import com.aaludra.spring.jpa.h2.exception.InvalidRequestException;
import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.view.Studentview;

public class StudentValidation {
	public void validate(Studentview student) throws InvalidRequestException {
		if (student.getStudentname() == null) {
			throw new InvalidRequestException("Studentname is Mandatory");
		}
		if (student.getStudentname().length() > 25) {
			throw new InvalidRequestException("Studentname Invalid length");
		}
		if (student.getRollnumber().length() > 25) {
			throw new InvalidRequestException("Rollnumber is Mandatory");
		}
		if (student.getCourse().length() > 25) {
			throw new InvalidRequestException("Course is Invalid length");
		}
		if (student.getCourse() == null) {
			throw new InvalidRequestException("Course is Mandatory");
		}
		if (student.getDegree() == null) {
			throw new InvalidRequestException("Degree is Mandatory");
		}
		if (student.getDegree().length() > 25) {
			throw new InvalidRequestException("Degree is Invalid length");
		}
		if (student.getCreatedby().length() > 25) {
			throw new InvalidRequestException("Createdby length is invalid ");
		}
	    if (student.getUpdatedby().length() > 25) {
			throw new InvalidRequestException("Updatedby length is invalid");
		}
		CourseEnum b = CourseEnum.checkCourse(student.getCourse());
		if (b==null) {
			throw new InvalidRequestException("Invalid Course");
		}
		DegreeEnum c = DegreeEnum.check(student.getDegree());
		if (c==null) {
			throw new InvalidRequestException("Invalid Degree");
		}

	}
	
	}




