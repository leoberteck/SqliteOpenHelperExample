package com.example.leo.myapplication.interfaces;

/**
 * Created by Trovata on 28/06/2017.
 */

public interface IKeyValueWrapper<T> {
    void setInt     (T key, int val);
    void setInt     (T key, Integer val);
    void setLong    (T key, long val);
    void setLong    (T key, Long val);
    void setDouble  (T key, double val);
    void setDouble  (T key, Double val);
    void setFloat   (T key, float val);
    void setFloat   (T key, Float val);
    void setShort   (T key, short val);
    void setShort   (T key, Short val);
    void setBool    (T key, boolean val);
    void setBool    (T key, Boolean val);
    void setString  (T key, String val);
    int getInt      (T key, int def);
    Integer getInt  (T key);
    long getLong    (T key, long def);
    Long getLong    (T key);
    double getDouble(T key, double def);
    Double getDouble(T key);
    float getFloat  (T key, float def);
    Float getFloat  (T key);
    short getShort  (T key, short def);
    Short getShort  (T key);
    boolean getBool (T key, boolean def);
    Boolean getBool (T key);
    String getString(T key);
    boolean hasKey(T key);
}
