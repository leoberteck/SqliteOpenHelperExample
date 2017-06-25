package com.example.leo.myapplication.entity;

import com.example.leo.myapplication.interfaces.IEntity;

/**
 * Created by leo on 24/06/17.
 */

public class Animal implements IEntity {
    private int id;
    private String name;
    private String specie;
    private double weight;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public static String getDDl() {
        return
        "create table Animal(" +
            "id integer primary key autoincrement," +
            "name varchar(50)," +
            "specie varchar(50)," +
            "weight numeric(5,2)" +
        ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
