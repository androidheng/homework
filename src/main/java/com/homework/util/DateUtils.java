package com.homework.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getCurrent() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 String str = format.format(new Date()); 
		 return str;
	}
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String str = format.format(new Date()); 
		return str;
	}
}
