package com.example.leo.myapplication.dao;

import android.content.Context;

import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalDao;

/**
 * Created by leo on 24/06/17.
 */

public class AnimalDao extends BaseDao<Animal> implements IAnimalDao {
    public AnimalDao() {
        super(Animal.class);
    }
}
