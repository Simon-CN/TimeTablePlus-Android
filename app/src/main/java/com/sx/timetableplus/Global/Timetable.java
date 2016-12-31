package com.sx.timetableplus.Global;

import android.content.Context;
import android.os.Handler;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Http.ApiClient;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.LaunchActivity;
import com.sx.timetableplus.View.MainActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sx on 2016/10/27.
 */

public class Timetable {
    public static int weekNum;
    public static List<ArrayList<LessonInfo>> timetable;

    public static void AddLesson(LessonInfo lesson) {
        if (IsEmpty())
            timetable = new ArrayList<>(7);
        InsertLesson(lesson);
    }

    public static boolean IsEmpty() {
        return timetable.isEmpty();
    }

    private static void InsertLesson(LessonInfo lesson) {
        int week = lesson.getDayofweek();
        int flag = 0;
        for (int i = timetable.get(week).size() - 1; i >= 0; i--) {
            if (timetable.get(week).get(i).getEndTime() < lesson.getEndTime()) {
                timetable.get(week).add(i, lesson);
                flag++;
                break;
            }
        }
        if (flag != 1) {
            timetable.get(week).add(0, lesson);
        }
    }

    public static void updateTimetable(ApiClient mClient, final Context mContext, final Handler handler) {
        mClient.getTimetable(UserInfo.getInstance(mContext).getToken(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (ResponseUtil.hasError(responseBody)) {
                    ToastUtils.showShortToast(mContext, ResponseUtil.getErrorMessage(responseBody));
                } else {
                    String content = ResponseUtil.getJsonContent(responseBody);
                    if (content != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<ArrayList<LessonInfo>>>() {
                        }.getType();
                        timetable = gson.fromJson(content, type);
                        handler.sendEmptyMessage(233);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.showShortToast(mContext, R.string.network_error);
            }
        });
    }
}
