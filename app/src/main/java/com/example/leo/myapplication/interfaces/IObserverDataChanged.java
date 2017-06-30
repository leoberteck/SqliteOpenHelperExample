package com.example.leo.myapplication.interfaces;

import com.example.leo.myapplication.entity.Animal;

/**
 * Created by Trovata on 28/06/2017.
 */

public interface IObserverDataChanged<T extends IEntity> {
    void inserted(T entity);
    void updated(T entity);
    void deleted(T entity);
}
