package com.example.leo.myapplication.interfaces;

import android.content.Context;

import java.util.List;

/**
 * Created by leo on 24/06/17.
 */

public interface IDao<T extends  IEntity> {
    long add(T entity);
    int update(T entity);
    void delete(T entity);
    void delete(int id);
    T getById(int id) throws InstantiationException;
    List<T> getAll() throws InstantiationException;
    List<T> query(String query) throws InstantiationException;
    int getCount();
    void setContext(Context context);
}
