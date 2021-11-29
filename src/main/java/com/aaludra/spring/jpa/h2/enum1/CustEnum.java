package com.aaludra.spring.jpa.h2.enum1;

public enum CustEnum {
	MALE("male"), FEMALE("female");

	String action;
	CustEnum(String action){
		this.action=action;
	}
	public static CustEnum check(String gender) {
		for (CustEnum value : CustEnum.values()) {
			if (value.action.equals(gender)) {
				return value;
			}
		}
		return null;

	}
}
