package com.smartman.myroadrecord.base.application;

import com.smartman.base.application.XUtilApplication;

/**
 * Created by jiahui.chen on 2015/12/22.
 */
public class MyApplication extends XUtilApplication {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
