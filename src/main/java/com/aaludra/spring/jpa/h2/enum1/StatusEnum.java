package com.aaludra.spring.jpa.h2.enum1;

public enum StatusEnum {
Active,Inactive;

	//String action;
	//String symbol;

	//StatusEnum(String action, String symbol) {
		//this.action=action;
		//this.symbol=symbol;	
	//}
	
	public static StatusEnum check(String status) {
		for (StatusEnum value : StatusEnum.values()) {
			if (value.equals(status)) {
				return value ;
			}
		}
		return null;
	}
}
