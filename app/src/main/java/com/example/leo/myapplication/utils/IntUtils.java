package com.example.leo.myapplication.utils;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class IntUtils {

    public static Integer TryParseInt(String s, Integer def)
    {
        Integer result = def;
        try {
            result = Integer.parseInt(s);
        } catch (Exception e){}
        finally {
            return result;
        }
    }

    public static int TryParseInt(String s)
    {
        return TryParseInt(s, 0);
    }
}
