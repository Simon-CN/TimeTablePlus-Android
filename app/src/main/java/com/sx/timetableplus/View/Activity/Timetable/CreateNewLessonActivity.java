package com.sx.timetableplus.View.Activity.Timetable;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.Custom.TimePickDialog;
import com.sx.timetableplus.databinding.ActivityCreateNewLessonBinding;
import com.sx.timetableplus.R;

/**
 * Created by sx on 2016/12/11.
 */

public class CreateNewLessonActivity extends BaseActivity {
    ActivityCreateNewLessonBinding mBinding;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_lesson);
    }

    @Override
    protected void initView() {
        initToolbar();
        mBinding.selectLessonTimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickDialog mDialog = new TimePickDialog(CreateNewLessonActivity.this);
                mDialog.show();
            }
        });
    }

    protected void initToolbar() {
        toolbar = mBinding.createLessonToolbar.toolbar;
        toolbar.setTitle(R.string.create_lesson);
        super.initToolbar();
    }

}
