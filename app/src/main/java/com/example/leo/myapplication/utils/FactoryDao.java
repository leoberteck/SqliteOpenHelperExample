package com.example.leo.myapplication.utils;

import android.content.Context;

import com.example.leo.myapplication.dao.AnimalDao;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalDao;
import com.example.leo.myapplication.interfaces.IDao;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * Created by leo on 24/06/17.
 */

public final class FactoryDao {

    private static HashMap<Class, Object> dependencyCache = new HashMap<>();
    private static HashMap<Class, Class> dependencyLinks = new HashMap<>();

    static {
        dependencyLinks.put(IAnimalDao.class, AnimalDao.class);
    }

    public static <T extends IDao> T getInstance(Class<T> tClass, Context context){
        T value = null;
        if(dependencyCache.containsKey(tClass)){
            value = tClass.cast(dependencyCache.get(tClass));
        }else if(dependencyLinks.containsKey(tClass)){
            Class valueClass = dependencyLinks.get(tClass);
            try {
                value = tClass.cast(valueClass.newInstance());
                value.setContext(context);
                dependencyCache.put(tClass, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static HashMap<Class, Class> getDependencyLinks() {
        return dependencyLinks;
    }
}
