package com.sx.timetableplus.View.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Adapter.LessonListAdapter;
import com.sx.timetableplus.databinding.FragmentLessonListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sx on 2016/10/21.
 */

public class LessonListFragment extends Fragment {
    private List<LessonInfo> mData;
    private Context mContext;
    FragmentLessonListBinding mBinding;
    private LessonListAdapter mAdapter;
    private static final String KEY_DAY_OF_WEEK = "dayofweek";
    private static final String KEY_LESSON_LIST = "lessons";
    private static int dayofweek;

    public static LessonListFragment newInstance(int dayofweek, ArrayList<LessonInfo> mData) {
        LessonListFragment fragment = new LessonListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DAY_OF_WEEK, dayofweek);
        bundle.putSerializable(KEY_LESSON_LIST, mData.isEmpty() ? new ArrayList<>() : mData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_list, container, false);
        mContext = getActivity();
        dayofweek = getArguments().getInt(KEY_DAY_OF_WEEK);
        mData = (ArrayList<LessonInfo>) getArguments().getSerializable(KEY_LESSON_LIST);

        initView();
        return mBinding.getRoot();
    }

    private void initListener() {

    }

    private void initView() {
        mBinding.lessonListRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter = new LessonListAdapter(mData, mContext);
        initListener();
        mBinding.lessonListRecycler.setAdapter(mAdapter);
    }

}