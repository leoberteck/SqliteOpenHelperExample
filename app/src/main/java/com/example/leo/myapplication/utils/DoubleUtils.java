package com.example.leo.myapplication.utils;

/**
 * Created by Trovata on 27/06/2017.
 */

public final class DoubleUtils {
    public static Double TryParseDouble(String s, Double def)
    {
        Double result = def;
        try {
            result = Double.parseDouble(s);
        } catch (Exception e){}
        finally {
            return result;
        }
    }

    public static Double TryParseDouble(String s)
    {
        return TryParseDouble(s, 0d);
    }
}
