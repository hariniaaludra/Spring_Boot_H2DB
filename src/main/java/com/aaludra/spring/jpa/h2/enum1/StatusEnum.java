package com.aaludra.spring.jpa.h2.enum1;

public enum StatusEnum {
Active("Active"),Inactive("Inactive");

	String action;
	

	StatusEnum(String action) {
		this.action=action;
			}
	
	public static StatusEnum check(String status) {
		for (StatusEnum value : StatusEnum.values()) {
			if (value.action.equals(status)) {
				return value ;
			}
		}
		return null;
	}
}
