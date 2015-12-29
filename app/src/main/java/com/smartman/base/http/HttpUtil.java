package com.smartman.base.http;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class HttpUtil {
    public static <T> T doGetSync(RequestParams params, Class<T> responseCls) {
        try {
            return x.http().getSync(params, responseCls);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public static void doGet(RequestParams params) {
        x.http().get(params, new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static <T> T doPostSync(RequestParams params, Class<T> responseCls) {
        try {
            return x.http().postSync(params, responseCls);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public static void doPost(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}
