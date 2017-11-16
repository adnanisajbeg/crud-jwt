package com.rest.example.util;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-11-16
 */
public class StringUtils {

    private StringUtils() {}

    public static boolean isNotBlank(String str) {
        return !(str == null || str.trim().isEmpty());
    }
}
