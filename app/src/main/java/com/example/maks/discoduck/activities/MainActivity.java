package com.example.maks.discoduck.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.maks.discoduck.R;
import com.example.maks.discoduck.fragments.BaseFragment;
import com.example.maks.discoduck.fragments.CatalogFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private FragmentManager fragmentManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        checkBackButton();

        pushFragmentToStack(new CatalogFragment(), CatalogFragment.TAG);
    }

    @Override
    public void onBackStackChanged() {
        checkBackButton();
    }

    @Override
    public void onBackPressed() {
        if (null != fragmentManager && fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void checkBackButton() {
        if (null != this.getSupportActionBar()) {
            if (getBackStackEntryCount() > 1) {
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                this.getSupportActionBar().setDisplayShowHomeEnabled(true);
            } else {
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                this.getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }
    }

    public int getBackStackEntryCount() {
        return null != fragmentManager ? fragmentManager.getBackStackEntryCount() : 0;
    }

    public void pushFragmentToStack(BaseFragment fragment, String tag) {
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);
        if (!fragmentPopped && null == fragmentManager.findFragmentByTag(tag)) {
            addNewFragmentToStack(fragment, tag);
        } else if (null != fragmentManager.findFragmentByTag(tag)) {
            showFragment(fragment);
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    private void addNewFragmentToStack(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ac_main_fl_fragment_container, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    public void setTitle(String title) {
        if (null != toolbar) {
            toolbar.setTitle(title);
        }
    }
}
