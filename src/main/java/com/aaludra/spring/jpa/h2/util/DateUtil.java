package com.aaludra.spring.jpa.h2.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtil {

	public DateUtil() {

	}
	public static Timestamp getCurrentTimeStamp() {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime());
	}
	public static Date convertStringToDate(String inputString) {

		try {
			System.out.println(inputString);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// you can change format of date
			java.util.Date date = formatter.parse(inputString);
			
			return new Date(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
  public static Timestamp convertStringToTimestamp(String inputString) {
	  try {
		  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	       // you can change format of date
	      java.util.Date date = formatter.parse(inputString);
	     return new Timestamp(date.getTime());
	  }catch(Exception e) {
		  
	  
	return null;
	  
  }
  }
  public static Date DateToStringExample1() {   
               Date date1 = new Date(0);
               DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
               String strDate = dateFormat.format(date1);  
               return new Date(date1.getTime());
                
       }
  
	}

