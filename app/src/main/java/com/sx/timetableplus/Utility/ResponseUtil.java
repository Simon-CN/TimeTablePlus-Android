package com.sx.timetableplus.Utility;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by sx on 2016/12/30.
 */

public class ResponseUtil {

    private static String getJsonResult(byte[] responseBody) {
        return new String(responseBody);
    }

    private static int getErrorCode(byte[] responseBody) {
        String s = getJsonResult(responseBody);
        int errorCode = 0;
        try {
            JSONObject json = new JSONObject(s);
            errorCode = json.getInt("code");
        } catch (Exception e) {
            errorCode = 1;
            Log.d("Parsing Json", "getErrorCode: " + e.getMessage());
        }
        return errorCode;
    }

    public static String getJsonContent(byte[] responseBody) {
        String s = getJsonResult(responseBody);
        String content = null;
        try {
            JSONObject json = new JSONObject(s);
            content = json.getString("content");
            Log.d("Parsing Json", "getJsonContent: " + content);
        } catch (Exception e) {
            Log.d("Parsing Json", "json parse fail..." + e.getMessage() + "\n" + s);
        }
        return content;
    }

    public static boolean hasError(byte[] responseBody) {
        return !(0 == getErrorCode(responseBody));
    }

    public static String getErrorMessage(byte[] responseBody) {
        String errorMessage = null;
        try {
            JSONObject json = new JSONObject(getJsonResult(responseBody));
            errorMessage = json.getString("message");
        } catch (Exception e) {
            Log.d("Parsing Json", "getErrorMessage: " + e.getMessage());
        }
        return errorMessage;
    }
}
