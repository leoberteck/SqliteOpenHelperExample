package com.example.leo.myapplication.adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leo.myapplication.R;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IObserverDataChanged;
import com.example.leo.myapplication.utils.ObserverDataChangedHelper;

import java.util.List;

/**
 * Created by Trovata on 27/06/2017.
 */

public class AnimalListAdapter extends ArrayAdapter<Animal> {
    private List<Animal> listAnimal;

    public AnimalListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Animal> objects) {
        super(context, resource, objects);
        listAnimal = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_simple_layout, parent, false);
        TextView textPrimary = rowView.findViewById(R.id.row_simple_text_primary);
        TextView textSecondary = rowView.findViewById(R.id.row_simple_text_secondary);
        textPrimary.setText(getItem(position).getName());
        textSecondary.setText(getItem(position).getWeight() + " Kilos");
        return rowView;
    }

    public Animal getById(int id){
        Animal animal = null;
        for (Animal _animal : listAnimal) {
            if(_animal.getId() == id) {
                animal = _animal;
                break;
            }
        }
        return animal;
    }

    public void removeById(int id){
        Animal animal = getById(id);
        if(animal != null) {
            remove(animal);
        }
    }

    public Integer getPositionById(int id){
        Integer position = null;
        Animal animal = getById(id);
        if(animal != null) {
            position = getPosition(animal);
        }
        return position;
    }
}
