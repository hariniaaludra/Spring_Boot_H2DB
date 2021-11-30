package com.aaludra.spring.jpa.h2.enum1;

public enum DegreeEnum {
	BE("BE"), BTECH("BTECH"), BCA("BCA"), MCA("MCA");

	String action;

	DegreeEnum(String action) {
		this.action = action;
	}

	public static DegreeEnum check(String degree) {
		for (DegreeEnum value : DegreeEnum.values()) {
			if (value.action.equals(degree)) {
				return value;
			}
		}
		return null;
	}
}
