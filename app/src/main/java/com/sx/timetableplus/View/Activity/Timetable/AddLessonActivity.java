package com.sx.timetableplus.View.Activity.Timetable;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.Adapter.SearchLessonResultAdapter;
import com.sx.timetableplus.databinding.ActivityAddLessonBinding;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sx on 2016/10/28.
 */

public class AddLessonActivity extends BaseActivity {
    ActivityAddLessonBinding mBinding;
    private List<LessonInfo> mData;
    private SearchLessonResultAdapter mAdapter;


    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_lesson);
    }

    @Override
    protected void initView() {
        initToolbar();
        initData();
        mAdapter = new SearchLessonResultAdapter(this, mData);
        mAdapter.setOnItemClickListener(new SearchLessonResultAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                final ProgressDialog dialog = new ProgressDialog(AddLessonActivity.this);
                dialog.setMessage(getResources().getString(R.string.processing));
                int lid = mData.get(position).getId();
                mClient.addLessonToTimetable(UserInfo.getInstance(getApplicationContext()).getToken(), lid, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (ResponseUtil.hasError(responseBody)) {
                            ToastUtils.showShortToast(AddLessonActivity.this, ResponseUtil.getErrorMessage(responseBody));
                        } else {
                            dialog.dismiss();
                            ToastUtils.showShortToast(AddLessonActivity.this, "添加成功...");
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        ToastUtils.showShortToast(AddLessonActivity.this, R.string.network_error);
                    }
                });
            }
        });
        mBinding.searchResultRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.searchResultRecycler.setAdapter(mAdapter);

        mBinding.createLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(CreateNewLessonActivity.class);
            }
        });

        mBinding.lessonSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mData.clear();
                if (charSequence.length() < 2)
                    return;
                mClient.searchLesson(charSequence.toString(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (ResponseUtil.hasError(responseBody)) {
                            ToastUtils.showShortToast(AddLessonActivity.this, ResponseUtil.getErrorMessage(responseBody));
                        } else {
                            String content = ResponseUtil.getJsonContent(responseBody);
                            if (content != null && !TextUtils.isEmpty(content)) {
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<LessonInfo>>() {
                                }.getType();
                                List<LessonInfo> rst = gson.fromJson(content, type);
                                mData.addAll(rst);
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        ToastUtils.showShortToast(AddLessonActivity.this, R.string.network_error);
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    protected void initToolbar() {
        toolbar = mBinding.addLessonToolbar;
        toolbar.setTitle(R.string.add_lesson_title);
        super.initToolbar();
    }

    protected void initData() {
        mData = new ArrayList<>();
    }
}
