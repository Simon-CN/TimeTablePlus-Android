package com.sx.timetableplus.Utility;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.TextView;

/**
 * Created by sx on 2016/12/22.
 */

public class DrawableUtil {

    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;

    /**
     * @param dw       Drawable
     * @param tv       Target View
     * @param position Left,Top,Right,Botton
     */
    public static void LoadDrawable(Drawable dw, TextView tv, int position) {
        dw.setBounds(0, 0, dw.getMinimumWidth(), dw.getMinimumHeight());
        tv.setCompoundDrawables(position == LEFT ? dw : null, position == TOP ? dw : null,
                position == RIGHT ? dw : null, position == BOTTOM ? dw : null);
    }
}
