package org.ukhome.jsonviewer.util;

public class StringUtils {

	public static boolean isEmpty(String str) {
		return null == str || str.trim().equals("") ? true : false;
	}
	
}
