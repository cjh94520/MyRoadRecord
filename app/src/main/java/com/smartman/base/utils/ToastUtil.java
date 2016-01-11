package com.smartman.base.utils;

import android.widget.Toast;

import com.smartman.myroadrecord.base.application.MyApplication;

/**
 * Created by jiahui.chen on 2016/1/11.
 */
public class ToastUtil {
    public static void showMessage(CharSequence msg) {
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(int msgId) {
        showMessage(MyApplication.getInstance().getResources().getString(msgId));
    }
}
