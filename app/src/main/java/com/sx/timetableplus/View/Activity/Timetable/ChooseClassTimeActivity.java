package com.sx.timetableplus.View.Activity.Timetable;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sx.timetableplus.Global.StaticResource;
import com.sx.timetableplus.R;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.databinding.ActivityChooseClassTimeBinding;

/**
 * Created by sx on 2016/12/12.
 */

public class ChooseClassTimeActivity extends BaseActivity {
    public static final int REQUEST_TIME_INFO = 101;
    public static final String KEY_RESULT_WEEK = "week";
    public static final String KEY_RESULT_CLASS = "class";
    public static final String KEY_RESULT_DAYOFWEEK = "dayofweek";
    public static final String KEY_TIME_RESULT = "timeResult";
    ActivityChooseClassTimeBinding mBinding;
    private AlertDialog.Builder builder;

    private int[] weekRange = {-1, -1};
    private int[] classRange = {-1, -1};
    private int dayofweek = -1;

    @Override
    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_class_time);
    }

    @Override
    protected void initView() {
        initToolbar();
        setupDialog();
        mBinding.chooseDayofweekTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
    }

    protected void initToolbar() {
        toolbar = mBinding.chooseClassTimeToolbar.toolbar;
        toolbar.setTitle(R.string.class_time_selector);
        super.initToolbar();
    }

    protected void setupDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.class_time_selector);
        builder.setSingleChoiceItems(StaticResource.WeekDay, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mBinding.chooseDayofweekTxt.setText(StaticResource.WeekDay[which]);
                dayofweek = which;
            }
        });
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        weekRange[0] = mBinding.weekRangeBar.getSelectedMinValue().intValue();
        weekRange[1] = mBinding.weekRangeBar.getSelectedMaxValue().intValue();
        classRange[0] = mBinding.classRangeBar.getSelectedMinValue().intValue();
        classRange[1] = mBinding.classRangeBar.getSelectedMaxValue().intValue();
        if (dayofweek == -1) {
            Toast.makeText(this, R.string.choose_dayofweek_toast, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_RESULT_DAYOFWEEK, dayofweek);
            bundle.putIntArray(KEY_RESULT_WEEK, weekRange);
            bundle.putIntArray(KEY_RESULT_CLASS, classRange);
            intent.putExtra(KEY_TIME_RESULT, bundle);
            setResult(REQUEST_TIME_INFO, intent);
            finish();
        }

        return true;
    }
}
