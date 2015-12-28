package com.smartman.myroadrecord.utils;

import com.smartman.myroadrecord.MyApplication;

/**
 * Created by jiahui.chen on 2015/12/28.
 */
public class ResourceUtil {
    /**
     * 获取本地资源字符串
     *
     * @param resId
     */
    public static final String getString(int resId) {
        MyApplication myApplication = MyApplication.getInstance();
        if (myApplication == null || myApplication.getResources() == null) {
            return "";
        }
        String string = "";
        try {
            string = myApplication.getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }
}
