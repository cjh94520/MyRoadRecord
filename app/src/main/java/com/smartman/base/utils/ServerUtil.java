package com.smartman.base.utils;

import com.smartman.myroadrecord.base.application.MyApplication;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class ServerUtil {
    static final String DOMAIN_NORMAL = "Normal";
    static final String DOMAIN_TEST = "Test";

    /**
     * 获取服务端地址，如果不配置走测试服务地址,基础服务器地址分为国外，国内以及测试
     *
     * @param
     * @return
     */
    public static String getServerUrl() {
        String defaultDomain = ManifestUtil.getMetaData(MyApplication.getInstance(), "DOMAIN_VERSION");
        if (defaultDomain.equalsIgnoreCase(DOMAIN_NORMAL)) {
            return SettingUtil.getStringSetting("url_china");
        } else if (defaultDomain.equalsIgnoreCase(DOMAIN_TEST)) {
            return SettingUtil.getStringSetting("url_test");
        }
        return SettingUtil.getStringSetting("url_test");
    }

    public static String getValue(String type)
    {
        return SettingUtil.getStringSetting(type);
    }
}
