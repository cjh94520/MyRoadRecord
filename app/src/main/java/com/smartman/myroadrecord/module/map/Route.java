package com.smartman.myroadrecord.module.map;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chaohao.zhou
 * @Description: 路线类
 * @date 2016/1/25 11:14
 * @copyright TCL-MIE
 */
@Table(name = "Route")
public class Route {

    @Column(name = "id", isId = true, autoGen = true)
    public int id;

    /**
     * 路线点列表
     */
    @Column(name = "locations")
    public ArrayList<Location> mLocations;

    /**
     * 路线边界
     */
    @Column(name = "border")
    public Border mBorder;

    public Route() {
        mLocations = new ArrayList<>();
        mBorder = new Border();
    }

    /**
     * 将定位信息插入到路线点列表中，并修改相应的边界值
     *
     * @param location 定位点信息
     */
    public void addLocation(Location location) {
        mLocations.add(location);
        mBorder.modifyBorderIfNeed(location.getLatitude(), location.getLongitude());
    }

    /**
     * @param lat 纬度
     * @param lng 经度
     * @return 参数指定的经纬度是否在该边界内，如果路线点的数量为0，返回false
     */
    public boolean isInBorder(double lat, double lng) {
        return mLocations.size() == 0 ? false : mBorder.isInBorder(lat, lng);
    }

    /**
     * TODO 测试用的。。。。
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("locations:\n");
        for (Location location : mLocations) {
            stringBuilder.append("latitude: " + location.getLatitude() + " lng: " + location.getLongitude() + "\n");
        }
        stringBuilder.append("border: \n" + "上: " + mBorder.mMaxLat + "下: " + mBorder.mMinLat + "左: " + mBorder
                .mMinLng + "右: " + mBorder.mMaxLng);
        return stringBuilder.toString();
    }

}
