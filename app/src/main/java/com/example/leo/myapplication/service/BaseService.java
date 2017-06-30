package com.example.leo.myapplication.service;

import android.content.Context;

import com.example.leo.myapplication.customexceptions.ValidationException;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IEntity;
import com.example.leo.myapplication.interfaces.IService;
import com.example.leo.myapplication.utils.ObserverDataChangedHelper;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Trovata on 27/06/2017.
 */

public abstract class BaseService<T extends IEntity> implements IService<T> {

    protected Context context;

    @Override
    public long add(T entity) throws ValidationException {
        validate(entity);
        long result = getTDao().add(entity);
        return result;
    }

    @Override
    public int update(T entity) throws ValidationException {
        validate(entity);
        int result = getTDao().update(entity);
        return result;
    }

    @Override
    public long saveOrUpdate(T entity) throws ValidationException {
        validate(entity);
        long result = getTDao().saveOrUpdate(entity);
        return result;
    }

    @Override
    public void delete(T entity) {
        getTDao().delete(entity);
    }

    @Override
    public void delete(int id) {
        T entity = getById(id);
        getTDao().delete(entity);
    }

    @Override
    public T getById(int id) {
        return getTDao().getById(id);
    }

    @Override
    public List<T> getAll() {
        return getTDao().getAll();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
