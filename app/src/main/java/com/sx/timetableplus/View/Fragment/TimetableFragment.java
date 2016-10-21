package com.sx.timetableplus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Adapter.LessonListPagerAdapter;
import com.sx.timetableplus.ViewModel.TimetableViewModel;
import com.sx.timetableplus.databinding.FragmentTimetableBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sx on 2016/10/17.
 */

public class TimetableFragment extends Fragment {
    FragmentTimetableBinding mBinding;
    private List<String> titles;
    private LessonListPagerAdapter lessonListPagerAdapter;
    private List<Fragment> fragments;
    private TimetableViewModel mData;
    private static final int workDay = 7;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false);
        fragments = new ArrayList<>();
        initData();
        initView();

        return mBinding.getRoot();
    }

    private void initData() {
        mData = new TimetableViewModel();
        mData.setCurrentTime(new Date(System.currentTimeMillis()));
        mData.setWeekNum(8);
        List<ArrayList<LessonInfo>> timetable = new ArrayList<>();
        for (int i = 0; i < workDay; i++) {
            ArrayList<LessonInfo> temp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                LessonInfo lesson = new LessonInfo();
                lesson.setName("我是课");
                lesson.setClassroom("3-239");
                temp.add(lesson);
            }
            timetable.add(temp);
        }
        mData.setTimetable(timetable);
    }

    private void initView() {
        initTitle();
        mBinding.dayofweekTab.setTabMode(TabLayout.MODE_FIXED);

        for (int i = 0; i < workDay; i++) {
            mBinding.dayofweekTab.addTab(mBinding.dayofweekTab.newTab().setText(titles.get(i)));
            fragments.add(LessonListFragment.newInstance(i + 1, mData.getTimetable().get(i)));
        }
        lessonListPagerAdapter = new LessonListPagerAdapter(getFragmentManager(), fragments);
        mBinding.lessonListVp.setAdapter(lessonListPagerAdapter);
        mBinding.dayofweekTab.setupWithViewPager(mBinding.lessonListVp);
    }

    private void initTitle() {
        titles = new ArrayList<>();
        titles.add("一");
        titles.add("二");
        titles.add("三");
        titles.add("四");
        titles.add("五");
        titles.add("六");
        titles.add("日");
    }
}