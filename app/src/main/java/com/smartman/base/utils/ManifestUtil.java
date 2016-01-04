package com.smartman.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class ManifestUtil {
    public static String getMetaData(Context context, String name) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        Object value = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                value = applicationInfo.metaData.get(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        return value == null ? null : value.toString();
    }
}
