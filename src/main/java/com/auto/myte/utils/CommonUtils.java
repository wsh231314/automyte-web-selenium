package com.auto.myte.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.springframework.util.StringUtils;

public class CommonUtils {
	public static String PRICE_REGEX = "((([1-9]{1}\\d*)|([0]{1}))(((.[0-9]{3})*(\\d*){1,2}))*円$)";
	public static String PRICE_REGEX1 = "((([1-9]{1}\\d*)|([0]{1}))(((.[0-9]{3})*(\\d*){1,2})))";
	public static String DATE_REGEX = "([0-9]{2})年([0-9]{2})月([0-9]{2})日|([0-9]{4})年([0-9]{2})月([0-9]{2})日|([0-9]{4})-([0-9]{2})-([0-9]{2})";
	public static String TAXI_REGEX = "車番|タクシー|運賃|乗車|交通|車両|自動車|Β-Τ";

	public static String getCurrentDate() {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYY/MM/dd");
		return ft.format(dNow);
	}

	public static boolean isProductNoNotEmpty(String productNo) {
		if (StringUtils.isEmpty(productNo)) {
			return false;
		}
		return true;
	}

	public static boolean isProductNo(String productNo) {

		String s = "[A-Z]+-[A-Z]+|[A-Z]+[0-9]+|[A-Z]+";
		Pattern p = Pattern.compile(s);
		Matcher matcher = p.matcher(productNo);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean containsProductNo(String productName, String productNo) {
		return productName.toUpperCase().replaceAll("-", "").contains(productNo.toUpperCase().replaceAll("-", ""));
	}

	public static String getAmount(String price) {
		return price.replace("円", "").replaceAll(",", "").replace(".", "");
	}

	public static boolean isCsv(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}
		int len = fileName.indexOf(".");

		String filetype = fileName.substring(len + 1);

		if ("csv".equals(filetype)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断文件是否图片格式
	 * 
	 * @param str
	 * @return boolean regDemo.java
	 * @author: ge.tao
	 */
	public static boolean isImage(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}
		
		String str = fileName.substring(fileName.lastIndexOf(".") + 1);
		String regStr = "[Gg][Ii][Ff]|[Jj][Pp][Gg]|[Bb][Mm][Pp]|[Jj][Pp][Ee][Gg]|[Pp][Nn][Gg]";
		PatternCompiler compiler = new Perl5Compiler();
		org.apache.oro.text.regex.Pattern pattern = null;
		try {
			pattern = compiler.compile(regStr);
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		PatternMatcher pm = new Perl5Matcher();
		if (pm.matches(str, pattern)) {
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * @param regex
	 * @param input
	 * @return
	 */
	public static String getMatchContent(String regex, String input) {
		String matchContent = null;
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(input);
		if (matcher.find()) {
			matchContent = matcher.group();
		}
		return matchContent;
	}
	

	/**
	 * @param regex
	 * @param input
	 * @return
	 */
	public static boolean isMatch(String regex, String input) {
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(input);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	public static String getBasePath(HttpServletRequest request) {
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		return basePath;
	}
	
	public static String convertYyyyMmdd(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		String yyyyMmdd = "";
		String rDate = date.replace("年", "/").replace("月", "/").replace("日", "").replace(" ", "").replace("　", "");
		
		if (rDate.length() == 8) {
			yyyyMmdd = "20" + rDate;
		} else if (rDate.length() == 10) {
			yyyyMmdd = rDate;
		}
		
		return yyyyMmdd;
	}


	public static void main(String[] args) {
		CommonUtils c = new CommonUtils();
		boolean b = c.containsProductNo("エレコム　USB2.0延長ケーブルAタイプ　5m　USB2EXA50", "USB2-EXA50");
		System.out.println(b);
	}
}
