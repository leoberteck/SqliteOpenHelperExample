package com.example.leo.myapplication.interfaces;

import android.content.Context;

import java.util.List;

/**
 * Created by leo on 24/06/17.
 */

public interface IDao<T extends  IEntity> extends IContextDependent{
    long add(T entity);
    int update(T entity);
    long saveOrUpdate(T entity);
    void delete(T entity);
    void delete(int id);
    T getById(int id);
    List<T> getAll();
    List<T> query(String query);
    int getCount();
}
