package com.smartman.myroadrecord.business.mcc;

import com.smartman.base.http.HttpUtil;
import com.smartman.base.utils.ServerUtil;
import com.smartman.myroadrecord.business.mcc.bean.MccInfoReturnBean;

import org.xutils.http.RequestParams;

/**
 * @author jiahui.chen
 * @Description: 获取MCC信息
 * @date 2015年8月24日 15:37:00
 * @copyright TCL-MIE
 */
public class MccInfoMgmt {


    /**
     * @return MccInfoBean
     * @Description: 获取Mcc信息
     */
    public MccInfoReturnBean getMccInfo(String mcc) {
        String uri = ServerUtil.getServerUrl();
        uri += ServerUtil.getValue("MccInfo");
        RequestParams params = new RequestParams(uri);
        params.addQueryStringParameter("mcc", mcc);
        return HttpUtil.doGetSync(params, MccInfoReturnBean.class);
    }


}
