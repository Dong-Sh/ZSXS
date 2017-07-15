package com.huida.zsxs.utils;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.huida.zsxs.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by lenovo on 2017/7/15.
 */

public class MyBitmapUtils {
    private static String TAG = "MyBitmapUtils";
    private static ImageOptions options = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.duanwang_icon)
            .build();

    private static Callback.CommonCallback<Drawable> commonCallback = new Callback.CacheCallback<Drawable>() {
        @Override
        public boolean onCache(Drawable result) {
            return false;
        }

        @Override
        public void onSuccess(Drawable result) {
            Log.d(TAG, "onSuccess: "+result);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            Log.d(TAG, "onError: "+ex);
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }
    };

    public static void display(String url, ImageView imageView){

        x.image().bind(imageView,url,options,commonCallback);

    }
}
