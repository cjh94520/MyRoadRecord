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
        // 百度地图在使用 SDK 各组间之前初始化 context 信息
        //SDKInitializer.initialize(this);
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
