package com.sx.timetableplus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.FragmentTimetableBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sx on 2016/10/17.
 */

public class TimetableFragment extends Fragment {
    FragmentTimetableBinding mBinding;
    private List<String> titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false);

        initView();

        return mBinding.getRoot();
    }

    private void initView() {
        initTitle();
        mBinding.dayofweekTab.setTabMode(TabLayout.MODE_FIXED);
        for (String s : titles
                ) {
            mBinding.dayofweekTab.addTab(mBinding.dayofweekTab.newTab().setText(s));
        }
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