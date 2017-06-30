package com.example.leo.myapplication.utils;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class HashMapUtils {
    public static Object getOrDefault(HashMap map, Object id, Object def){
        Object result = def;
        if(map.containsKey(id)){
            result = map.get(id);
        }
        return result;
    }
}
