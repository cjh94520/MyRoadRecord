package com.smartman.myroadrecord.base.application;

import com.smartman.base.application.XUtilApplication;
import com.smartman.base.utils.PrefsUtil;
import com.smartman.myroadrecord.module.map.fragment.ArrayListColumnConverter;

import org.xutils.db.converter.ColumnConverterFactory;

import java.util.ArrayList;

/**
 * Created by jiahui.chen on 2015/12/22.
 */
public class MyApplication extends XUtilApplication {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        PrefsUtil.init(this);
        initDataBaseColumnConverter();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    private void initDataBaseColumnConverter() {
        ColumnConverterFactory.registerColumnConverter(ArrayList.class, new ArrayListColumnConverter());
    }
}
