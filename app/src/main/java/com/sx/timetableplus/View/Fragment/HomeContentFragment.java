package com.sx.timetableplus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.FragmentHomeContentBinding;

/**
 * Created by sx on 2016/10/13.
 */

public class HomeContentFragment extends Fragment {
    FragmentHomeContentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_content, container, false);

        mBinding.homeContent.setText("nihao...");

        return mBinding.getRoot();
    }
}
