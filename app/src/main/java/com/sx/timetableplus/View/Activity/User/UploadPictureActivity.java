package com.sx.timetableplus.View.Activity.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.utils.ImageUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.ResponseUtil;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.databinding.ActivityUploadPictureBinding;

import org.florescu.android.util.BitmapUtil;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by sx on 2017/1/1.
 */

public class UploadPictureActivity extends BaseActivity {
    ActivityUploadPictureBinding mBinding;
    public static final String KEY_PAGE_TYPE = "pageType";
    public static final int REQUEST_IMAGE_SELECT = 101;
    private String path = null;
    private int pageType;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_upload_picture);

        pageType = getBundle().getInt(KEY_PAGE_TYPE);
    }

    @Override
    protected void initView() {
        initToolbar();

        mBinding.selectImageLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiImageSelector.create().single().start(UploadPictureActivity.this, REQUEST_IMAGE_SELECT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (path == null || TextUtils.isEmpty(path)) {
            ToastUtils.showShortToast(UploadPictureActivity.this, R.string.upload_picture_tip);
            return false;
        }

        if (pageType == 1)
            uploadPortrait();
        else
            uploadBackground();
        return true;
    }

    private void uploadPortrait() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getString(R.string.processing));
        dialog.show();
        mClient.uploadPortrait(UserInfo.getInstance(getApplicationContext()).getToken(), path, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                dialog.dismiss();
                if (ResponseUtil.hasError(responseBody)) {
                    ToastUtils.showShortToast(UploadPictureActivity.this, ResponseUtil.getErrorMessage(responseBody));
                } else {
                    ToastUtils.showShortToast(UploadPictureActivity.this, R.string.save_finish);
                    UserInfo.updateUserInfo(getApplicationContext());
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                ToastUtils.showShortToast(UploadPictureActivity.this, R.string.network_error);
            }
        });
    }

    private void uploadBackground() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getString(R.string.processing));
        dialog.show();
        mClient.uploadBackground(UserInfo.getInstance(getApplicationContext()).getToken(), path, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                dialog.dismiss();
                if (ResponseUtil.hasError(responseBody)) {
                    ToastUtils.showShortToast(UploadPictureActivity.this, ResponseUtil.getErrorMessage(responseBody));
                } else {
                    ToastUtils.showShortToast(UploadPictureActivity.this, R.string.save_finish);
                    UserInfo.updateUserInfo(getApplicationContext());
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                ToastUtils.showShortToast(UploadPictureActivity.this, R.string.network_error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_SELECT) {
            if (resultCode == RESULT_OK) {
                List<String> s = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                path = s.get(0);
                Bitmap bm = ImageUtils.getBitmap(s.get(0));
                mBinding.pictureIv.setImageBitmap(bm);
                mBinding.pictureIv.setVisibility(View.VISIBLE);
                mBinding.selectImageLy.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.uploadPictureToolbar.toolbar;
        if (pageType == 1)
            toolbar.setTitle(R.string.upload_portrait);
        else
            toolbar.setTitle(R.string.upload_background);
        super.initToolbar();
    }
}
