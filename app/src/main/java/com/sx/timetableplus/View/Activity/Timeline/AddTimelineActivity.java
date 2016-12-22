package com.sx.timetableplus.View.Activity.Timeline;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.DrawableUtil;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.Adapter.SelectPicturesAdapter;
import com.sx.timetableplus.databinding.ActivityAddTimelineBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by sx on 2016/12/20.
 */

public class AddTimelineActivity extends BaseActivity {
    ActivityAddTimelineBinding mBinding;
    private List<String> imageList;
    private SelectPicturesAdapter mAdapter;

    public static final int KEY_IMAGE_REQUEST = 102;
    public static final int KEY_LOCATION_REQUEST = 103;
    public static final int KEY_LESSON_REQUEST = 104;
    public static final String KEY_RETURN_LOCATION = "location";
    public static final String KEY_RETURN_LESSON_NAME = "lessonName";
    public static final String KEY_RETURN_LESSON_ID = "lessonId";
    public static final String KEY_CREATE_TIMELINE_RESULT = "result";

    public boolean hasCircle;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_timeline);
    }

    @Override
    protected void initView() {
        hasCircle = false;
        imageList = new ArrayList<>();
        initToolbar();

        mAdapter = new SelectPicturesAdapter(this, imageList);
        mAdapter.setOnItemClickListener(new SelectPicturesAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                if (position == 0) {
                    MultiImageSelector.create().start(AddTimelineActivity.this, KEY_IMAGE_REQUEST);
                } else {
                    imageList.remove(position - 1);
                    mAdapter.notifyItemRemoved(position);
                    mAdapter.notifyItemRangeChanged(1, imageList.size() + 1);
                }
            }
        });
        mBinding.selectPicturesRecycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mBinding.selectPicturesRecycler.setAdapter(mAdapter);

        mBinding.chooseLocationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivityForResult(ChooseLocationActivity.class, KEY_LOCATION_REQUEST);
            }
        });

        mBinding.chooseLessonTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToActivityForResult(ChooseLessonActivity.class, KEY_LESSON_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> temp = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if (!imageList.isEmpty()) {
                    for (String s : temp
                            ) {
                        if (!imageList.contains(s)) {
                            imageList.add(s);
                        }
                    }
                } else {
                    imageList.addAll(temp);
                }
                mAdapter.notifyDataSetChanged();
                Log.d(TAG, "onActivityResult: " + imageList.toString());
            }
        } else if (requestCode == KEY_LOCATION_REQUEST) {
            if (resultCode == RESULT_OK) {
                mBinding.chooseLocationTxt.setText(data.getStringExtra(KEY_RETURN_LOCATION));
                Drawable dw = getResources().getDrawable(R.mipmap.ic_location_enabled);
                dw.setBounds(0, 0, dw.getMinimumWidth(), dw.getMinimumHeight());
                mBinding.chooseLocationTxt.setCompoundDrawables(dw, null, null, null);
            }
        } else if (requestCode == KEY_LESSON_REQUEST) {
            if (resultCode == RESULT_OK) {
                hasCircle = true;
                mBinding.chooseLessonTxt.setText(data.getStringExtra(KEY_RETURN_LESSON_NAME));
                DrawableUtil.LoadDrawable(getResources().getDrawable(R.mipmap.ic_lesson_circle_enabled), mBinding.chooseLessonTxt
                        , DrawableUtil.LEFT);
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
        if (!hasCircle) {
            Toast.makeText(this, R.string.choose_lesson_circle_tip, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mBinding.timelineContentEdt.getText().toString())) {
            Toast.makeText(this, R.string.input_content_tip, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(KEY_CREATE_TIMELINE_RESULT, true);
            setResult(RESULT_OK, intent);
            finish();
        }
        return true;
    }
}
