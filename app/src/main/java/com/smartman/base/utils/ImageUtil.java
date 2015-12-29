package com.smartman.base.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class ImageUtil {

    /**
     * // Internet
     * x.image().bind(imageView, url, imageOptions);
     * <p>
     * // assets file
     * x.image().bind(imageView, "assets://test.gif", imageOptions);
     * <p>
     * // local file
     * x.image().bind(imageView, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
     * x.image().bind(imageView, "/sdcard/test.gif", imageOptions);
     * x.image().bind(imageView, "file:///sdcard/test.gif", imageOptions);
     * x.image().bind(imageView, "file:/sdcard/test.gif", imageOptions);
     */
    public static void displayImage(ImageView imageView, String url) {
        x.image().bind(imageView, url);
    }

    public static void displayImage(ImageView imageView, String url, ImageOptions options) {
        x.image().bind(imageView, url, options);
    }

    public static void displayImage(ImageView imageView, String url, ImageOptions options, Callback.CommonCallback<Drawable> callback) {
        x.image().bind(imageView, url, options, callback);
    }

    public static Callback.Cancelable loadDrawable(String url, ImageOptions options, Callback.CommonCallback<Drawable> callback) {
        return x.image().loadDrawable(url, options, callback);
    }

    public static Callback.Cancelable loadFile(String url, ImageOptions options, Callback.CommonCallback<File> callback) {
        return x.image().loadFile(url, options, callback);
    }

    public static void clearMemoryCache() {
        x.image().clearMemCache();
    }

    public static void clearDiskCache() {
        x.image().clearCacheFiles();
    }

}
