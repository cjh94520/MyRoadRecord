package com.smartman.myroadrecord.business.account.bean;

import com.smartman.base.http.FastJsonResponseParser;
import com.smartman.myroadrecord.business.BaseBean;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by jiahui.chen on 2016/1/24.
 */
@HttpResponse(parser = FastJsonResponseParser.class)
public class AccountReturnBean extends BaseBean {
    public AccountBean data;

    @Override
    public String toString() {
        return "AccountReturnBean{" +
                "data=" + data +
                '}';
    }
}
