package com.example.leo.myapplication.utils;

import android.app.Activity;
import android.content.Context;

import com.example.leo.myapplication.interfaces.IKeyValueWrapper;

import java.util.HashMap;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class ActivityVariableCache implements IKeyValueWrapper<String> {

    private static HashMap<String, ActivityVariableCache> instanceCache = new HashMap<>();
    public static ActivityVariableCache getInstance(Activity activity){
        ActivityVariableCache cache = null;
        String key = activity.getClass().getSimpleName();
        if(instanceCache.containsKey(key)){
            cache = instanceCache.get(key);
        }else {
            cache = new ActivityVariableCache(activity);
            instanceCache.put(key, cache);
        }
        return cache;
    }

    public static void clearCache(Class activityClass){
        instanceCache.remove(activityClass.getSimpleName());
    }

    private String masterKey;
    private PreferencesWrapper preferencesWrapper;

    private ActivityVariableCache(Activity activity){
        preferencesWrapper = new PreferencesWrapper(activity.getPreferences(Context.MODE_PRIVATE));
        this.masterKey = activity.getClass().getSimpleName();
    }

    public <T> void set(T obj, Class<T> tClass, String key) {
        GenericObjectSerializer<T> genericObjectSerializer = new GenericObjectSerializer<>(tClass);
        try {
            genericObjectSerializer.setIntoWrapper(obj, preferencesWrapper, masterKey + "_" + key);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> T get(Class<T> tClass, String key) {
        GenericObjectSerializer<T> genericObjectSerializer = new GenericObjectSerializer<>(tClass);
        T result = null;
        try {
            result = genericObjectSerializer.getFromWrapper(preferencesWrapper, masterKey + "_" + key);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void setInt(String key, int val) {
        preferencesWrapper.setInt(key, val);
    }

    @Override
    public void setInt(String key, Integer val) {
        preferencesWrapper.setInt(key, val);
    }

    @Override
    public void setLong(String key, long val) {
        preferencesWrapper.setLong(key, val);
    }

    @Override
    public void setLong(String key, Long val) {
        preferencesWrapper.setLong(key, val);
    }

    @Override
    public void setDouble(String key, double val) {
        preferencesWrapper.setDouble(key, val);
    }

    @Override
    public void setDouble(String key, Double val) {
        preferencesWrapper.setDouble(key, val);
    }

    @Override
    public void setFloat(String key, float val) {
        preferencesWrapper.setFloat(key, val);
    }

    @Override
    public void setFloat(String key, Float val) {
        preferencesWrapper.setFloat(key, val);
    }

    @Override
    public void setShort(String key, short val) {
        preferencesWrapper.setShort(key, val);
    }

    @Override
    public void setShort(String key, Short val) {
        preferencesWrapper.setShort(key, val);
    }

    @Override
    public void setBool(String key, boolean val) {
        preferencesWrapper.setBool(key, val);
    }

    @Override
    public void setBool(String key, Boolean val) {
        preferencesWrapper.setBool(key, val);
    }

    @Override
    public void setString(String key, String val) {
        preferencesWrapper.setString(key, val);
    }

    @Override
    public int getInt(String key, int def) {
        return preferencesWrapper.getInt(key, def);
    }

    @Override
    public Integer getInt(String key) {
        return preferencesWrapper.getInt(key);
    }

    @Override
    public long getLong(String key, long def) {
        return preferencesWrapper.getLong(key, def);
    }

    @Override
    public Long getLong(String key) {
        return preferencesWrapper.getLong(key);
    }

    @Override
    public double getDouble(String key, double def) {
        return preferencesWrapper.getDouble(key, def);
    }

    @Override
    public Double getDouble(String key) {
        return preferencesWrapper.getDouble(key);
    }

    @Override
    public float getFloat(String key, float def) {
        return preferencesWrapper.getFloat(key, def);
    }

    @Override
    public Float getFloat(String key) {
        return preferencesWrapper.getFloat(key);
    }

    @Override
    public short getShort(String key, short def) {
        return preferencesWrapper.getShort(key, def);
    }

    @Override
    public Short getShort(String key) {
        return preferencesWrapper.getShort(key);
    }

    @Override
    public boolean getBool(String key, boolean def) {
        return preferencesWrapper.getBool(key, def);
    }

    @Override
    public Boolean getBool(String key) {
        return preferencesWrapper.getBool(key);
    }

    @Override
    public String getString(String key) {
        return preferencesWrapper.getString(key);
    }

    @Override
    public boolean hasKey(String key) {
        return preferencesWrapper.hasKey(key);
    }
}
