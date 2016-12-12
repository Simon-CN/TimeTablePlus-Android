package com.sx.timetableplus.View.Custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.sx.timetableplus.R;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

/**
 * Created by sx on 2016/12/12.
 */

public class TimePickDialog extends Dialog {
    private Context context;
    private RangeSeekBar weekRangeBar;
    private RangeSeekBar classRangeBar;

    public TimePickDialog(Context context) {
        super(context);
        this.context = context;
    }

    public TimePickDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_time_picker);

        weekRangeBar = (RangeSeekBar) findViewById(R.id.week_range_bar);
        classRangeBar = (RangeSeekBar) findViewById(R.id.class_range_bar);

    }
}
