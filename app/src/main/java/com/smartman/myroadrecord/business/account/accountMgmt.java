package com.smartman.myroadrecord.business.account;

import com.smartman.base.http.HttpUtil;
import com.smartman.base.task.TaskException;
import com.smartman.base.utils.ServerUtil;
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
    public String uploadRegisterInfo(AccountBean bean) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("uploadRegisterInfo");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("userID", bean.id);
        params.addQueryStringParameter("userPwd", bean.password);
        return HttpUtil.doGetSync(params, String.class);
    }
}
