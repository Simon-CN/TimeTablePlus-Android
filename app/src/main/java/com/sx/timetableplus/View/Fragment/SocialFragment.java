package com.sx.timetableplus.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.blankj.utilcode.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Http.ApiClient;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.Utility.SharedPreferencesUtils;
import com.sx.timetableplus.View.Activity.User.EditPasswordActivity;
import com.sx.timetableplus.View.Activity.User.LoginActivity;
import com.sx.timetableplus.View.Activity.User.UploadPictureActivity;
import com.sx.timetableplus.databinding.FragmentSocialBinding;

import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;


/**
 * Created by sx on 2016/10/17.
 */

public class SocialFragment extends Fragment {
    FragmentSocialBinding mBinding;
    private Context mContext;
    private ApiClient mClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_social, container, false);
        mContext = getActivity();
        mClient = new ApiClient();

        initView();

        return mBinding.getRoot();
    }

    protected void closeInput() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
            imm.hideSoftInputFromWindow(mBinding.userDescEdt.getWindowToken(), 0);
        }
    }

    private void initView() {
        String desc = UserInfo.getInstance(mContext.getApplicationContext()).getDesc();
        if (desc != null && TextUtils.isEmpty(desc))
            mBinding.userDescEdt.setText(desc);
        mBinding.saveUserDescBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = mBinding.userDescEdt.getText().toString();
                closeInput();
                if (!TextUtils.isEmpty(desc)) {
                    mClient.editDescription(UserInfo.getInstance(mContext.getApplicationContext()).getToken(), desc, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (ResponseUtil.hasError(responseBody)) {
                                ToastUtils.showShortToast(mContext, ResponseUtil.getErrorMessage(responseBody));
                            } else {
                                UserInfo.updateUserInfo(mContext.getApplicationContext());
                                ToastUtils.showShortToast(mContext, R.string.save_finish);
                                mBinding.descTitleTxt.requestFocus();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            ToastUtils.showShortToast(mContext, R.string.network_error);
                        }
                    });
                }
            }
        });

        mBinding.uploadPortraitTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToUploadActivity(1);
            }
        });

        mBinding.uploadBackgroundTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToUploadActivity(2);
            }
        });

        mBinding.editPasswordTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditPasswordActivity.class);
                startActivity(intent);
            }
        });

        mBinding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo.clearUserInfo(mContext.getApplicationContext());
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    protected void jumpToUploadActivity(int type) {
        Intent intent = new Intent(mContext, UploadPictureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(UploadPictureActivity.KEY_PAGE_TYPE, type);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}