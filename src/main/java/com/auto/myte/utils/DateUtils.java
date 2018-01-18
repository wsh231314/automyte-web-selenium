package com.auto.myte.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期Util类
 */
public class DateUtils {
	private static String defaultDatePattern = "yyyy/MM/dd ";
	private static String dateTimePattern = "yyyy/MM/dd HH:mm:ss";
	
	public static String dateTimeFilePattern = "yyyyMMdd_HHmmss";

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return date == null ? " " : format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? " " : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		 cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	public static Date getMiddleDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		// 月，因为Calendar里的月是从0开始，所以要-1
		 cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) ));
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, 14);
		return new Date(cal.getTimeInMillis());
	}
	

	public static Date getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		// 月，因为Calendar里的月是从0开始，所以要-1
		 cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)));
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return new Date(cal.getTimeInMillis());
	}


	public static Date getDate(String year, String month, String day) throws ParseException {
		String result = year + "- " + (month.length() == 1 ? ("0 " + month) : month) + "- "
				+ (day.length() == 1 ? ("0 " + day) : day);
		return parse(result);
	}
	
	public static String getMyteEndDateTime() {
		Date today = new Date();
		Date middleDay = getMiddleDayOfMonth();
		if (today.getTime() > middleDay.getTime()) {
			return format(getLastDayOfMonth());
		}
		return format(middleDay);
	}
	
	public static String convert2String(long time) {
		Date d = new Date(time);
		System.out.println(format(d));
		return format(d, dateTimePattern);
	}
	public static void main(String[] args) {
//        Calendar now = Calendar.getInstance();  
//        System.out.println("年: " + now.get(Calendar.YEAR));  
//        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
//        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));  
//        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));  
//        System.out.println("分: " + now.get(Calendar.MINUTE));  
//        System.out.println("秒: " + now.get(Calendar.SECOND));  
//        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());  
//        System.out.println(now.getTime());  
//		// TODO Auto-generated method stub
//		Date day = getMiddleDayOfMonth();
//		String a = format(day);
//		System.out.println(getMyteEndDateTime());
		System.out.println(getLastDayOfMonth());
	}
}
