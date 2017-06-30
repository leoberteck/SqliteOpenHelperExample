package com.example.leo.myapplication.interfaces;

import android.content.Context;

import com.example.leo.myapplication.customexceptions.ValidationException;

import java.util.List;

/**
 * Created by Trovata on 27/06/2017.
 */

public interface IService<T extends IEntity> extends IContextDependent{
    long add(T entity) throws ValidationException;
    int update(T entity) throws ValidationException;
    long saveOrUpdate(T entity) throws ValidationException;
    void delete(T entity);
    void delete(int id);
    T getById(int id);
    List<T> getAll();
    void validate(T entity) throws ValidationException;
    IDao<T> getTDao();
}
