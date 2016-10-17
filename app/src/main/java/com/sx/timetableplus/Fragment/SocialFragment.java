package com.sx.timetableplus.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.FragmentSocialBinding;

/**
 * Created by sx on 2016/10/17.
 */

public class SocialFragment extends Fragment {
    FragmentSocialBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_social, container, false);
        return mBinding.getRoot();
    }
}
