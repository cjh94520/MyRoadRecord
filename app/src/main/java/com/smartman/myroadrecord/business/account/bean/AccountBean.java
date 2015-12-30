package com.smartman.myroadrecord.business.account.bean;

import com.smartman.myroadrecord.business.BaseBean;

/**
 * Created by jiahui.chen on 2015/12/30.
 */
public class AccountBean extends BaseBean {
    private static final long serialVersionUID = -7419721349288976885L;

    public String id;

    public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}