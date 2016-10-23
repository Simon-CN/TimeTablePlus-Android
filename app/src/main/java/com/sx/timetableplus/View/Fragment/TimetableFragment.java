package com.sx.timetableplus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.DateTime;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Adapter.LessonListPagerAdapter;
import com.sx.timetableplus.ViewModel.TimetableViewModel;
import com.sx.timetableplus.databinding.FragmentTimetableBinding;

import java.util.ArrayList;
import java.util.Calendar;
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


        mBinding.lessonListVp.setCurrentItem(mData.getTime().getDayofWeekNum(), true);

        return mBinding.getRoot();
    }

    private void initData() {
        mData = new TimetableViewModel();
        mData.setTime(new DateTime(Calendar.getInstance(), 8));
        mBinding.setDatetime(mData.getTime());

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
        lessonListPagerAdapter = new LessonListPagerAdapter(getFragmentManager(), fragments, titles);
        mBinding.lessonListVp.setAdapter(lessonListPagerAdapter);

        initListener();

    }

    private void initListener() {
        mBinding.dayofweekTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                mBinding.lessonListVp.setCurrentItem(index);

                mData.getTime().changeDate(index);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mBinding.lessonListVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.dayofweekTab.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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