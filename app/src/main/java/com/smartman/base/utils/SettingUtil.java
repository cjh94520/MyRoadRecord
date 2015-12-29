package com.smartman.base.utils;

import com.smartman.base.setting.SettingBean;
import com.smartman.myroadrecord.base.application.MyApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class SettingUtil {
    private static Map<String, SettingBean> settingMap;

    static {
        settingMap = new HashMap<String, SettingBean>();
        addSettings(File.CONFIG_ACTIONS);
    }

    private SettingUtil() {

    }

    public static class File {
        // action、url的配置文件名
        public static final String CONFIG_ACTIONS = "actions";
    }

    /**
     * 添加设置配置数据
     *
     * @param settingsXmlName
     */
    public static void addSettings(String settingsXmlName) {
        settingMap = SettingsXmlParser.parseSettings(MyApplication.getInstance(), settingsXmlName);
    }

    public static String getStringSetting(String type) {
        if (settingMap.containsKey(type))
            return settingMap.get(type).getValue();

        return null;
    }
}
