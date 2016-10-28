package com.sx.timetableplus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Global.Timetable;
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

import static android.content.ContentValues.TAG;

/**
 * Created by sx on 2016/10/17.
 */

public class TimetableFragment extends Fragment {
    FragmentTimetableBinding mBinding;
    private List<String> titles;
    private LessonListPagerAdapter lessonListPagerAdapter;
    private List<LessonListFragment> fragments;
    private DateTime mTime;
    private static final int workDay = 7;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false);
        fragments = new ArrayList<>();
        initData();
        initView();

        mBinding.lessonListVp.setCurrentItem(mTime.getDayofWeekNum(), true);

        return mBinding.getRoot();
    }

    private void initData() {
        mTime = new DateTime(Calendar.getInstance(), 9);
        mBinding.setDatetime(mTime);

        List<ArrayList<LessonInfo>> timetable = new ArrayList<>();
        for (int i = 0; i < workDay; i++) {
            ArrayList<LessonInfo> temp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                LessonInfo lesson = new LessonInfo();
                lesson.setName("中国特色社会主义理论与实践研究");
                lesson.setClassroom("3-239");
                lesson.setDayofweek(3);
                lesson.setId(1);
                lesson.setTeacher("马新颖");
                lesson.setStartTime(1);
                lesson.setEndTime(2);
                lesson.setStartWeek(2);
                lesson.setEndWeek(19);
                temp.add(lesson);
            }
            timetable.add(temp);
        }
        Timetable.timetable = timetable;
    }

    private void initView() {
        initTitle();
        mBinding.dayofweekTab.setTabMode(TabLayout.MODE_FIXED);

        for (int i = 0; i < workDay; i++) {
            mBinding.dayofweekTab.addTab(mBinding.dayofweekTab.newTab().setText(titles.get(i)));
            fragments.add(LessonListFragment.newInstance(i));
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

                mTime.changeDate(index);
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

        mBinding.addLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: TimetableFragment....");
    }
}