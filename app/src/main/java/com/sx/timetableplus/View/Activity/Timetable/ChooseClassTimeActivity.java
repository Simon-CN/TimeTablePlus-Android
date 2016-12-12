package com.sx.timetableplus.View.Activity.Timetable;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
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
    ActivityChooseClassTimeBinding mBinding;
    private AlertDialog.Builder builder;
    private boolean[] dayOfWeek = new boolean[]{false, false, false, false, false, false, false};

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
        finish();
        return true;
    }
}
