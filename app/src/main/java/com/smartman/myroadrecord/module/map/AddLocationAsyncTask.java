package com.smartman.myroadrecord.module.map;

import android.os.AsyncTask;

import com.amap.api.location.AMapLocation;

/**
 * @author chaohao.zhou
 * @Description: 添加坐标点的异步任务，使用AsyncTask默认的顺序执行（避免并发引起的问题），仅仅会使用在定位结果返回的时候，主要功能是分析定位到的结果跟之前所有点的距离的问题，忽略不需要添加的点
 * @date 2016/2/1 16:55
 * @copyright TCL-MIE
 */
public class AddLocationAsyncTask extends AsyncTask<AMapLocation, Void, Void> {

    @Override
    protected Void doInBackground(AMapLocation... params) {
        return null;
    }
}
