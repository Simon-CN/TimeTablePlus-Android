package com.sx.timetableplus.View.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Global.Timetable;
import com.sx.timetableplus.Http.ApiClient;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.Timeline.LessonTimelineActivity;
import com.sx.timetableplus.View.Adapter.LessonListAdapter;
import com.sx.timetableplus.View.Custom.DividerItemDecoration;
import com.sx.timetableplus.databinding.FragmentLessonListBinding;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sx on 2016/10/21.
 */

public class LessonListFragment extends Fragment {
    private Context mContext;
    FragmentLessonListBinding mBinding;
    private LessonListAdapter mAdapter;
    private List<LessonInfo> mData;
    private static final String KEY_DAY_OF_WEEK = "dayofweek";
    private static int dayofweek;


    public static LessonListFragment newInstance(int dayofweek) {
        LessonListFragment fragment = new LessonListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DAY_OF_WEEK, dayofweek);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_list, container, false);
        mContext = getActivity();
        dayofweek = getArguments().getInt(KEY_DAY_OF_WEEK);
        initView();
        return mBinding.getRoot();
    }

    private void initListener() {
        mAdapter.setmOnItemClickListener(new LessonListAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position, LessonInfo lesson) {
                Intent intent = new Intent(mContext, LessonTimelineActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lesson", lesson);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }

            @Override
            public void OnLongClick(int position, final LessonInfo lesson) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.tip);
                builder.setMessage("你确定要删除该课程吗？\n" + lesson.getName() + "\n" + lesson.getTeacher());
                builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        final ProgressDialog dialog = new ProgressDialog(mContext);
                        dialog.setMessage(mContext.getResources().getString(R.string.processing));
                        dialog.show();
                        ApiClient client = new ApiClient();
                        client.removeLessonFromTimetable(UserInfo.getInstance(mContext.getApplicationContext()).getToken(), lesson.getId(),
                                new AsyncHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                        dialog.dismiss();
                                        if (ResponseUtil.hasError(responseBody)) {
                                            ToastUtils.showShortToast(mContext, ResponseUtil.getErrorMessage(responseBody));
                                        } else {
                                            ToastUtils.showShortToast(mContext, R.string.remove_success);
                                        }
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                        dialogInterface.dismiss();
                                        ToastUtils.showShortToast(mContext, R.string.network_error);
                                    }
                                });
                    }
                });
                builder.setNegativeButton(R.string.cancel, null);
                builder.show();
            }
        });
    }

    private void initView() {
        mData = new ArrayList<>();
        mBinding.lessonListRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mData.addAll(Timetable.timetable.get(dayofweek));
        mAdapter = new LessonListAdapter(mData, mContext);
        initListener();
        mBinding.lessonListRecycler.setAdapter(mAdapter);
    }

    public void notifyLessonListChanged() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LessonList", "onResume: ");
    }
}
