package com.ta.netredcat.utils;

public class NumberUtils {
    public static boolean isPhone(String content) {
        return content != null && !content.isEmpty() && content.startsWith("1") && content.length() == 11;
    }

    public static boolean isValidPassword(String content) {
        return content != null && !content.isEmpty() && content.length() >= 6 && content.length() <= 20;
    }
}
