package com.ain.engine.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

	public static String currentDateTime(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);	
		return df.format(new Date());
	}

	public static String currentDateTime() {
		String format = "yyyy-MM-dd HH:mm:ss";
		
		return currentDateTime(format);
	}

	public static String currentDate() {
		String format = "yyyy-MM-dd";
		
		return currentDateTime(format);
	}

	public static String getYMD(int before) {
		String format = "yyyyMMdd";
		int amount = before * -1;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, amount);
		
		SimpleDateFormat df = new SimpleDateFormat(format);
		
		return df.format(cal.getTime());
	}
}
