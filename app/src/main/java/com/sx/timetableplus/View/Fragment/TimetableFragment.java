package com.sx.timetableplus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.FragmentTimetableBinding;

/**
 * Created by sx on 2016/10/17.
 */

public class TimetableFragment extends Fragment {
    FragmentTimetableBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false);
        return mBinding.getRoot();
    }
}