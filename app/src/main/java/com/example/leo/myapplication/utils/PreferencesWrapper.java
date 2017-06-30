package com.example.leo.myapplication.utils;

import android.content.SharedPreferences;

import com.example.leo.myapplication.interfaces.IKeyValueWrapper;

/**
 * Created by Trovata on 28/06/2017.
 */

public final class PreferencesWrapper implements IKeyValueWrapper<String> {

    SharedPreferences preferences;

    public PreferencesWrapper(SharedPreferences preferences){
        this.preferences = preferences;
    }

    @Override
    public void setInt(String key, int val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, val);
        editor.apply();
    }

    @Override
    public void setInt(String key, Integer val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setLong(String key, long val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, val);
        editor.apply();
    }

    @Override
    public void setLong(String key, Long val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setDouble(String key, double val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setDouble(String key, Double val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setFloat(String key, float val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, val);
        editor.apply();
    }

    @Override
    public void setFloat(String key, Float val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setShort(String key, short val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setShort(String key, Short val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setBool(String key, boolean val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, val);
        editor.apply();
    }

    @Override
    public void setBool(String key, Boolean val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, String.valueOf(val));
        editor.apply();
    }

    @Override
    public void setString(String key, String val) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, val);
        editor.apply();
    }

    @Override
    public int getInt(String key, int def) {
        return preferences.getInt(key, def);
    }

    @Override
    public Integer getInt(String key) {
        return IntUtils.TryParseInt(preferences.getString(key, null), null);
    }

    @Override
    public long getLong(String key, long def) {
        return preferences.getLong(key, def);
    }

    @Override
    public Long getLong(String key) {
        return LongUtils.TryParseLong(preferences.getString(key, null), null);
    }

    @Override
    public double getDouble(String key, double def) {
        return DoubleUtils.TryParseDouble(preferences.getString(key, null));
    }

    @Override
    public Double getDouble(String key) {
        return DoubleUtils.TryParseDouble(preferences.getString(key, null), null);
    }

    @Override
    public float getFloat(String key, float def) {
        return preferences.getFloat(key, def);
    }

    @Override
    public Float getFloat(String key) {
        return FloatUtils.TryParseFloat(preferences.getString(key, null),null);
    }

    @Override
    public short getShort(String key, short def) {
        return ShortUtils.TryParseShort(preferences.getString(key, null));
    }

    @Override
    public Short getShort(String key) {
        return ShortUtils.TryParseShort(preferences.getString(key, null), null);
    }

    @Override
    public boolean getBool(String key, boolean def) {
        return BoolUtils.TryParseBool(preferences.getString(key, null));
    }

    @Override
    public Boolean getBool(String key) {
        return BoolUtils.TryParseBool(preferences.getString(key, null), null);
    }

    @Override
    public String getString(String key) {
        return preferences.getString(key, null);
    }

    @Override
    public boolean hasKey(String key) {
        return preferences.contains(key);
    }
}
