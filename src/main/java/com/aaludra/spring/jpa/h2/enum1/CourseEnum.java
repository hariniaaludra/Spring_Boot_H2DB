package com.aaludra.spring.jpa.h2.enum1;

public enum CourseEnum {
	    CS("CS"),
		ECE("ECE"),
		IT("IT"),
		MECH("MECH"),
		EEE("EEE");
	String action;
	CourseEnum(String action){ 
		this.action=action;
	}

	 public static CourseEnum checkCourse(String course) {
			for (CourseEnum value : CourseEnum.values()) {
				if (value.action.equals(course)) {
					return value ;
				}
			}
			return null;
		}
	}

	
   
