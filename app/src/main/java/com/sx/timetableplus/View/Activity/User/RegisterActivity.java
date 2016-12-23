package com.sx.timetableplus.View.Activity.User;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.ActivityRegisterBinding;

/**
 * Created by sx on 2016/12/23.
 */

public class RegisterActivity extends BaseActivity {
    ActivityRegisterBinding mBinding;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
    }

    @Override
    protected void initView() {
        initToolbar();
        mBinding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivity(MainActivity.class);
            }
        });
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.registerToolbar.toolbar;
        toolbar.setTitle(R.string.register);
        super.initToolbar();
    }
}
