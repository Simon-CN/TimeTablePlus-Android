package com.sx.timetableplus.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sx.timetableplus.R;

/**
 * Created by sx on 2016/12/20.
 */

public class GlideUtil {
    public static final String EMPTY_IMAGE_URL = "http://noImage.png";

    public static void glide(Context context, ImageView targetView, String url) {

        url = url == null || url.isEmpty() ? EMPTY_IMAGE_URL : url;

        Glide.with(context).load(url)
                .placeholder(R.drawable.loading_default_image)
                .error(R.drawable.load_fail_image)
                .crossFade()
                .into(targetView);
    }

    public static void glidePortrait(final Context context, final ImageView targetView, String url) {

        url = url == null || url.isEmpty() ? EMPTY_IMAGE_URL : url;

        Glide.with(context).load(url).asBitmap().centerCrop()
                .placeholder(R.mipmap.ic_default_portrait).into(new BitmapImageViewTarget(targetView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                targetView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static void glide(Context context, ImageView targetView, @DrawableRes int resId) {
        Glide.with(context).load(resId).placeholder(R.drawable.loading_default_image)
                .error(R.drawable.load_fail_image)
                .crossFade().into(targetView);
    }


    public static class CropCircleTransformation implements Transformation<Bitmap> {

        private BitmapPool mBitmapPool;

        public CropCircleTransformation(Context context) {
            this(Glide.get(context).getBitmapPool());
        }

        public CropCircleTransformation(BitmapPool pool) {
            this.mBitmapPool = pool;
        }

        @Override
        public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
            Bitmap source = resource.get();
            int size = Math.min(source.getWidth(), source.getHeight());

            int width = (source.getWidth() - size) / 2;
            int height = (source.getHeight() - size) / 2;

            Bitmap bitmap = mBitmapPool.get(size, size, Bitmap.Config.ARGB_8888);
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader =
                    new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            if (width != 0 || height != 0) {
                // source isn't square, move viewport to center
                Matrix matrix = new Matrix();
                matrix.setTranslate(-width, -height);
                shader.setLocalMatrix(matrix);
            }
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            return BitmapResource.obtain(bitmap, mBitmapPool);
        }

        @Override
        public String getId() {
            return "CropCircleTransformation()";
        }

    }

    /**
     * 暂停所有的请求
     *
     * @param context
     */
    public static void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    public static void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

}
