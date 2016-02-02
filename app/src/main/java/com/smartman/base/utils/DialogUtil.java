package com.smartman.base.utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by jiahui.chen on 2016/1/27.
 * 对话框的类
 */
public class DialogUtil {
    public static ProgressDialog progressDialog;

    public static void showProgressDialog(Activity context, String message) {
        if (context == null) {
            return;
        }
        dismissNormalProgressDialog();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void showProgressDialog(Activity context, String message, boolean cancel) {
        if (context == null) {
            return;
        }
        dismissNormalProgressDialog();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(cancel);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void updateNormalProgressDialog(String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        }
    }

    public static void dismissNormalProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
            }
            progressDialog = null;
        }
    }
}
