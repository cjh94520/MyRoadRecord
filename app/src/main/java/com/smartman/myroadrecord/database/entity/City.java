package com.smartman.myroadrecord.database.entity;

import java.io.Serializable;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class City implements Serializable {
    private static final long serialVersionUID = 5023853576178143338L;

    public String cityName;  //城市名
    public int area; //面积，单位平方米
    public String provinceName; //省份名

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
