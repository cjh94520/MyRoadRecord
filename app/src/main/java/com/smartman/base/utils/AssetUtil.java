package com.smartman.base.utils;

import com.smartman.myroadrecord.base.application.MyApplication;

import java.io.InputStream;

/**
 * Created by jiahui.chen on 2015/12/29.
 * 从Assets中获取各种资源
 */
public class AssetUtil {
    public static String getFromAssets(String fileName) {
        MyApplication myApplication = MyApplication.getInstance();
        if (myApplication == null || myApplication.getResources() == null) {
            return "";
        }
        String ENCODING = "UTF-8";
        String result = "";
        try {
            InputStream in = myApplication.getResources().getAssets().open(fileName);
            //获取文件的字节数
            int lenght = in.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据读到byte数组中
            in.read(buffer);
            result = new String(buffer, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
