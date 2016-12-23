package com.sx.timetableplus.View.Activity.User;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.ActivityLoginBinding;

/**
 * Created by sx on 2016/12/23.
 */

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding mBinding;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    @Override
    protected void initView() {
        initToolbar();
        mBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivity(MainActivity.class);
            }
        });

        mBinding.registerTipTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivity(RegisterActivity.class);
            }
        });
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.loginToolbar.toolbar;
        toolbar.setTitle(R.string.login);
        setSupportActionBar(toolbar);
    }
}
