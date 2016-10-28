package com.sx.timetableplus.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sx.timetableplus.R;

/**
 * Created by sx on 2016/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Bundle savedInstanceState;
    protected String TAG = getClass().getSimpleName();
    protected Toolbar toolbar;

    abstract protected void getLayoutResource();

    abstract protected void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        getLayoutResource();

        initView();
    }

    protected void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void jumpToActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent, requestCode);
    }

    public void jumpToActivityForResult(Class<?> cls, int requestCode, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
    }

    public void jumpToActivity(Class<?> c) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        this.startActivity(intent);
    }

    public void jumpToActivity(Class<?> c, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        intent.putExtras(extras);
        this.startActivity(intent);
    }

    protected void jumpToActivity(Class<?> c, View sharedElement, String shareElementName) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, sharedElement, shareElementName);
        ActivityCompat.startActivity(
                this, intent, optionsCompat.toBundle()
        );

        /**
         * 在目标Activity中执行此操作
         */
        //ViewCompat.setTransitionName(sharedElement, shareElementName);
    }

    protected void jumpToActivity(Class<?> c, Bundle extras, View sharedElement, String shareElementName) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        intent.putExtras(extras);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, sharedElement, shareElementName);
        ActivityCompat.startActivity(
                this, intent, optionsCompat.toBundle());
    }

    protected Bundle getBundle() {
        return this.getIntent().getExtras();
    }


    protected String getTag() {
        return TAG;
    }
}
