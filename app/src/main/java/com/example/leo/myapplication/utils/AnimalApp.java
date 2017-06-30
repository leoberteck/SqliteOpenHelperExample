package com.example.leo.myapplication.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Trovata on 27/06/2017.
 */

public class AnimalApp extends Application {
    private static AnimalApp instance;

    public static AnimalApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    private static boolean isStarting = true;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static boolean isStarting() {
        return isStarting;
    }

    public static void setIsStarting(boolean isStarting) {
        AnimalApp.isStarting = isStarting;
    }
}
