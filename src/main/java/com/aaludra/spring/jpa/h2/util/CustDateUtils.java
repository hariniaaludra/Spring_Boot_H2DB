package com.aaludra.spring.jpa.h2.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CustDateUtils {
       public CustDateUtils() {
    	   
       }
       public static Timestamp getCurrentTimeStamp() {
   		java.util.Date date = new java.util.Date();
   		return new Timestamp(date.getTime());
   	}
       public static String convertstringtodate(Date input) {
    	   try {
    		   DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
    		   String date=formatter.format(input);
    		   
    		   return date;
    	   }catch(Exception e) {
    		   return null;
    	   }
       }

   	public static Date convertStringToDate(String date2) {

   		try {
   			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   			java.util.Date date = formatter.parse(date2);
   			
   			return new Date(date.getTime());
   		} catch (Exception e) {
   			return null;

   		}
   	}
   	
     public static Timestamp convertStringToTimestamp(String inputString) {
   	  try {
   		  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   	      java.util.Date date = formatter.parse(inputString);
   	      Timestamp timeStampDate = new Timestamp(date.getTime());
   		  return timeStampDate;
   	  }catch(Exception e) {
   		  
   	  
   	return null;
   	  
     }
}
}