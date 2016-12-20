package com.sx.timetableplus.Utility;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sx on 2016/12/20.
 */

public class ImageSetterUtil {
    @BindingAdapter({"image"})
    public static void setImage(ImageView img, String url){
        GlideUtil.glide(img.getContext(), img, url);
    }

    @BindingAdapter({"portrait"})
    public static void setPortrait(ImageView img, String url){
        GlideUtil.glidePortrait(img.getContext(), img, url);
    }

    @BindingAdapter("background")
    public static void setBackground(View view, int background){
        view.setBackgroundResource(background);
    }
}
