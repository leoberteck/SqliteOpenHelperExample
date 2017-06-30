package com.example.leo.myapplication.utils;

import android.content.Context;
import android.util.Log;

import com.example.leo.myapplication.dao.AnimalDao;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalDao;
import com.example.leo.myapplication.interfaces.IAnimalService;
import com.example.leo.myapplication.interfaces.IContextDependent;
import com.example.leo.myapplication.interfaces.IDao;
import com.example.leo.myapplication.presenters.MainActivityMVP;
import com.example.leo.myapplication.presenters.MainPresenter;
import com.example.leo.myapplication.service.AnimalService;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * Created by leo on 24/06/17.
 */

public final class DependencyCacheHelper {

    private static final String TAG = DependencyCacheHelper.class.getSimpleName();

    private static HashMap<Class, Object> dependencyCache = new HashMap<>();
    private static HashMap<Class, Class> dependencyLinks = new HashMap<>();

    static {
        dependencyLinks.put(IAnimalDao.class, AnimalDao.class);
        dependencyLinks.put(IAnimalService.class, AnimalService.class);
        dependencyLinks.put(MainActivityMVP.IMainPresenter.class, MainPresenter.class);
    }

    public static <T> T getInstance(Class<T> tClass){
        T value = null;
        if(dependencyCache.containsKey(tClass)){
            value = tClass.cast(dependencyCache.get(tClass));
        }else if(dependencyLinks.containsKey(tClass)){
            Class valueClass = dependencyLinks.get(tClass);
            try {
                value = tClass.cast(valueClass.newInstance());
                if(value instanceof IContextDependent){
                    ((IContextDependent)value).setContext(AnimalApp.getContext());
                }
                dependencyCache.put(tClass, value);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return value;
    }

    public static <T> void disposeInstance(Class<T> tClass){
        dependencyCache.remove(tClass);
    }

    public static HashMap<Class, Class> getDependencyLinks() {
        return dependencyLinks;
    }
}
