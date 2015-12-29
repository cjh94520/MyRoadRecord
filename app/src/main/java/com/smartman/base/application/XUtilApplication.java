package com.smartman.base.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class XUtilApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //xutils3初始化
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
