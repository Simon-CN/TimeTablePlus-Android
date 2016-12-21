package com.sx.timetableplus.View.Activity.Timeline;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.databinding.ActivityAddTimelineBinding;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by sx on 2016/12/20.
 */

public class AddTimelineActivity extends BaseActivity {
    ActivityAddTimelineBinding mBinding;
    public static final int KEY_IMAGE_REQUEST = 102;
    private List<String> imageList;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_timeline);
    }

    @Override
    protected void initView() {
        imageList = new ArrayList<>();
        initToolbar();
        mBinding.selectImagesTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiImageSelector.create().start(AddTimelineActivity.this, KEY_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                imageList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Log.d(TAG, "onActivityResult: " + imageList.toString());
            }
        }
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.createTimelineToobar.toolbar;
        toolbar.setTitle(R.string.add_timeline);
        super.initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }
}
