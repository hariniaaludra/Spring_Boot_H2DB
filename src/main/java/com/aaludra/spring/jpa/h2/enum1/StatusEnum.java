package com.aaludra.spring.jpa.h2.enum1;

public enum StatusEnum {
	ACTIVE("Active","A"), INACTIVE("Inactive","I");

	String action;
	String symbol;

	StatusEnum(String action, String symbol) {
		this.action=action;
		this.symbol=symbol;	
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
