package com.smartman.myroadrecord.database.entity;

import java.io.Serializable;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class Province implements Serializable {
    private static final long serialVersionUID = -3175850052918689628L;

    public String provinceName;  //省份名
    public int area;  //面积，单位平方米
    public int cityNum;  //城市个数

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCityNum() {
        return cityNum;
    }

    public void setCityNum(int cityNum) {
        this.cityNum = cityNum;
    }
}
