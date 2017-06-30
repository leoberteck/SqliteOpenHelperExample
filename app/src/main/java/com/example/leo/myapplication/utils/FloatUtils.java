package com.example.leo.myapplication.utils;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class FloatUtils {
    public static Float TryParseFloat(String s, Float def)
    {
        Float result = def;
        try {
            result = Float.parseFloat(s);
        } catch (Exception e){}
        finally {
            return result;
        }
    }

    public static float TryParseInt(String s)
    {
        return TryParseFloat(s, 0f);
    }
}
