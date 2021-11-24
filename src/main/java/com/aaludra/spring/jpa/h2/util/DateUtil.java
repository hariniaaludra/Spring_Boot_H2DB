package com.aaludra.spring.jpa.h2.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
	public static Timestamp getCurrentTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());

	}
}
