package com.sx.timetableplus.Http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by sx on 2016/10/20.
 */

public class BaseService {
    public AsyncHttpClient client = new AsyncHttpClient();

    public void GetRequest(String url, AsyncHttpResponseHandler handler) {
        client.get(url, handler);
    }

    public void PostRequest(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.post(url, params, handler);
    }
}
