package com.example.leo.myapplication.utils;

/**
 * Created by Trovata on 27/06/2017.
 */

public final class StringUtils {
    public static boolean IsNotNullOrEmpty(String string){
        return string != null && string.length() > 0;
    }

    public static boolean isNullOrEmpty(String string){
        return !IsNotNullOrEmpty(string);
    }
}
