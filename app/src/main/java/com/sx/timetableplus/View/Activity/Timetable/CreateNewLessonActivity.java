package com.sx.timetableplus.View.Activity.Timetable;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.sx.timetableplus.Global.StaticResource;
import com.sx.timetableplus.Global.Timetable;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.databinding.ActivityCreateNewLessonBinding;
import com.sx.timetableplus.R;

/**
 * Created by sx on 2016/12/11.
 */

public class CreateNewLessonActivity extends BaseActivity {
    ActivityCreateNewLessonBinding mBinding;
    private LessonInfo mData;
    private boolean flag = false;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_lesson);
    }

    @Override
    protected void initView() {
        initToolbar();
        mData = new LessonInfo();
        mBinding.selectLessonTimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivityForResult(ChooseClassTimeActivity.class, ChooseClassTimeActivity.REQUEST_TIME_INFO);
            }
        });

        mBinding.createLessonConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag || TextUtils.isEmpty(mBinding.createLessonNameEdt.getText()) ||
                        TextUtils.isEmpty(mBinding.createLessonClassroomEdt.getText()) ||
                        TextUtils.isEmpty(mBinding.createLessonTeacherEdt.getText())) {
                    Toast.makeText(CreateNewLessonActivity.this, R.string.input_full, Toast.LENGTH_SHORT).show();
                } else {
                    mData.setName(mBinding.createLessonNameEdt.getText().toString());
                    mData.setTeacher(mBinding.createLessonTeacherEdt.getText().toString());
                    mData.setClassroom(mBinding.createLessonClassroomEdt.getText().toString());

                    Timetable.AddLesson(mData);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ChooseClassTimeActivity.REQUEST_TIME_INFO) {
            if (data != null) {
                Bundle bundle = data.getBundleExtra(ChooseClassTimeActivity.KEY_TIME_RESULT);
                int[] temp = bundle.getIntArray(ChooseClassTimeActivity.KEY_RESULT_CLASS);
                int[] temp0 = bundle.getIntArray(ChooseClassTimeActivity.KEY_RESULT_WEEK);

                mData.setDayofweek(bundle.getInt(ChooseClassTimeActivity.KEY_RESULT_DAYOFWEEK));
                mData.setStartWeek(temp0[0]);
                mData.setEndWeek(temp0[1]);
                mData.setStartTime(temp[0]);
                mData.setEndTime(temp[1]);

                flag = true;
                mBinding.selectLessonTimeTxt.setText(mData.getFullTimeString());
            }
        }
    }

    protected void initToolbar() {
        toolbar = mBinding.createLessonToolbar.toolbar;
        toolbar.setTitle(R.string.create_lesson);
        super.initToolbar();
    }

}
