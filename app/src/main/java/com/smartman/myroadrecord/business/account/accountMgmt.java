package com.smartman.myroadrecord.business.account;

import com.smartman.base.http.HttpUtil;
import com.smartman.base.task.TaskException;
import com.smartman.base.utils.ServerUtil;
import com.smartman.myroadrecord.business.account.bean.AccountBean;
import com.smartman.myroadrecord.business.account.bean.AccountReturnBean;

import org.xutils.http.RequestParams;

import java.io.File;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class accountMgmt {

    /**
     * @return AccountBean
     * @Description: 注册
     */
    public String uploadRegisterInfo(AccountBean bean) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("uploadRegisterInfo");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("id", bean.id);
        params.addQueryStringParameter("password", bean.password);
        return HttpUtil.doGetSync(params, String.class);
    }

    /**
     * @return AccountBean
     * @Description: 检查手机号码是否存在
     */
    public Boolean checkPhoneInfo(String phone) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("checkPhoneInfo");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("phone", phone);
        return HttpUtil.doGetSync(params, Boolean.class);
    }

    /**
     * @return AccountBean
     * @Description: 登录
     */
    public AccountReturnBean login(AccountBean bean) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("login");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("id", bean.id);
        params.addQueryStringParameter("password", bean.password);
        return HttpUtil.doGetSync(params, AccountReturnBean.class);
    }

    /**
     * @return Boolean
     * @Description: 修改密码
     */
    public Boolean updatePwd(AccountBean bean) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("updatePwd");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("id", bean.id);
        params.addQueryStringParameter("password", bean.password);
        return HttpUtil.doGetSync(params, Boolean.class);
    }

    /**
     * @return Boolean
     * @Description: 修改密码
     */
    public Boolean uploadImg(AccountBean bean) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("uploadImg");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("test", "test");
        params.setMultipart(true);
        params.addBodyParameter("file", new File("/sdcard/test.jpg"), null); // 如果文件没有扩展名, 最好设置contentType参数.
        params.addQueryStringParameter("password", bean.password);
        return HttpUtil.doGetSync(params, Boolean.class);
    }

}
