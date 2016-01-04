package com.smartman.myroadrecord;

/**
 * Created by jiahui.chen on 2016/1/4.
 */
public class NDKTest {
    static {
        System.loadLibrary("MyJni");//导入生成的链接库文件
    }
    public native String getStringFromNative();//本地方法
}
