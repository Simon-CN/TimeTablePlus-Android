package com.sx.timetableplus.View.Activity.User;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.databinding.ActivityEditPasswordBinding;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sx on 2017/1/1.
 */

public class EditPasswordActivity extends BaseActivity {
    ActivityEditPasswordBinding mBinding;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_password);
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.editPasswordToolbar.toolbar;
        toolbar.setTitle(R.string.change_password);
        super.initToolbar();
    }

    @Override
    protected void initView() {
        initToolbar();
        mBinding.changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.currentPasswordEdit.getText().toString().isEmpty() || mBinding.newPasswordEdt.getText().toString().isEmpty() ||
                        mBinding.repeatPasswordEdt.getText().toString().isEmpty()) {
                    ToastUtils.showShortToast(EditPasswordActivity.this, R.string.input_full);
                } else if (!mBinding.newPasswordEdt.getText().toString().equals(mBinding.repeatPasswordEdt.getText().toString())) {
                    ToastUtils.showShortToast(EditPasswordActivity.this, R.string.password_repeat_no_same);
                } else {
                    execChange();
                    UserInfo.clearUserInfo(getApplicationContext());
                    jumpToActivity(LoginActivity.class);
                    finish();
                }
            }
        });
    }

    private void execChange() {
        mClient.editPassword(UserInfo.getInstance(getApplicationContext()).getToken(),
                mBinding.newPasswordEdt.getText().toString(),
                mBinding.currentPasswordEdit.getText().toString(),
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (ResponseUtil.hasError(responseBody)) {
                            ToastUtils.showShortToast(EditPasswordActivity.this, ResponseUtil.getErrorMessage(responseBody));
                        } else {
                            ToastUtils.showShortToast(EditPasswordActivity.this, R.string.save_finish);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        ToastUtils.showShortToast(EditPasswordActivity.this, R.string.network_error);
                    }
                });
    }
}
