package com.sx.timetableplus.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Global.Timetable;
import com.sx.timetableplus.Http.ApiClient;
import com.sx.timetableplus.Model.DateTime;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.Timetable.AddLessonActivity;
import com.sx.timetableplus.View.Adapter.LessonListPagerAdapter;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.FragmentTimetableBinding;

import java.util.ArrayList;
import java.util.Calendar;
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
    private Context mContext;
    private static final int workDay = 7;

    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            lessonListPagerAdapter.NotifyLessonListChanged();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false);
        mContext = getActivity();
        fragments = new ArrayList<>();
        initData();
        initView();

        mBinding.lessonListVp.setCurrentItem(mTime.getDayofWeekNum(), true);

        return mBinding.getRoot();
    }

    private void initData() {
        mTime = new DateTime(Calendar.getInstance(), 19);
        mBinding.setDatetime(mTime);
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
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddLessonActivity.class);
                startActivity(intent);
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
        Timetable.updateTimetable(new ApiClient(), mContext, updateHandler);
    }
}