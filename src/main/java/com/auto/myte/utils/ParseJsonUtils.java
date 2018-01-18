package com.auto.myte.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auto.myte.entity.ReceiptInfo;

public class ParseJsonUtils {

	public static List<String> parseJson(JSONObject entity) {
		List<String> textList = new ArrayList<String>();
		if (entity == null) {
			return textList;
		}
        JSONArray array = entity.getJSONArray("regions");
        if (array == null) {
        	return textList;
        }
        for (Object object : array) {
        	Map jsonLines = (Map) object;
        	JSONArray lines = (JSONArray) jsonLines.get("lines");
        	for (Object words : lines) {
        		Map jsonwords = (Map) words;
            	JSONArray texts = (JSONArray) jsonwords.get("words");
            	StringBuilder sb = new StringBuilder();
            	for (Object text : texts) {
            		Map mapText = (Map) text;
            		sb.append(mapText.get("text"));
				}
            	textList.add(sb.toString());
			}
		}
        return textList;
	}
	

    public static String pareseText(List<String> list, String regex) {
    	
    	for (String string : list) {
    		String content = CommonUtils.getMatchContent(regex, string);
    		if (content != null) {
    			return content;
    		}

		}
    	return null;
    }
	

    public static String pareseTextByMeals(List<String> list, String regex) {
    	
    	String comparePirce = "0";
    	for (String string : list) {
    		String content = CommonUtils.getMatchContent(regex, string);
    		if (StringUtils.isBlank(content)) {
    			continue;
    		}
    		String rc = content.replace(",", "").replace(".", "");
    		if (StringUtils.isBlank(rc)) {
    			rc = "0";
    		}
    		if (Integer.parseInt(rc) > Integer.parseInt(comparePirce)) {
    			comparePirce = rc;
    		}

		}
    	return comparePirce;
    }
    
    public static String parserCnAmount(List<String> list) {
    	for (String string : list) {
			if (string.toUpperCase().contains("KM")) {
				continue;
			}
			if (string.indexOf(".") > 0) {
				int index = string.indexOf(".");
				String price = string.substring(0, index + 2);
				return price;
			}
		}
    	return null;
    }
    
    public static void main(String[] args) {
    	List<String> list = new ArrayList<String>();
    	list.add("令頁料又言正");
    	list.add("現・チ・ク・割引No.0722");
    	list.add("日付'17年05月18日");
    	list.add("東京タクシーヤンター");
    	list.add("車番3213");
    	list.add("運賃料金計");
    	list.add("00");
    	list.add("\\3770円");
    	list.add("\\3770円");
    	list.add("合計");
    	list.add("\\3770円");
    	String date = pareseText(list, CommonUtils.DATE_REGEX);
    	System.out.println(date);
    	String price = pareseText(list, CommonUtils.PRICE_REGEX);
    	System.out.println(price);
	}
}
