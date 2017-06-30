package com.example.leo.myapplication.utils;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class ShortUtils {
    public static Short TryParseShort(String s, Short def)
    {
        Short result = def;
        try {
            result = Short.parseShort(s);
        } catch (Exception e){}
        return result;
    }

    public static short TryParseShort(String s) { return TryParseShort(s, (short)0); }
}
