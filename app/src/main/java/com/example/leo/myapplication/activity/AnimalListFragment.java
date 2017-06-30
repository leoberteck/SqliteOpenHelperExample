package com.example.leo.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.leo.myapplication.R;
import com.example.leo.myapplication.adapters.AnimalListAdapter;
import com.example.leo.myapplication.interfaces.IEntity;
import com.example.leo.myapplication.utils.ActivityComponentLoader;
import com.example.leo.myapplication.utils.DependencyCacheHelper;

import static com.example.leo.myapplication.presenters.MainActivityMVP.*;

public class AnimalListFragment extends Fragment implements AdapterView.OnItemClickListener, IListFragment {

    private View rootView;
    private ActivityComponentLoader componentLoader;
    AnimalListAdapter animalListAdapter;

    public AnimalListFragment() {

    }

    public static AnimalListFragment newInstance() {
        return new AnimalListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_animal_list, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getComponentLoader().loadComponents(getView(), R.id.animal_list);
        componentLoader.getListView(R.id.animal_list).setOnItemClickListener(this);
        DependencyCacheHelper.getInstance(IMainPresenter.class).setListFragment(this);
        DependencyCacheHelper.getInstance(IMainPresenter.class).createAnimalAdapter(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        componentLoader.unloadComponents();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IEntity selected = animalListAdapter.getItem(i);
        if(selected != null)
        {
            DependencyCacheHelper.getInstance(IMainPresenter.class).setIdSelectedAnimal(selected.getId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public ActivityComponentLoader getComponentLoader() {
        if(componentLoader == null){
            componentLoader = new ActivityComponentLoader();
        }
        return componentLoader;
    }

    @Override
    public void onAnimalDeleted(int idAnimal) {
        animalListAdapter.removeById(idAnimal);
    }

    @Override
    public void onAdapterChanged(AnimalListAdapter adapter) {
        animalListAdapter = adapter;
        ListView animalList = componentLoader.getListView(R.id.animal_list);
        animalList.setAdapter(animalListAdapter);
        Integer position =  animalListAdapter.getPositionById(DependencyCacheHelper.getInstance(IMainPresenter.class).getIdSelectedAnimal());
        if(position != null){
            animalList.setSelection(position);
            animalList.setItemChecked(position, true);
        }
    }
}
