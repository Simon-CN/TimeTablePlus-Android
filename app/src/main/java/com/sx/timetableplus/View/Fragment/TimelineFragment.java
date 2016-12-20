package com.sx.timetableplus.View.Fragment;

import android.content.Context;
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
import com.sx.timetableplus.View.Adapter.MineTimelineAdapter;
import com.sx.timetableplus.databinding.FragmentTimelineBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sx on 2016/10/17.
 */

public class TimelineFragment extends Fragment {
    FragmentTimelineBinding mBinding;
    private MineTimelineAdapter mAdapter;
    private List<Timeline> mData;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false);
        mContext = getContext();
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mData = new ArrayList<>();
        getData();

        mAdapter = new MineTimelineAdapter(getContext(), mData);
        mBinding.mineTimelineRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mBinding.mineTimelineRecycler.setAdapter(mAdapter);
    }

    protected void getData() {
        for (int i = 0; i < 10; i++) {
            Timeline temp = new Timeline();
            temp.setUserName("Miaopasi");
            temp.setLessonName("中国特色社会主义");
            temp.setContent("今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊今天上课了好高兴啊");
            mData.add(temp);
        }
    }

}