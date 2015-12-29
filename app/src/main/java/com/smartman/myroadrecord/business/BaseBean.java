package com.smartman.myroadrecord.business;

import java.io.Serializable;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 7652079004836119715L;

    public int code;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
