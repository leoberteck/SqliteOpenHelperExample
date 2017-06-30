package com.example.leo.myapplication.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.leo.myapplication.interfaces.IActivityComponentLoader;

import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Created by Trovata on 28/06/2017.
 */

public class ActivityComponentLoader implements IActivityComponentLoader {

    private HashMap<Integer, Object> loadedComponents = new HashMap<>();
    private boolean isLoaded;

    @Override
    public void loadComponents(View view, int... ids) {
        for (int id : ids){
            loadedComponents.put(id, view.findViewById(id));
        }
        setLoaded(true);
    }

    @Override
    public void unloadComponents() {
        loadedComponents.clear();
        setLoaded(false);
    }

    public EditText getEditText(int id){
        return get(id, EditText.class);
    }

    public ListView getListView(int id){
        return get(id, ListView.class);
    }

    public BottomNavigationView getBottomNavigationView(int id) { return get(id, BottomNavigationView.class);}

    public <T> T get(int id, Class<T> tClass){
        return tClass.cast(HashMapUtils.getOrDefault(loadedComponents, id, null));
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }
}
