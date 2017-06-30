package com.example.leo.myapplication.activity;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.leo.myapplication.R;
import com.example.leo.myapplication.utils.ActivityComponentLoader;
import com.example.leo.myapplication.utils.ActivityVariableCache;
import com.example.leo.myapplication.utils.AnimalApp;
import com.example.leo.myapplication.utils.DependencyCacheHelper;

import static com.example.leo.myapplication.presenters.MainActivityMVP.*;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private ActivityComponentLoader componentLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        componentLoader = new ActivityComponentLoader();
    }

    @Override
    protected void onPause() {
        super.onPause();
        componentLoader.unloadComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        componentLoader.loadComponents(findViewById(android.R.id.content), R.id.main_bottom_navigation);
        componentLoader.getBottomNavigationView(R.id.main_bottom_navigation)
            .setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.bottom_menu_btn_add:
                                navigateToFragment(FRAGMENT_NAVIGATE_TYPES.ADD);
                                break;
                            case R.id.bottom_menu_btn_edit:
                                navigateToFragment(FRAGMENT_NAVIGATE_TYPES.EDIT);
                                break;
                            case R.id.bottom_menu_btn_show_list:
                                navigateToFragment(FRAGMENT_NAVIGATE_TYPES.LIST);
                                break;
                            case R.id.bottom_menu_btn_remove:
                                IMainPresenter presenter = DependencyCacheHelper.getInstance(IMainPresenter.class);
                                presenter.delete(presenter.getIdSelectedAnimal());
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                }
            );
        if(AnimalApp.isStarting()){
            componentLoader
                    .getBottomNavigationView(R.id.main_bottom_navigation)
                    .setSelectedItemId(FRAGMENT_NAVIGATE_TYPES.LIST.getValue());
            AnimalApp.setIsStarting(false);
        }
        DependencyCacheHelper.getInstance(IMainPresenter.class).setMainActivity(this);
        onSelectedAnimalChanged(DependencyCacheHelper.getInstance(IMainPresenter.class).getIdSelectedAnimal());
    }

    @Override
    public void onBackPressed() {
        if(componentLoader.getBottomNavigationView(R.id.main_bottom_navigation).getSelectedItemId() != FRAGMENT_NAVIGATE_TYPES.LIST.getValue()) {
            componentLoader.getBottomNavigationView(R.id.main_bottom_navigation).setSelectedItemId(FRAGMENT_NAVIGATE_TYPES.LIST.getValue());
        } else {
            AnimalApp.setIsStarting(true);
            finish();
        }
    }

    private void navigateToFragment(FRAGMENT_NAVIGATE_TYPES typeToGoTo){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentToGoTo = getFragmentFromType(typeToGoTo);
        String tagToGoTo = fragmentToGoTo.getClass().getSimpleName();
        Fragment fragment = fragmentManager.findFragmentByTag(tagToGoTo);
        if(fragment == null || !fragment.isVisible()){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_frame_content, fragmentToGoTo, tagToGoTo);
            fragmentTransaction.addToBackStack(tagToGoTo);
            fragmentTransaction.commit();
        }
    }

    private Fragment getFragmentFromType(FRAGMENT_NAVIGATE_TYPES type){
        Fragment fragment;
        switch (type){
            case ADD:
                DependencyCacheHelper.getInstance(IMainPresenter.class).requestNewAnimalForm();
                fragment = AnimalFormFragment.newInstance();
                break;
            case EDIT:
                fragment = AnimalFormFragment.newInstance();
                break;
            case LIST:
            default:
                fragment = AnimalListFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public void showErrorMessage(@StringRes int message) {
        new AlertDialog.Builder(this)
            .setTitle(R.string.text_alert_title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            })
            .show();
    }

    @Override
    public void onSelectedAnimalChanged(int idAnimal) {
        MenuItem editItem = componentLoader.getBottomNavigationView(R.id.main_bottom_navigation).getMenu().findItem(R.id.bottom_menu_btn_edit);
        MenuItem deleteItem = componentLoader.getBottomNavigationView(R.id.main_bottom_navigation).getMenu().findItem(R.id.bottom_menu_btn_remove);
        if(idAnimal == 0){
            editItem.setEnabled(false);
            deleteItem.setEnabled(false);
        } else {
            editItem.setEnabled(true);
            deleteItem.setEnabled(true);
        }
    }

    @Override
    public void onFormSaveSuccessfully() {
        navigateToFragment(FRAGMENT_NAVIGATE_TYPES.LIST);
    }

    @Override
    public void onAnimalDeleted() {
        navigateToFragment(FRAGMENT_NAVIGATE_TYPES.LIST);
    }

    private enum FRAGMENT_NAVIGATE_TYPES{
        LIST(R.id.bottom_menu_btn_show_list),
        ADD(R.id.bottom_menu_btn_add),
        EDIT(R.id.bottom_menu_btn_remove);

        private final int id;
        FRAGMENT_NAVIGATE_TYPES(int id) {
            this.id = id;
        }

        public int getValue(){
            return id;
        }

        public static FRAGMENT_NAVIGATE_TYPES fromValue(int value){
            FRAGMENT_NAVIGATE_TYPES result = LIST;
            switch (value){
                case R.id.bottom_menu_btn_show_list:
                    break;
                case R.id.bottom_menu_btn_add:
                    break;
                case R.id.bottom_menu_btn_remove:
                    break;
            }
            return result;
        }
    }
}
