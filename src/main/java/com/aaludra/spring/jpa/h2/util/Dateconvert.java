package com.aaludra.spring.jpa.h2.util;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Dateconvert {
	
	public Dateconvert() {
	}
	
	public static String convertStringToDate(Date inputString) {

		try {
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			String date = formatter.format(inputString);
			
			return date;
		} catch (Exception e) {
			return null;
}
}

	}