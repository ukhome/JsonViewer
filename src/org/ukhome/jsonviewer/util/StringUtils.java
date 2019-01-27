package org.ukhome.jsonviewer.util;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return null == str || str.trim().equals("") ? true : false;
    }

    public static boolean isJson(String str) {
        if (isEmpty(str)) return false;
        char firstChar = str.trim().charAt(0);
        return firstChar == '[' || firstChar == '{';
    }

}
