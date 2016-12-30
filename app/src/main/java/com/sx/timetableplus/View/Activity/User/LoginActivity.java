package com.sx.timetableplus.View.Activity.User;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.ActivityLoginBinding;

import cz.msebera.android.httpclient.Header;

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
                mClient.login(mBinding.usernameEdt.getText().toString(), mBinding.passwordEdt.getText().toString(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
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
