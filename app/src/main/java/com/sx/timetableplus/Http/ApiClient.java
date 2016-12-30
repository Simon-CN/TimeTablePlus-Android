package com.sx.timetableplus.Http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;

/**
 * Created by sx on 2016/12/30.
 */

public class ApiClient extends BaseApiServer {
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

    public void editPassword(String token, String password, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("password", password);
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

    public void getTimetable(String token, AsyncHttpResponseHandler handler) {
        GetRequest(GET_TIMETABLE_URL + token, handler);
    }

    public void addLessonToTimetable(String token, int id, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("token", token);
        params.add("id", String.valueOf(id));
        PostRequest(ADD_LESSON_TO_TIMETABLE, params, handler);
    }

    public void createLesson(String name, String classroom, String teacher, int dayofweek,
                             int start_time, int end_time, int start_week, int end_week,
                             String token, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("name", name);
        params.add("classroom", classroom);
        params.add("teacher", teacher);
        params.add("dayofweek", String.valueOf(dayofweek));
        params.add("start_time", String.valueOf(start_time));
        params.add("end_time", String.valueOf(end_time));
        params.add("start_week", String.valueOf(start_week));
        params.add("end_week", String.valueOf(end_week));
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


}
