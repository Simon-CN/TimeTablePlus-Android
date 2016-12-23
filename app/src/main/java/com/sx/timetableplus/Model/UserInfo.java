package com.sx.timetableplus.Model;

import android.content.Context;
import android.text.TextUtils;

import com.sx.timetableplus.Utility.SharedPreferencesUtils;

import org.w3c.dom.Text;

/**
 * Created by sx on 2016/12/23.
 */

public class UserInfo {

    public static final String KEY_USER_ID = "userId";
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
        userInfo.id = (int) SharedPreferencesUtils.getParam(context, KEY_USER_ID, -1);
        userInfo.desc = (String) SharedPreferencesUtils.getParam(context, KEY_USER_DESCRIPTION, "");
        userInfo.portrait = (String) SharedPreferencesUtils.getParam(context, KEY_USER_PORTRAIT, "http://noImage.png");
        userInfo.background = (String) SharedPreferencesUtils.getParam(context, KEY_USER_BACKGROUND, defaultBackground);
        userInfo.screenName = (String) SharedPreferencesUtils.getParam(context, KEY_USER_NAME, "");
        userInfo.screenName = (String) SharedPreferencesUtils.getParam(context, KEY_ACCESS_TOKEN, "");
    }

    private int id;
    private String screenName;
    private String desc;
    private String token;
    private String portrait;
    private String background;

    public boolean isLogin() {
        return !TextUtils.isEmpty(token);
        //return true;
    }

    public int getId() {
        return id;
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
        return background;
    }
}
