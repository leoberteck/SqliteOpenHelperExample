package com.example.leo.myapplication.utils;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class BoolUtils {
    public static Boolean TryParseBool(String s, Boolean def)
    {
        Boolean result = def;
        try {
            result = Boolean.parseBoolean(s);
        } catch (Exception e){}
        finally {
            return result;
        }
    }

    public static Boolean TryParseBool(String s)
    {
        return TryParseBool(s, false);
    }
}
