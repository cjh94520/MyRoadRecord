package com.smartman.myroadrecord.business.account;

import com.smartman.base.exception.TaskException;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class accountMgmt {

    private String getUserInfoURI()
    {
        return "http://www.baidu.com";
    }
    /**
     * @return AccountBean
     * @Description: 获取用户信息
     */
    public void getUserInfo() throws TaskException {
        String uri = getUserInfoURI();
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("userID","18688553035");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }
}
