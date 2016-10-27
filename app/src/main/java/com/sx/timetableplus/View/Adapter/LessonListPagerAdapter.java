package com.sx.timetableplus.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.View.Fragment.LessonListFragment;

import java.util.List;

/**
 * Created by sx on 2016/10/20.
 */

public class LessonListPagerAdapter extends FragmentPagerAdapter {
    private List<LessonListFragment> fragments;
    private List<String> titles;

    public LessonListPagerAdapter(FragmentManager fm, List<LessonListFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void NotifyLessonListChanged(int position) {
        fragments.get(position).notifyLessonListChanged();
        if (position > 0)
            fragments.get(position - 1).notifyLessonListChanged();
        if (position < 6)
            fragments.get(position + 1).notifyLessonListChanged();
    }
}
