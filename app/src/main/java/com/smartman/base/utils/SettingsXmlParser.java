package com.smartman.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Xml;

import com.smartman.base.setting.SettingBean;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class SettingsXmlParser {
    private SettingsXmlParser() {
    }

    static Map<String, SettingBean> parseSettings(Context context, String fileName) {
        Map<String, SettingBean> settingMap = new HashMap<String, SettingBean>();
        SettingBean bean = null;
        XmlPullParser xmlResParser = null;
        try {
            String packageName = context.getPackageName();
            Resources resources = context.getPackageManager().getResourcesForApplication(packageName);
            int resId = resources.getIdentifier(fileName, "raw", packageName);
            // 解析URL配置
            xmlResParser = Xml.newPullParser();
            xmlResParser.setInput(resources.openRawResource(resId), "utf-8");
            int eventType = xmlResParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("setting".equals(xmlResParser.getName())) {
                            bean = new SettingBean();
                            bean.setType(xmlResParser.getAttributeValue(null, "type"));
                        }
                        if ("des".equals(xmlResParser.getName())) {
                            bean.setDescription(xmlResParser.nextText());
                        }
                        if ("value".equals(xmlResParser.getName())) {
                            bean.setValue(xmlResParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("setting".equals(xmlResParser.getName())) {
                            if (bean != null) {
                                settingMap.put(bean.getType(), bean);
                            }
                        }
                }
                eventType = xmlResParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settingMap;
    }

}
