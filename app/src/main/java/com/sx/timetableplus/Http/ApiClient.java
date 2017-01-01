package com.sx.timetableplus.Http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sx.timetableplus.Model.LessonInfo;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by sx on 2016/12/30.
 */

public class ApiClient extends BaseApiServer {
    public static final String PAGE_SIZE = String.valueOf(10);

    public void getUserInfo(String token, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        PostRequest(GET_USER_INFO_URL, params, handler);
    }

    public void login(String username, String password, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("username", username);
        params.add("password", password);
        PostRequest(LOGIN_URL, params, handler);
    }

    public void register(String username, String password, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("username", username);
        params.add("password", password);
        PostRequest(REGISTER_URL, params, handler);
    }

    public void editPassword(String token, String password, String oldpassword, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("password", password);
        params.add("oldpassword", oldpassword);
        PostRequest(EDIT_PASSWORD_URL, params, handler);
    }

    public void editDescription(String token, String description, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("desc", description);
        PostRequest(EDIT_DESCRIPTION_URL, params, handler);
    }

    public void uploadPortrait(String token, String path, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        try {
            File file = new File(path);
            params.put("file", file);
        } catch (Exception e) {
            Log.d("Upload", "portrait..." + e.getMessage());
        }
        params.add("token", token);
        PostRequest(UPLOAD_PORTRAIT_URL, params, handler);
    }

    public void uploadBackground(String token, String path, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        try {
            File file = new File(path);
            params.put("file", file);
        } catch (Exception e) {
            Log.d("Upload", "portrait..." + e.getMessage());
        }
        params.add("token", token);
        PostRequest(UPLOAD_BACKGROUND_URL, params, handler);
    }

    public void identifyUserInfo(String token, String password, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("password", password);
        PostRequest(IDENTIFY_USER_INFO_URL, params, handler);
    }

    public void getTimetable(String token, AsyncHttpResponseHandler handler) {
        GetRequest(GET_TIMETABLE_URL + token, handler);
    }

    public void addLessonToTimetable(String token, int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("id", String.valueOf(id));
        PostRequest(ADD_LESSON_TO_TIMETABLE, params, handler);
    }

    public void createLesson(LessonInfo lesson, String token, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("name", lesson.getName());
        params.add("classroom", lesson.getClassroom());
        params.add("teacher", lesson.getTeacher());
        params.add("dayofweek", String.valueOf(lesson.getDayofweek()));
        params.add("start_time", String.valueOf(lesson.getStartTime()));
        params.add("end_time", String.valueOf(lesson.getEndTime()));
        params.add("start_week", String.valueOf(lesson.getStartWeek()));
        params.add("end_week", String.valueOf(lesson.getEndWeek()));
        params.add("token", token);

        PostRequest(CREATE_LESSON_URL, params, handler);
    }

    public void searchLesson(String str, AsyncHttpResponseHandler handler) {
        GetRequest(SEARCH_LESSON_URL + str, handler);
    }

    public void getUserLesson(String token, AsyncHttpResponseHandler handler) {
        GetRequest(GET_USER_LESSON_URL + token, handler);
    }

    public void removeLessonFromTimetable(String token, int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("id", String.valueOf(id));
        PostRequest(REMOVE_LESSON_FROM_TIMETABLE_URL, params, handler);
    }

    public void getUserTimeline(String token, int page, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("page", String.valueOf(page));
        params.add("size", PAGE_SIZE);
        PostRequest(GET_USER_TIMELINE_URL, params, handler);
    }

    public void getLessonTimeline(int id, int page, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(id));
        params.add("page", String.valueOf(page));
        params.add("size", PAGE_SIZE);
        PostRequest(GET_LESSON_TIMELINE_URL, params, handler);
    }

    public void createTimeline(List<String> files, String token, int lesson_id, String location, String content
            , AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("lesson_id", String.valueOf(lesson_id));
        params.add("location", location);
        params.add("content", content);
        File[] fs = new File[10];
        if (files != null && files.size() > 0) {
            try {
                for (int i = 0; i < files.size(); i++) {
                    fs[i] = new File(files.get(i));
                }
                params.put("files", fs);
            } catch (Exception e) {
                Log.d("Upload", "Timeline picture.." + e.getMessage());
            }
        }

        PostRequest(CREATE_TIMELINE_URL, params, handler);
    }


}
