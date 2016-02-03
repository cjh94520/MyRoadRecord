package com.smartman.myroadrecord.business.account;

import com.smartman.base.http.HttpUtil;
import com.smartman.base.task.TaskException;
import com.smartman.base.utils.PrefsUtil;
import com.smartman.base.utils.ServerUtil;
import com.smartman.myroadrecord.business.account.bean.AccountBean;
import com.smartman.myroadrecord.business.account.bean.AccountReturnBean;
import com.smartman.myroadrecord.module.account.param.UserConst;
import com.smartman.myroadrecord.module.account.util.UserUtil;

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
    public Boolean checkPhoneInfo(String id) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("checkPhoneInfo");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("id", id);
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
     * @Description: 上传用户头像
     */
    public Boolean uploadImg(String path) throws TaskException {
        String address = ServerUtil.getServerUrl();
        address += ServerUtil.getValue("uploadImg");
        RequestParams params = new RequestParams(address);
        String name = PrefsUtil.loadPrefString(UserConst.USER_NAME, "未登录");
        params.addQueryStringParameter("id", name);
        params.addBodyParameter("file", new File(path), null); // 如果文件没有扩展名, 最好设置contentType参数.
        return HttpUtil.uploadFile(params, Boolean.class);
    }

    /**
     * @return Boolean
     * @Description: 下载用户头像
     */
    public File downloadImg(String id) throws TaskException {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("downloadImg");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("id", id);
        return HttpUtil.downloadFile(params, UserUtil.getUserImgPath());
    }

}
