package com.smartman.myroadrecord;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import org.xutils.x;

/**
 * Created by jiahui.chen on 2015/12/22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 百度地图在使用 SDK 各组间之前初始化 context 信息
        SDKInitializer.initialize(this);
        //xutils3初始化
        x.Ext.init(this);
    }
}
