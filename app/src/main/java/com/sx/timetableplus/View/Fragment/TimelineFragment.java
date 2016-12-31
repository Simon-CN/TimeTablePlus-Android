package com.sx.timetableplus.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.tool.DataBindingBuilder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Model.Timeline;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.Timeline.AddTimelineActivity;
import com.sx.timetableplus.View.Adapter.MineTimelineAdapter;
import com.sx.timetableplus.databinding.FragmentTimelineBinding;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.app.Activity.RESULT_OK;
import static com.sx.timetableplus.View.Activity.Timeline.AddTimelineActivity.KEY_PAGE_TYPE;
import static com.sx.timetableplus.View.Activity.Timeline.AddTimelineActivity.REQUEST_ADD_TIMELINE;

/**
 * Created by sx on 2016/10/17.
 */

public class TimelineFragment extends BasePullLoadFragment {
    FragmentTimelineBinding mBinding;
    private MineTimelineAdapter mAdapter;
    private List<Timeline> mData;
    private Context mContext;
    private static int page;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false);
        mContext = getContext();
        mRecyclerView = mBinding.mineTimelineRecycler;
        initView();
        return mBinding.getRoot();
    }

    protected void initView() {
        mData = new ArrayList<>();
        page = 1;

        mAdapter = new MineTimelineAdapter(getContext(), mData);
        mBinding.mineTimelineRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mBinding.mineTimelineRecycler.setAdapter(mAdapter);
        setupPullLoad();

        mBinding.addTimelineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(AddTimelineActivity.KEY_PAGE_TYPE, AddTimelineActivity.TYPE_MY_TIMELINE);
                Intent intent = new Intent(mContext, AddTimelineActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_ADD_TIMELINE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_TIMELINE) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra(AddTimelineActivity.KEY_CREATE_TIMELINE_RESULT, false))
                    startRefresh();
            }
        }
    }

    @Override
    public void getData(final boolean isRefresh) {
        if (isRefresh)
            page = 1;
        mClient.getUserTimeline(UserInfo.getInstance(mContext).getToken(), page++, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                endLoadingRefresh(isRefresh);
                if (ResponseUtil.hasError(responseBody)) {
                    ToastUtils.showShortToast(mContext, ResponseUtil.getErrorMessage(responseBody));
                    page--;
                } else {
                    String content = ResponseUtil.getJsonContent(responseBody);
                    if (!TextUtils.isEmpty(content)) {
                        if (isRefresh)
                            mData.clear();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Timeline>>() {
                        }.getType();

                        List<Timeline> rst = gson.fromJson(content, type);
                        if (rst != null) {
                            mData.addAll(rst);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            setNoMoreData();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                endLoadingRefresh(isRefresh);
                ToastUtils.showShortToast(mContext, R.string.network_error);
            }
        });
    }

    public void startLoading() {
        startRefresh();
    }
}