package com.sx.timetableplus.View.Activity.User;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.Utility.SharedPreferencesUtils;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.ActivityLoginBinding;

import org.json.JSONObject;

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
                final ProgressDialog dialog = new ProgressDialog(LoginActivity.this, R.string.processing);
                if (mBinding.usernameEdt.getText().toString().isEmpty()) {
                    ToastUtils.showShortToast(LoginActivity.this, R.string.username_no_null);
                    return;
                }
                if (mBinding.passwordEdt.getText().toString().isEmpty()) {
                    ToastUtils.showShortToast(LoginActivity.this, R.string.password_no_null);
                    return;
                }
                dialog.show();
                mClient.login(mBinding.usernameEdt.getText().toString(),
                        mBinding.passwordEdt.getText().toString(),
                        new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                if (!ResponseUtil.hasError(responseBody)) {
                                    String token = ResponseUtil.getJsonContent(responseBody);
                                    SharedPreferencesUtils.setParam(getApplicationContext(), UserInfo.KEY_ACCESS_TOKEN, token);
                                    SharedPreferencesUtils.setParam(getApplicationContext(), UserInfo.KEY_USER_NAME, mBinding.usernameEdt.getText().toString());
                                    dialog.dismiss();
                                    setResult(RESULT_OK);
                                    finish();
                                } else {
                                    ToastUtils.showShortToast(LoginActivity.this, R.string.username_password_incorrect);
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                ToastUtils.showShortToast(LoginActivity.this, R.string.network_error);
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
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.loginToolbar.toolbar;
        toolbar.setTitle(R.string.login);
        setSupportActionBar(toolbar);
    }
}
