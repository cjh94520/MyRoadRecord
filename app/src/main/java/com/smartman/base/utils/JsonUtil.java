package com.smartman.base.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class JsonUtil {
    public static void Convert2Json() {
        try {
            JSONObject object = new JSONObject();

            JSONArray jsonMembers = new JSONArray();
            JSONObject member1 = new JSONObject();
            member1.put("provinceName", "广东");

            member1.put("area", "177084");
            jsonMembers.put(member1);

            JSONObject member2 = new JSONObject();
            member2.put("provinceName", "新疆");
            member2.put("area", "1633280");

            jsonMembers.put(member2);
            object.put("users", jsonMembers);
            LogUtil.d(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
