package com.sx.timetableplus.View.Activity.Timetable;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.sx.timetableplus.View.Activity.BaseActivity;
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
                jumpToActivityForResult(ChooseClassTimeActivity.class, ChooseClassTimeActivity.REQUEST_TIME_INFO);
            }
        });
    }

    protected void initToolbar() {
        toolbar = mBinding.createLessonToolbar.toolbar;
        toolbar.setTitle(R.string.create_lesson);
        super.initToolbar();
    }

}
