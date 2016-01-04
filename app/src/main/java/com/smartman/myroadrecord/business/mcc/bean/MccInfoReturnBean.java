package com.smartman.myroadrecord.business.mcc.bean;


import com.smartman.base.http.JsonResponseParser;
import com.smartman.myroadrecord.business.BaseBean;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by jiahui.chen on 2015/8/24.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class MccInfoReturnBean extends BaseBean {

    private static final long serialVersionUID = -5551449689288109496L;
    public MccInfoBean data;

    @Override
    public String toString() {
        return "MccInfoReturnBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
