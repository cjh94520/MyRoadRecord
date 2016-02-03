package com.smartman.myroadrecord.module.map;

/**
 * @author chaohao.zhou
 * @Description: 转换工具
 * @date 2016/1/19 11:21
 * @copyright TCL-MIE
 */
public final class Converter {

    /**
     * 私有构造函数
     */
    private Converter() {
    }

    /**
     * @param x Tile的x坐标
     * @param z 地图的zoom值
     * @return 对应Tile的Left所在的经度
     */
    public static double tilex2lng(double x, int z) {
        return x / Math.pow(2.0, z) * 360.0 - 180.0;
    }

    /**
     * @param y Tile的y坐标
     * @param z 地图的zoom值
     * @return 对应Tile的Top所在的纬度
     */
    public static double tiley2lat(double y, int z) {
        double n = Math.PI - 2.0 * Math.PI * y / Math.pow(2, z);
        return 180.0 / Math.PI * Math.atan(0.5 * (Math.exp(n) - Math.exp(-n)));
    }

    /**
     * @param lng 经度
     * @param z   地图的zoom值
     * @return 经度对应Tile的x值
     */
    public static double lng2tilex(double lng, int z) {
        return (lng + 180) / 360 * (1 << z);
    }

    /**
     * @param lat 纬度
     * @param z   地图的zoom值
     * @return 纬度对应Tile的y值
     */
    public static double lat2tiley(double lat, int z) {
        return (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1
                << z);
    }
}
