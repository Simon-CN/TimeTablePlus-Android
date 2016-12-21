package com.sx.timetableplus.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Global.Timetable;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.Timeline.LessonTimelineActivity;
import com.sx.timetableplus.View.Adapter.LessonListAdapter;
import com.sx.timetableplus.View.Custom.DividerItemDecoration;
import com.sx.timetableplus.databinding.FragmentLessonListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sx on 2016/10/21.
 */

public class LessonListFragment extends Fragment {
    private Context mContext;
    FragmentLessonListBinding mBinding;
    private LessonListAdapter mAdapter;
    private static final String KEY_DAY_OF_WEEK = "dayofweek";
    private static int dayofweek;


    public static LessonListFragment newInstance(int dayofweek) {
        LessonListFragment fragment = new LessonListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DAY_OF_WEEK, dayofweek);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_list, container, false);
        mContext = getActivity();
        dayofweek = getArguments().getInt(KEY_DAY_OF_WEEK);
        initView();
        return mBinding.getRoot();
    }

    private void initListener() {
        mAdapter.setmOnItemClickListener(new LessonListAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                Intent intent = new Intent(mContext, LessonTimelineActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void OnLongClick(int position) {

            }
        });
    }

    private void initView() {
        mBinding.lessonListRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter = new LessonListAdapter(Timetable.timetable.get(dayofweek), mContext);
        initListener();
        mBinding.lessonListRecycler.setAdapter(mAdapter);
    }

    public void notifyLessonListChanged() {
        try {
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }

    }

}
