package com.sx.timetableplus.Http;

import java.net.URL;

/**
 * Created by sx on 2016/12/30.
 */

public class BaseApiServer extends BaseService {
    private static final String BASE_URL = "http://10.205.27.210:80/";

    private static final String USER_URL = "api/user/";
    private static final String LESSON_URL = "api/lesson/";
    private static final String TIMELINE_URL = "api/timeline/";

    private static String UrlBuilder(String method, int controller) {
        String s = null;
        switch (controller) {
            case 1:
                s = BASE_URL + USER_URL;
                break;
            case 2:
                s = BASE_URL + LESSON_URL;
                break;
            case 3:
                s = BASE_URL + TIMELINE_URL;
                break;
        }
        s += method;
        return s;
    }

    public static final String GET_USER_INFO_URL = UrlBuilder("info", 1);
    public static final String LOGIN_URL = UrlBuilder("login", 1);
    public static final String REGISTER_URL = UrlBuilder("register", 1);
    public static final String EDIT_PASSWORD_URL = UrlBuilder("editPassword", 1);
    public static final String EDIT_DESCRIPTION_URL = UrlBuilder("editDescription", 1);
    public static final String UPLOAD_PORTRAIT_URL = UrlBuilder("portrait", 1);
    public static final String UPLOAD_BACKGROUND_URL = UrlBuilder("background", 1);

    public static final String GET_TIMETABLE_URL = UrlBuilder("timetable/", 2);
    public static final String ADD_LESSON_TO_TIMETABLE = UrlBuilder("timetable/add", 2);
    public static final String CREATE_LESSON_URL = UrlBuilder("create", 2);
    public static final String SEARCH_LESSON_URL = UrlBuilder("search/", 2);
    public static final String GET_USER_LESSON_URL = UrlBuilder("list/", 2);
    public static final String REMOVE_LESSON_FROM_TIMETABLE_URL = UrlBuilder("timetable/remove", 2);

    public static final String GET_USER_TIMELINE_URL = UrlBuilder("user", 3);
    public static final String GET_LESSON_TIMELINE_URL = UrlBuilder("lesson", 3);
    public static final String CREATE_TIMELINE_URL = UrlBuilder("create", 3);
}
