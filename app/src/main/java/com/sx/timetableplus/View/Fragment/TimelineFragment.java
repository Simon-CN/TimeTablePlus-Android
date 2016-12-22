package com.sx.timetableplus.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.tool.DataBindingBuilder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.Timeline;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.Timeline.AddTimelineActivity;
import com.sx.timetableplus.View.Adapter.MineTimelineAdapter;
import com.sx.timetableplus.databinding.FragmentTimelineBinding;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.sx.timetableplus.View.Activity.Timeline.AddTimelineActivity.KEY_PAGE_TYPE;
import static com.sx.timetableplus.View.Activity.Timeline.AddTimelineActivity.REQUEST_ADD_TIMELINE;

/**
 * Created by sx on 2016/10/17.
 */

public class TimelineFragment extends BasePullLoadFragment {
    FragmentTimelineBinding mBinding;
    private MineTimelineAdapter mAdapter;
    private List<Timeline> mData;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false);
        mContext = getContext();
        mRecyclerView = mBinding.mineTimelineRecycler;
        initView();
        return mBinding.getRoot();
    }

    protected void initView() {
        mData = new ArrayList<>();

        mAdapter = new MineTimelineAdapter(getContext(), mData);
        mBinding.mineTimelineRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mBinding.mineTimelineRecycler.setAdapter(mAdapter);
        setupPullLoad();

        mBinding.addTimelineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(AddTimelineActivity.KEY_PAGE_TYPE, AddTimelineActivity.TYPE_MY_TIMELINE);
                Intent intent = new Intent(mContext, AddTimelineActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_ADD_TIMELINE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_TIMELINE) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra(AddTimelineActivity.KEY_CREATE_TIMELINE_RESULT, false))
                    startRefresh();
            }
        }
    }

    @Override
    public void getData(boolean isRefresh) {
        if (isRefresh) {
            mData.clear();
            for (int i = 0; i < 10; i++) {
                Timeline temp = new Timeline();
                temp.setUserName("New Miaopasi" + i);
                temp.setLessonName("New 中国特色社会主义");
                temp.setContent("今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊");
                temp.setPortrait("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=801952764,820373701&fm=21&gp=0.jpg");
                mData.add(temp);
            }
            mAdapter.notifyDataSetChanged();
            endRefresh();
        } else {
            for (int i = 0; i < 10; i++) {
                Timeline temp = new Timeline();
                temp.setUserName("Add Miaopasi" + i);
                temp.setLessonName("Add 中国特色社会主义");
                temp.setContent("今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊");
                temp.setPortrait("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=801952764,820373701&fm=21&gp=0.jpg");
                mData.add(temp);
            }
            mAdapter.notifyDataSetChanged();
            endLoading();
        }

    }

    public void startLoading() {
        startRefresh();
    }
}