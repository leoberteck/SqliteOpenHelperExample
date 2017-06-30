package com.example.leo.myapplication.interfaces;

import android.view.View;

/**
 * Created by Trovata on 28/06/2017.
 */

public interface IActivityComponentLoader {
    void loadComponents(View view, int... ids);
    void unloadComponents();
}
