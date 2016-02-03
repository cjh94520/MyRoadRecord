package com.smartman.myroadrecord.module.map;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.model.LatLng;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @author chaohao.zhou
 * @Description: 定位点信息类
 * @date 2016/2/1 18:56
 * @copyright TCL-MIE
 */
@Table(name = "Location")
public class Location {
    @Column(name = "latitude")
    private double mLatitude; // 坐标点纬度
    @Column(name = "longitude")
    private double mLongitude; // 坐标点经度
    @Column(name = "city")
    private String mCity; // 点所在的城市

    public Location(double latitude, double longitude, String city) {
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mCity = city;
    }

    public Location(LatLng latLng, String city) {
        this(latLng.latitude, latLng.longitude, city);
    }

    public Location(AMapLocation aMapLocation) {
        this(aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation.getCity());
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }
}
