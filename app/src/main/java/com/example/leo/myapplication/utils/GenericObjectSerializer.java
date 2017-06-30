package com.example.leo.myapplication.utils;

import com.example.leo.myapplication.interfaces.IKeyValueWrapper;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Trovata on 28/06/2017.
 */

public class GenericObjectSerializer<T> {

    private static HashMap<Class, GenericObjectSerializer> instanceCache = new HashMap<>();
    public static <T> GenericObjectSerializer<T> getInstance(Class<T> tClass){
        GenericObjectSerializer<T> instance;
        if(instanceCache.containsKey(tClass)){
            instance = new GenericObjectSerializer<>(tClass);
        }else{
            instance = new GenericObjectSerializer<>(tClass);
            instanceCache.put(tClass, instance);
        }
        return instance;
    }

    Class<T> tClass;
    Field[] fieldCache;

    public GenericObjectSerializer(Class<T> tClass){
        this.tClass = tClass;
        fieldCache = tClass.getDeclaredFields();
        for (Field field : fieldCache){
            field.setAccessible(true);
        }
    }

    public T getFromWrapper(IKeyValueWrapper<String> keyValueWrapper) throws InstantiationException, IllegalAccessException {
        return getFromWrapper(keyValueWrapper, "");
    }

    public T getFromWrapper(IKeyValueWrapper<String> keyValueWrapper, String keyPrefix) throws IllegalAccessException, InstantiationException {
        T entity = tClass.newInstance();

        for (Field field : fieldCache){
            Class fType = field.getType();
            if (fType.equals(String.class)) {
                field.set(entity, keyValueWrapper.getString(getFieldKey(field, keyPrefix)));
            } else if (fType.equals(int.class)) {
                field.setInt(entity, keyValueWrapper.getInt(getFieldKey(field, keyPrefix), 0));
            } else if (fType.equals(Integer.class)) {
                field.setInt(entity, keyValueWrapper.getInt(getFieldKey(field, keyPrefix)));
            } else if (fType.equals(double.class)) {
                field.setDouble(entity, keyValueWrapper.getDouble(getFieldKey(field, keyPrefix), (double)0));
            } else if (fType.equals(Double.class)) {
                field.setDouble(entity, keyValueWrapper.getDouble(getFieldKey(field, keyPrefix)));
            } else if (fType.equals(float.class)) {
                field.setFloat(entity, keyValueWrapper.getFloat(getFieldKey(field, keyPrefix), (float)0));
            } else if (fType.equals(Double.class)) {
                field.setFloat(entity, keyValueWrapper.getFloat(getFieldKey(field, keyPrefix)));
            } else if (fType.equals(long.class)) {
                field.setLong(entity, keyValueWrapper.getLong(getFieldKey(field, keyPrefix), (long)0));
            } else if (fType.equals(Long.class)) {
                field.setLong(entity, keyValueWrapper.getLong(getFieldKey(field, keyPrefix)));
            } else if (fType.equals(short.class)) {
                field.setShort(entity, keyValueWrapper.getShort(getFieldKey(field, keyPrefix), (short)0));
            } else if (fType.equals(Short.class)) {
                field.setShort(entity, keyValueWrapper.getShort(getFieldKey(field, keyPrefix)));
            } else if (fType.equals(boolean.class)) {
                field.setBoolean(entity, keyValueWrapper.getBool(getFieldKey(field, keyPrefix), false));
            } else if (fType.equals(Boolean.class)) {
                field.setBoolean(entity, keyValueWrapper.getBool(getFieldKey(field, keyPrefix)));
            }
        }
        return entity;
    }

    public void setIntoWrapper(T obj, IKeyValueWrapper<String> keyValueWrapper) throws IllegalAccessException {
        setIntoWrapper(obj, keyValueWrapper, "");
    }

    public void setIntoWrapper(T obj, IKeyValueWrapper<String> keyValueWrapper, String keyPrefix) throws IllegalAccessException {
        for (Field field : fieldCache){
            Class fType = field.getType();
            if (fType.equals(String.class)) {
                keyValueWrapper.setString(getFieldKey(field, keyPrefix), (String)field.get(obj));
            } else if (fType.equals(int.class) || fType.equals(Integer.class)) {
                keyValueWrapper.setInt(getFieldKey(field, keyPrefix), field.getInt(obj));
            } else if (fType.equals(double.class) || fType.equals(Double.class)) {
                keyValueWrapper.setDouble(getFieldKey(field, keyPrefix), field.getDouble(obj));
            } else if (fType.equals(float.class) || fType.equals(Double.class)) {
                keyValueWrapper.setFloat(getFieldKey(field, keyPrefix), field.getFloat(obj));
            } else if (fType.equals(long.class) || fType.equals(Long.class)) {
                keyValueWrapper.setLong(getFieldKey(field, keyPrefix), field.getLong(obj));
            } else if (fType.equals(short.class) || fType.equals(Short.class)) {
                keyValueWrapper.setShort(getFieldKey(field, keyPrefix), field.getShort(obj));
            } else if (fType.equals(boolean.class) || fType.equals(Boolean.class)) {
                keyValueWrapper.setBool(getFieldKey(field, keyPrefix), field.getBoolean(obj));
            }
        }
    }

    private String getFieldKey(Field field, String keyPrefix){
        return keyPrefix + "_" + field.getName();
    }
}
