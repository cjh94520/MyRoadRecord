package com.smartman.myroadrecord.business.mcc.bean;

import java.io.Serializable;


/**
 * Created by jiahui.chen on 2015/8/24.
 */
public class MccInfoBean implements Serializable {

    private static final long serialVersionUID = 4913346482428729389L;

    public String countryCode;

    public String countryMcc;

    public String countryName;
    public String countryNameCn;
    public int createdBy;
    public long createdOn;
    public Boolean fortumoEnable;
    public int id;
    public String language;
    public int lastChangedBy;
    public long lastChangedOn;

    public int regionId;

    @Override
    public String toString() {
        return "MccInfoBean{" +
                "countryCode='" + countryCode + '\'' +
                ", countryMcc='" + countryMcc + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryNameCn='" + countryNameCn + '\'' +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", fortumoEnable=" + fortumoEnable +
                ", id=" + id +
                ", language='" + language + '\'' +
                ", lastChangedBy=" + lastChangedBy +
                ", lastChangedOn=" + lastChangedOn +
                ", regionId=" + regionId +
                '}';
    }
}
