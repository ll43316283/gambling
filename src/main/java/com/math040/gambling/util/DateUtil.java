package com.math040.gambling.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static Date parse(String dateStr) throws ParseException{
		return sdf.parse(dateStr);
	}
	
	public static String format(Date date)  {
		return sdf.format(date);
	}
}
