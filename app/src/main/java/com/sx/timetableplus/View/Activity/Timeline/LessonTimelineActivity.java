package com.sx.timetableplus.View.Activity.Timeline;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.sx.timetableplus.Model.Timeline;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BasePullLoadActivity;
import com.sx.timetableplus.View.Adapter.MineTimelineAdapter;
import com.sx.timetableplus.databinding.ActivityLessonTimelineBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sx on 2016/12/21.
 */

public class LessonTimelineActivity extends BasePullLoadActivity {
    ActivityLessonTimelineBinding mBinding;
    private List<Timeline> mData;
    private MineTimelineAdapter mAdapter;


    @Override
    protected void initToolbar() {
        toolbar = mBinding.lessonTimelineToolbar.toolbar;
        toolbar.setTitle(R.string.lesson_timeline);
        super.initToolbar();
    }

    @Override
    public void getData(boolean isRefresh) {
        if (isRefresh) {
            mData.clear();
            for (int i = 0; i < 10; i++) {
                Timeline temp = new Timeline();
                temp.setUserName("New Miaopasi");
                temp.setLessonName("New 中国特色社会主义");
                temp.setContent("今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊");
                temp.setPortrait("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=801952764,820373701&fm=21&gp=0.jpg");
                mData.add(temp);
            }
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setOnRefreshComplete();
            mRecyclerView.onFinishLoading(true, false);
        } else {
            for (int i = 0; i < 10; i++) {
                Timeline temp = new Timeline();
                temp.setUserName("Add Miaopasi");
                temp.setLessonName("Add 中国特色社会主义");
                temp.setContent("今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊");
                temp.setPortrait("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=801952764,820373701&fm=21&gp=0.jpg");
                mData.add(temp);
            }
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setOnLoadMoreComplete();
            mRecyclerView.onFinishLoading(true, false);
        }
    }

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lesson_timeline);
        mRecyclerView = mBinding.lessonTimelineRecycler;
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();

        initToolbar();
        mAdapter = new MineTimelineAdapter(this, mData, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        setupPullLoad();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
