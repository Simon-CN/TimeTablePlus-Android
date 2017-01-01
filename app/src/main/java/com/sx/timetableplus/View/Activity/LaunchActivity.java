package com.sx.timetableplus.View.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Global.Timetable;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.User.LoginActivity;
import com.sx.timetableplus.View.MainActivity;
import com.sx.timetableplus.databinding.ActivityLaunchBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sx on 2016/12/23.
 */

public class LaunchActivity extends BaseActivity {
    ActivityLaunchBinding mBinding;

    Handler retryHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getTimetable();
        }
    };

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
    }

    @Override
    protected void initView() {
        if (UserInfo.getInstance(getApplicationContext()).isLogin()) {
            UserInfo.updateUserInfo(getApplicationContext());
            getTimetable();
        } else {
            jumpToActivity(LoginActivity.class);
            finish();
        }
    }


    private void getTimetable() {

        mClient.getTimetable(UserInfo.getInstance(getApplicationContext()).getToken(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (ResponseUtil.hasError(responseBody)) {
                    ToastUtils.showShortToast(LaunchActivity.this, ResponseUtil.getErrorMessage(responseBody));
                    retryHandler.sendEmptyMessage(66);
                } else {
                    String content = ResponseUtil.getJsonContent(responseBody);
                    if (content != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<ArrayList<LessonInfo>>>() {
                        }.getType();
                        Timetable.timetable = gson.fromJson(content, type);
                    }
                    jumpToActivity(MainActivity.class);
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.showShortToast(LaunchActivity.this, R.string.network_error);
                retryHandler.sendEmptyMessage(66);
            }
        });
    }

}
