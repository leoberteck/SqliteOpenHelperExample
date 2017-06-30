package com.example.leo.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.leo.myapplication.R;
import com.example.leo.myapplication.customexceptions.ValidationException;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalService;
import com.example.leo.myapplication.presenters.MainActivityMVP;
import com.example.leo.myapplication.utils.ActivityComponentLoader;
import com.example.leo.myapplication.utils.ActivityVariableCache;
import com.example.leo.myapplication.utils.DoubleUtils;
import com.example.leo.myapplication.utils.DependencyCacheHelper;
import com.example.leo.myapplication.utils.PreferencesWrapper;

import static com.example.leo.myapplication.presenters.MainActivityMVP.*;

public class AnimalFormFragment extends Fragment implements View.OnClickListener, IFormFragment {

    private ActivityComponentLoader componentLoader;

    public AnimalFormFragment() {

    }

    public static AnimalFormFragment newInstance() { return new AnimalFormFragment(); }

    @Override
    public void onClick(View view) {
        String name = getComponentLoader().getEditText(R.id.tbx_name).getText().toString();
        String specie = getComponentLoader().getEditText(R.id.tbx_specie).getText().toString();
        double weight = DoubleUtils.TryParseDouble(getComponentLoader().getEditText(R.id.tbx_weight).getText().toString());
        DependencyCacheHelper.getInstance(IMainPresenter.class).addOrUpdate(name, specie, weight);
    }

    @Override
    public void setFormData(String name, String specie, double weight) {
        if(getComponentLoader().isLoaded())
        {
            getComponentLoader().getEditText(R.id.tbx_name).setText(name);
            getComponentLoader().getEditText(R.id.tbx_specie).setText(specie);
            getComponentLoader().getEditText(R.id.tbx_weight).setText(String.valueOf(weight));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animal_form, container, false);
        Button btnSave = rootView.findViewById(R.id.btn_confirm_add);
        btnSave.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getComponentLoader().loadComponents(getView()
        , R.id.tbx_name
        , R.id.tbx_specie
        , R.id.tbx_weight);
        IMainPresenter presenter = DependencyCacheHelper.getInstance(IMainPresenter.class);
        presenter.setFormFragment(this);
        presenter.requestFormData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Bundle cache = DependencyCacheHelper.getInstance(IMainPresenter.class).getCache();
        String name = getComponentLoader().getEditText(R.id.tbx_name).getText().toString();
        String specie = getComponentLoader().getEditText(R.id.tbx_specie).getText().toString();
        double weight = DoubleUtils.TryParseDouble(getComponentLoader().getEditText(R.id.tbx_weight).getText().toString());
        cache.putString(NAME_KEYSTORE, name);
        cache.putString(SPECIE_KEYSTORE, specie);
        cache.putDouble(WEIGHT_KEYSTORE, weight);
        getComponentLoader().unloadComponents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityVariableCache.clearCache(getActivity().getClass());
    }

    public ActivityComponentLoader getComponentLoader() {
        if(componentLoader == null){
            setComponentLoader(new ActivityComponentLoader());
        }
        return componentLoader;
    }

    public void setComponentLoader(ActivityComponentLoader componentLoader) {
        this.componentLoader = componentLoader;
    }
}