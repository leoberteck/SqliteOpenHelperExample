package com.example.leo.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.leo.myapplication.database.DatabaseHelper;

import org.junit.After;
import org.junit.Before;

/**
 * Created by leo on 25/06/17.
 */

public abstract class BaseTestClass {

    protected Context context;

    @Before
    public void setup(){
        context = InstrumentationRegistry.getContext();
    }

    @After
    public void clean(){
        DatabaseHelper.close(context);
        context = null;
    }
}
