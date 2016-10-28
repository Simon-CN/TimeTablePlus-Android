package com.sx.timetableplus.View.Activity.Timetable;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.databinding.ActivityAddLessonBinding;

/**
 * Created by sx on 2016/10/28.
 */

public class AddLessonActivity extends BaseActivity {
    ActivityAddLessonBinding mBinding;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_lesson);
    }

    @Override
    protected void initView() {
        initToolbar();
    }

    protected void initToolbar() {
        toolbar = mBinding.addLessonToolbar;
        toolbar.setTitle(R.string.add_lesson_title);
        super.initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return true;
    }
}
