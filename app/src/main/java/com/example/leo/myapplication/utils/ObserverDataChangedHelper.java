package com.example.leo.myapplication.utils;

import com.example.leo.myapplication.interfaces.IEntity;
import com.example.leo.myapplication.interfaces.IObserverDataChanged;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class ObserverDataChangedHelper {

    private static HashMap<Class, List<IObserverDataChanged>> instanceCache = new HashMap<>();

    public static <T extends IEntity> void register(IObserverDataChanged<T> instance){
        Class<T> tClass = (Class<T>)((ParameterizedType)instance.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        instanceCache.put(tClass, new ArrayList<IObserverDataChanged>());
        instanceCache.get(tClass).add(instance);
    }

    public static <T extends IEntity> void unRegister(IObserverDataChanged<T> instance){
        Class<T> tClass = (Class<T>)((ParameterizedType)instance.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        instanceCache.remove(tClass);
    }

    public static <T extends IEntity> void notifyInsert(T entity){
        List<IObserverDataChanged> listObserver = instanceCache.get(entity.getClass());
        if(listObserver != null && listObserver.size() > 0){
            for(IObserverDataChanged observer : listObserver){
                observer.inserted(entity);
            }
        }

    }

    public static <T extends IEntity> void notifyUpdate(T entity){
        List<IObserverDataChanged> listObserver = instanceCache.get(entity.getClass());
        if(listObserver != null && listObserver.size() > 0){
            for(IObserverDataChanged observer : listObserver){
                observer.updated(entity);
            }
        }
    }

    public static <T extends IEntity> void notifyDelete(T entity){
        List<IObserverDataChanged> listObserver = instanceCache.get(entity.getClass());
        if(listObserver != null && listObserver.size() > 0){
            for(IObserverDataChanged observer : listObserver){
                observer.deleted(entity);
            }
        }
    }
}
