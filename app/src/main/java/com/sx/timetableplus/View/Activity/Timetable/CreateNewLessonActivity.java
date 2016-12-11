package com.sx.timetableplus.View.Activity.Timetable;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatDelegate;

import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.databinding.ActivityCreateNewLessonBinding;
import com.sx.timetableplus.R;

/**
 * Created by sx on 2016/12/11.
 */

public class CreateNewLessonActivity extends BaseActivity {
    ActivityCreateNewLessonBinding mBinding;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_lesson);
    }

    @Override
    protected void initView() {
        initToolbar();
    }

    protected void initToolbar() {
        toolbar = mBinding.createLessonToolbar.toolbar;
        toolbar.setTitle(R.string.create_lesson);
        super.initToolbar();
    }
}
