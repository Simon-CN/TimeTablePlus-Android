package com.sx.timetableplus.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Http.ApiClient;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.Utility.SharedPreferencesUtils;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sx on 2016/12/23.
 */

public class UserInfo {

    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_USER_PORTRAIT = "userPortrait";
    public static final String KEY_USER_BACKGROUND = "userBackground";
    public static final String KEY_ACCESS_TOKEN = "accessToken";
    public static final String KEY_USER_DESCRIPTION = "userDescription";


    private static final String defaultBackground = "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1610/31/c5/29213364_1477922832832_800x800.jpg";

    private static UserInfo userInfo;

    private UserInfo() {
    }

    public static UserInfo getInstance(Context context) {
        initUserInfo(context);
        return userInfo;
    }

    private static void initUserInfo(Context context) {
        userInfo = new UserInfo();
        userInfo.desc = (String) SharedPreferencesUtils.getParam(context, KEY_USER_DESCRIPTION, "");
        userInfo.portrait = (String) SharedPreferencesUtils.getParam(context, KEY_USER_PORTRAIT, "http://noImage.png");
        userInfo.background = (String) SharedPreferencesUtils.getParam(context, KEY_USER_BACKGROUND, defaultBackground);
        userInfo.screenName = (String) SharedPreferencesUtils.getParam(context, KEY_USER_NAME, "");
        userInfo.token = (String) SharedPreferencesUtils.getParam(context, KEY_ACCESS_TOKEN, "");

        Log.i("UserInfo", "token: " + userInfo.token + "\nscreenName: " + userInfo.screenName + "\nportrait: " + userInfo.portrait
                + "\nbackground: " + userInfo.background + "\n");
    }

    private String screenName;
    private String desc;
    private String token;
    private String portrait;
    private String background;

    public boolean isLogin() {
        return !TextUtils.isEmpty(token);
    }

    public String getScreenName() {
        return screenName;
    }

    public String getDesc() {
        return desc;
    }

    public String getToken() {
        return token;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public static void clearUserInfo(Context context) {
        SharedPreferencesUtils.setParam(context, KEY_USER_NAME, "");
        SharedPreferencesUtils.setParam(context, KEY_USER_DESCRIPTION, "");
        SharedPreferencesUtils.setParam(context, KEY_USER_BACKGROUND, defaultBackground);
        SharedPreferencesUtils.setParam(context, KEY_USER_PORTRAIT, "http://noImage.png");
        SharedPreferencesUtils.setParam(context, KEY_ACCESS_TOKEN, "");
    }

    public static void updateUserInfo(final Context context) {
        ApiClient client = new ApiClient();
        client.getUserInfo(userInfo.token, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (ResponseUtil.hasError(responseBody)) {
                    ToastUtils.showShortToast(context, ResponseUtil.getErrorMessage(responseBody));
                } else {
                    String content = ResponseUtil.getJsonContent(responseBody);
                    if (content != null && !TextUtils.isEmpty(content)) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<UserInfo>() {
                        }.getType();

                        UserInfo li = gson.fromJson(content, type);
                        setUserInfo(context, li);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.showShortToast(context, R.string.network_error);
            }
        });
    }

    private static void setUserInfo(Context context, UserInfo li) {
        if (li.getScreenName() != null)
            SharedPreferencesUtils.setParam(context, KEY_USER_NAME, li.getScreenName());
        if (li.getDesc() != null)
            SharedPreferencesUtils.setParam(context, KEY_USER_DESCRIPTION, li.getDesc());
        if (li.getBackground() != null)
            SharedPreferencesUtils.setParam(context, KEY_USER_BACKGROUND, li.getBackground());
        if (li.getPortrait() != null)
            SharedPreferencesUtils.setParam(context, KEY_USER_PORTRAIT, li.getPortrait());
    }
}
