package com.smartman.myroadrecord.module.map;

/**
 * @author chaohao.zhou
 * @Description: 边界类，每一条路线都有一个边界对象
 * @date 2016/2/15 11:04
 * @copyright TCL-MIE
 */
public class Border {
    /**
     * 下
     */
    public double mMinLat = 90;

    /**
     * 上
     */
    public double mMaxLat = -90;

    /**
     * 左
     */
    public double mMinLng = 180;

    /**
     * 右
     */
    public double mMaxLng = -180;

    /**
     * @param lat 纬度
     * @param lng 经度
     * @return 参数指定的经纬度是否在该边界内
     */
    public boolean isInBorder(double lat, double lng) {
        return mMinLat < lat && lat < mMaxLat && mMinLng < lng && lng < mMaxLng;
    }

    /**
     * 修改边界经纬度的极值
     *
     * @param lat 纬度
     * @param lng 经度
     */
    public void modifyBorderIfNeed(double lat, double lng) {
        if (mMinLat > lat) {
            mMinLat = lat;
        }

        if (mMaxLat < lat) {
            mMaxLat = lat;
        }

        if (mMinLng > lng) {
            mMinLng = lng;
        }

        if (mMaxLng < lng) {
            mMaxLng = lng;
        }
    }
}
