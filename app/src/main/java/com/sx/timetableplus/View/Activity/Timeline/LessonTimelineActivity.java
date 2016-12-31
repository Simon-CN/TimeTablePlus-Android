package com.sx.timetableplus.View.Activity.Timeline;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.Model.Timeline;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.BasePullLoadActivity;
import com.sx.timetableplus.View.Adapter.MineTimelineAdapter;
import com.sx.timetableplus.databinding.ActivityLessonTimelineBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sx on 2016/12/21.
 */

public class LessonTimelineActivity extends BasePullLoadActivity {
    ActivityLessonTimelineBinding mBinding;
    private List<Timeline> mData;
    private MineTimelineAdapter mAdapter;
    private LessonInfo mLesson;
    private static int page;

    @Override
    protected void initToolbar() {
        toolbar = mBinding.lessonTimelineToolbar.toolbar;
        toolbar.setTitle(R.string.lesson_timeline);
        super.initToolbar();
    }

    @Override
    public void getData(final boolean isRefresh) {
        if (isRefresh)
            page = 1;
        mClient.getLessonTimeline(mLesson.getId(), page++, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                endLoadingRefresh(isRefresh);
                if (ResponseUtil.hasError(responseBody)) {
                    ToastUtils.showShortToast(LessonTimelineActivity.this, ResponseUtil.getErrorMessage(responseBody));
                    page--;
                } else {
                    String content = ResponseUtil.getJsonContent(responseBody);
                    if (!TextUtils.isEmpty(content)) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Timeline>>() {
                        }.getType();
                        List<Timeline> rst = new ArrayList<>();
                        rst = gson.fromJson(content, type);
                        if (isRefresh)
                            mData.clear();
                        if (rst == null || rst.isEmpty()) {
                            setNoMoreData();
                        } else {
                            mData.addAll(rst);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                endLoadingRefresh(isRefresh);
                ToastUtils.showShortToast(LessonTimelineActivity.this, R.string.network_error);
            }
        });
    }

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lesson_timeline);
        mRecyclerView = mBinding.lessonTimelineRecycler;
    }

    @Override
    protected void initView() {
        page = 1;
        mData = new ArrayList<>();
        mLesson = new LessonInfo();
        try {
            mLesson = (LessonInfo) getBundle().getSerializable("lesson");
        } catch (Exception e) {
            Log.d(TAG, "initView: " + e.getMessage());
            finish();
        }

        initToolbar();
        mAdapter = new MineTimelineAdapter(this, mData, mLesson);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        setupPullLoad();
        super.initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putInt(AddTimelineActivity.KEY_PAGE_TYPE, AddTimelineActivity.TYPE_LESSON_TIMELINE);
        bundle.putInt(AddTimelineActivity.KEY_RETURN_LESSON_ID, mLesson.getId());
        bundle.putString(AddTimelineActivity.KEY_RETURN_LESSON_NAME, mLesson.getName());
        jumpToActivityForResult(AddTimelineActivity.class, AddTimelineActivity.REQUEST_ADD_TIMELINE, bundle);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AddTimelineActivity.REQUEST_ADD_TIMELINE) {
            if (resultCode == RESULT_OK) {
                startRefresh();
            }
        }
    }
}
