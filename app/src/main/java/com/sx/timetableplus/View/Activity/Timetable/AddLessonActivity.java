package com.sx.timetableplus.View.Activity.Timetable;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.Adapter.SearchLessonResultAdapter;
import com.sx.timetableplus.databinding.ActivityAddLessonBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sx on 2016/10/28.
 */

public class AddLessonActivity extends BaseActivity {
    ActivityAddLessonBinding mBinding;
    private List<LessonInfo> mData;
    private SearchLessonResultAdapter mAdapter;


    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_lesson);
    }

    @Override
    protected void initView() {
        initToolbar();
        initData();
        mAdapter = new SearchLessonResultAdapter(this, mData);
        mBinding.searchResultRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.searchResultRecycler.setAdapter(mAdapter);

        mBinding.createLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(CreateNewLessonActivity.class);
            }
        });
    }

    protected void initToolbar() {
        toolbar = mBinding.addLessonToolbar;
        toolbar.setTitle(R.string.add_lesson_title);
        super.initToolbar();
    }

    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LessonInfo li = new LessonInfo();
            li.setId(2);
            li.setName("软件体系结构分析与实践");
            li.setTeacher("王安生");
            li.setClassroom("主-324");
            li.setTerm(0);
            li.setStartTime(1);
            li.setEndTime(2);
            li.setStartWeek(2);
            li.setEndWeek(19);
            mData.add(li);
        }
    }
}
