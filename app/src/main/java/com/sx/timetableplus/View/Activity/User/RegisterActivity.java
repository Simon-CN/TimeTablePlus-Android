package com.sx.timetableplus.View.Activity.User;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.ActivityRegisterBinding;

import cz.msebera.android.httpclient.Header;

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
                final ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
                dialog.setMessage(getResources().getString(R.string.processing));
                dialog.show();
                if (mBinding.usernameEdt.getText().toString().isEmpty()) {
                    ToastUtils.showShortToast(RegisterActivity.this, R.string.username_no_null);
                    return;
                }
                if (mBinding.passwordEdt.getText().toString().isEmpty()) {
                    ToastUtils.showShortToast(RegisterActivity.this, R.string.password_no_null);
                    return;
                }
                if (!mBinding.passwordEdt.getText().toString().equals(mBinding.passwordRepeatEdt.getText().toString())) {
                    ToastUtils.showShortToast(RegisterActivity.this, R.string.password_repeat_no_same);
                    return;
                }

                mClient.register(mBinding.usernameEdt.getText().toString(), mBinding.passwordEdt.getText().toString(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        dialog.dismiss();
                        if (ResponseUtil.hasError(responseBody)) {
                            ToastUtils.showShortToast(RegisterActivity.this, ResponseUtil.getErrorMessage(responseBody));
                        } else {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        dialog.dismiss();
                        ToastUtils.showShortToast(RegisterActivity.this, R.string.network_error);
                    }
                });
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
