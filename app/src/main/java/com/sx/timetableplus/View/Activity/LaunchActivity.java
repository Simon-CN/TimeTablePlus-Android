package com.sx.timetableplus.View.Activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;

import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.ActivityLaunchBinding;

/**
 * Created by sx on 2016/12/23.
 */

public class LaunchActivity extends BaseActivity {
    ActivityLaunchBinding mBinding;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
    }

    @Override
    protected void initView() {
        if (UserInfo.getInstance(getApplicationContext()).isLogin()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    jumpToActivity(MainActivity.class);
                    finish();
                }
            }, 2000);


        } else {

        }

    }
}
