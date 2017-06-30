package com.example.leo.myapplication.presenters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.ArrayAdapter;

import com.example.leo.myapplication.adapters.AnimalListAdapter;
import com.example.leo.myapplication.entity.Animal;

import java.util.List;

/**
 * Created by Trovata on 29/06/2017.
 */

public interface MainActivityMVP {

    interface IMainActivity {
        void showErrorMessage(@StringRes int message);
        void onSelectedAnimalChanged(int idAnimal);
        void onFormSaveSuccessfully();
        void onAnimalDeleted();
    }

    interface IListFragment{
        void onAnimalDeleted(int idAnimal);
        void onAdapterChanged(AnimalListAdapter adapter);
    }

    interface IFormFragment{
        String NAME_KEYSTORE = "AnimalFormFragment.Name";
        String SPECIE_KEYSTORE = "AnimalFormFragment.Specie";
        String WEIGHT_KEYSTORE = "AnimalFormFragment.Weight";
        void setFormData(String name, String specie, double weight);
    }

    interface IMainPresenter {
        void setMainActivity(IMainActivity mainActivity);
        void setListFragment(IListFragment listFragment);
        void setFormFragment(IFormFragment formFragment);
        void addOrUpdate(String name, String specie, double weight);
        void delete(int idAnimal);
        void createAnimalAdapter(Context context);
        void requestNewAnimalForm();
        void requestFormData();
        int getIdSelectedAnimal();
        void setIdSelectedAnimal(int idAnimal);
        Bundle getCache();
        void clearAnimalFormCache();
    }
}
