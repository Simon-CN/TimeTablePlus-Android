package com.sx.timetableplus;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sx.timetableplus.View.Fragment.HomeContentFragment;
import com.sx.timetableplus.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initToolbar();

        getSupportActionBar().hide();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        HomeContentFragment content = new HomeContentFragment();
        ft.replace(R.id.content_fram, content);
        ft.commit();
    }

    protected void initToolbar() {
        mBinding.toolbarMain.setTitle("课程表");
    }
}
