package com.example.leo.myapplication.utils;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class LongUtils {
    public static Long TryParseLong(String s, Long def)
    {
        Long result = def;
        try {
            result = Long.parseLong(s);
        } catch (Exception e){}
        finally {
            return result;
        }
    }

    public static long TryParseLong(String s) { return TryParseLong(s, (long)0); }
}
