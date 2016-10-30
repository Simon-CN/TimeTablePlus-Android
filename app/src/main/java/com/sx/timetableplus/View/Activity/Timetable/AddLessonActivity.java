package com.sx.timetableplus.View.Activity.Timetable;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatDelegate;

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
}
