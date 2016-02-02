package com.smartman.myroadrecord.module.account.util;

import android.graphics.Bitmap;

import com.smartman.myroadrecord.base.application.MyApplication;
import com.smartman.myroadrecord.module.account.param.UserConst;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by jiahui.chen on 2016/2/2.
 */
public class UserUtil {

    //获取用户头像路径
    public static String getUserImgPath() {
        return UserConst.ROOT_DIR + MyApplication.getInstance().getPackageName() + UserConst.USER_DIR + UserConst.USER_IMG;
    }

    /**
     * 用户头像转存文件
     *
     * @param bmp
     * @throws IOException
     */
    public static File saveBitmap2file(Bitmap bmp) {
        File file1 = null;
        file1 = new File(UserConst.ROOT_DIR + MyApplication.getInstance().getPackageName() + UserConst.USER_DIR);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        File file2 = new File(UserConst.ROOT_DIR + MyApplication.getInstance().getPackageName() + UserConst.USER_DIR, UserConst.USER_IMG);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file2));
            bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file2;
    }
}
