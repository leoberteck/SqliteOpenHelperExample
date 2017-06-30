package com.example.leo.myapplication.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.leo.myapplication.adapters.AnimalListAdapter;
import com.example.leo.myapplication.customexceptions.ValidationException;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalService;
import com.example.leo.myapplication.utils.DependencyCacheHelper;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.example.leo.myapplication.presenters.MainActivityMVP.*;

/**
 * Created by Trovata on 29/06/2017.
 */

public class MainPresenter implements IMainPresenter {

    private final String TAG = getClass().getSimpleName();

    private WeakReference<MainActivityMVP.IMainActivity> mainActivityWeakReference;
    private WeakReference<MainActivityMVP.IListFragment> listFragmentWeakReference;
    private WeakReference<MainActivityMVP.IFormFragment> formFragmentWeakReference;

    private int idSelectedAnimal;
    private Bundle cache = new Bundle();

    @Override
    public void setMainActivity(IMainActivity mainActivity) {
        mainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Override
    public void setListFragment(IListFragment listFragment) {
        listFragmentWeakReference = new WeakReference<>(listFragment);
    }

    @Override
    public void setFormFragment(IFormFragment formFragment) {
        formFragmentWeakReference = new WeakReference<>(formFragment);
    }

    @Override
    public void addOrUpdate(String name, String specie, double weight) {
        new AsyncTask<Object, Void, ValidationException>() {
            @Override
            protected ValidationException doInBackground(Object... args) {
                final Animal animal = new Animal();
                animal.setId((int) args[0]);
                animal.setName((String) args[1]);
                animal.setSpecie((String) args[2]);
                animal.setWeight((double)args[3]);
                try {
                    DependencyCacheHelper.getInstance(IAnimalService.class).saveOrUpdate(animal);
                    return null;
                } catch (ValidationException e) {
                    Log.e(TAG, e.getMessage());
                    return e;
                }
            }

            @Override
            protected void onPostExecute(ValidationException e) {
                super.onPostExecute(e);
                if(e != null){
                    if(mainActivityWeakReference.get() != null)
                        mainActivityWeakReference.get().showErrorMessage(e.getLocalizedMessageId());
                } else {
                    setIdSelectedAnimal(0);
                    if(mainActivityWeakReference.get() != null)
                        mainActivityWeakReference.get().onFormSaveSuccessfully();
                }
            }
        }.execute(getIdSelectedAnimal(), name, specie, weight);
    }

    @Override
    public void delete(int idAnimal) {
        new AsyncTask<Object, Void, Integer>() {
            @Override
            protected Integer doInBackground(Object... args) {
                int id = (int)args[0];
                DependencyCacheHelper.getInstance(IAnimalService.class).delete(id);
                return id;
            }

            @Override
            protected void onPostExecute(Integer id) {
                if(id == getIdSelectedAnimal()){
                    setIdSelectedAnimal(0);
                    if(listFragmentWeakReference.get() != null)
                        listFragmentWeakReference.get().onAnimalDeleted(id);
                    if(mainActivityWeakReference.get() != null)
                        mainActivityWeakReference.get().onAnimalDeleted();
                }
            }
        }.execute(idAnimal);
    }

    @Override
    public void createAnimalAdapter(final Context context) {
        new AsyncTask<Object, Void, AnimalListAdapter>() {
            @Override
            protected AnimalListAdapter doInBackground(Object... args) {
                Context _context = (Context)args[0];
                List<Animal> listAnimal = DependencyCacheHelper.getInstance(IAnimalService.class).getAll();
                AnimalListAdapter adapter = new AnimalListAdapter(_context, 0, listAnimal);
                return adapter;
            }

            @Override
            protected void onPostExecute(AnimalListAdapter animalListAdapter) {
                super.onPostExecute(animalListAdapter);
                if(listFragmentWeakReference.get() != null)
                    listFragmentWeakReference.get().onAdapterChanged(animalListAdapter);
            }
        }.execute(context);
    }

    @Override
    public void requestNewAnimalForm() {
        setIdSelectedAnimal(0);
        if(formFragmentWeakReference != null && formFragmentWeakReference.get() != null)
            formFragmentWeakReference.get().setFormData(null, null, 0);
    }

    @Override
    public void requestFormData() {
        new AsyncTask<Object, Void, Animal>() {
            @Override
            protected Animal doInBackground(Object... args) {
                int id = (int)args[0];
                Animal animal = new Animal();

                if(id > 0 && !hasAnimalFormCache()){
                    animal = DependencyCacheHelper.getInstance(IAnimalService.class).getById(id);
                }else if(hasAnimalFormCache()){
                    animal.setName(getCache().getString(IFormFragment.NAME_KEYSTORE));
                    animal.setSpecie(getCache().getString(IFormFragment.SPECIE_KEYSTORE));
                    animal.setWeight(getCache().getDouble(IFormFragment.WEIGHT_KEYSTORE));
                }
                return animal;
            }

            @Override
            protected void onPostExecute(Animal animal) {
                super.onPostExecute(animal);
                if(formFragmentWeakReference.get() != null)
                    formFragmentWeakReference.get().setFormData(animal.getName(), animal.getSpecie(), animal.getWeight());
            }
        }.execute(getIdSelectedAnimal());
    }

    @Override
    public int getIdSelectedAnimal() {
        return idSelectedAnimal;
    }

    @Override
    public void setIdSelectedAnimal(int idSelectedAnimal) {
        this.idSelectedAnimal = idSelectedAnimal;
        clearAnimalFormCache();
        if(mainActivityWeakReference.get() != null)
            mainActivityWeakReference.get().onSelectedAnimalChanged(idSelectedAnimal);
    }

    @Override
    public Bundle getCache() {
        return cache;
    }

    @Override
    public void clearAnimalFormCache(){
        getCache().remove(IFormFragment.NAME_KEYSTORE);
        getCache().remove(IFormFragment.SPECIE_KEYSTORE);
        getCache().remove(IFormFragment.WEIGHT_KEYSTORE);
    }

    private boolean hasAnimalFormCache() {
        return getCache().containsKey(IFormFragment.NAME_KEYSTORE) && getCache().containsKey(IFormFragment.SPECIE_KEYSTORE) && getCache().containsKey(IFormFragment.WEIGHT_KEYSTORE);
    }
}
