package com.smartman.myroadrecord.business.account;

import com.smartman.base.task.TaskException;
import com.smartman.base.http.HttpUtil;
import com.smartman.base.utils.SettingUtil;
import com.smartman.myroadrecord.business.account.bean.AccountBean;

import org.xutils.http.RequestParams;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class accountMgmt {

    /**
     * @return AccountBean
     * @Description: 获取用户信息
     */
    public AccountBean getUserInfo() throws TaskException {
        String uri = SettingUtil.getStringSetting("url_china");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("userID", "18688553035");
        return HttpUtil.doGetSync(params, AccountBean.class);
    }
}
