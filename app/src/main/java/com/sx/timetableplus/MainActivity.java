package com.sx.timetableplus;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.sx.timetableplus.View.Fragment.HomeContentFragment;
import com.sx.timetableplus.databinding.ActivityMainBinding;

import org.antlr.v4.Tool;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initToolbar();
        toolbar = mBinding.toolbarMain;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.mainDrawerLy, toolbar, R.string.navigation_open, R.string.navigation_close);
        mBinding.mainDrawerLy.setDrawerListener(toggle);
        toggle.syncState();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        HomeContentFragment content = new HomeContentFragment();
        ft.replace(R.id.content_fram, content);
        ft.commit();
    }

    protected void initToolbar() {
        toolbar = mBinding.toolbarMain;
        toolbar.setTitle("课程表");
        setSupportActionBar(toolbar);

    }
}
