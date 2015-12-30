package com.smartman.myroadrecord.database.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
@Table(name = "Province")
public class Province implements Serializable {
    private static final long serialVersionUID = -3175850052918689628L;

    @Column(name = "provinceName", isId = true)
    public String provinceName;  //省份名

    @Column(name = "area")
    public int area;  //面积，单位平方公里

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

    @Override
    public String toString() {
        return "Province{" +
                "provinceName='" + provinceName + '\'' +
                ", area=" + area +
                ", cityNum=" + cityNum +
                '}';
    }
}
