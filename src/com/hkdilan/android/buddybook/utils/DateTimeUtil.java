package com.hkdilan.android.buddybook.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtil {
	
	public final static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String DIARY_DATE_TIME_FORMAT = "EEE, MMMMM dd";
	
	public static SimpleDateFormat getSimpleDateFormat(String dateFormatPattern){
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern);
		sdf.setTimeZone(TimeZone.getDefault());
		return sdf;
	}

	public static String getDefaultDateString(Calendar calendar) {
		return getSimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(calendar.getTime());
	}
	
	public static Calendar getDefaultCalendarFromString(String date){
		try {
			final Date d = getSimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).parse(date);
			final Calendar cal = getNow();
			cal.setTime(d);
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Calendar getNow(){
		return Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
	}
	
	public static String getDateString(Calendar cal, String pattern){
		return getSimpleDateFormat(pattern).format(cal.getTime());
	}
}
