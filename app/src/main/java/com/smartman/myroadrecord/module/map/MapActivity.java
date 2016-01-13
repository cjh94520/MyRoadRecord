package com.smartman.myroadrecord.module.map;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.smartman.base.activity.BaseActivity;
import com.smartman.base.ui.ImageEraser;
import com.smartman.myroadrecord.R;

public class MapActivity extends BaseActivity {
    private static final String TAG = MapActivity.class.getSimpleName();
    // 地图相关
    private MapView mapView;
    private AMap aMap;


    private ImageEraser mFloatImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // 初始化地图
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        aMap = mapView.getMap();


//        mMapView = (TextureMapView) findViewById(R.id.map);
//        mMapView.showScaleControl(false);
//        mMapView.showZoomControls(false);
//        mBaiduMap = mMapView.getMap();//113.30765,23.120049
//        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
//            @Override
//            public void onMapLoaded() {
//                android.graphics.Point point1 = new android.graphics.Point(0, mMapView.getHeight());
//                Log.i(TAG, "Height:" + String.valueOf(mMapView.getHeight()));
//                Projection projection = mBaiduMap.getProjection();
//                if (projection == null) {
//                    Log.i(TAG, "projection is null");
//                    return;
//                }
//                LatLng screenLatLng1 = mBaiduMap.getProjection().fromScreenLocation(point1);
//                Log.i(TAG, "左下角坐标：纬度：" + String.valueOf(screenLatLng1.latitude) + "经度:" + String.valueOf(screenLatLng1.longitude));
//                android.graphics.Point point = new android.graphics.Point(mMapView.getWidth(), 0);
//                Log.i(TAG, "width:" + String.valueOf(mMapView.getWidth()));
//                LatLng screenLatLng = mBaiduMap.getProjection().fromScreenLocation(point);
//                Log.i(TAG, "右上角坐标：纬度：" + String.valueOf(screenLatLng.latitude) + "经度:" + String.valueOf(screenLatLng.longitude));
//            }
//        });

        mFloatImage = new ImageEraser(this);
        mFloatImage.prepare();
        RelativeLayout ly = (RelativeLayout) findViewById(R.id.frontImg);
        ly.addView(mFloatImage);
        //设置中心点
//        LatLng latLng = new LatLng(23.120049, 113.30765);
//        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
//        mBaiduMap.animateMapStatus(update);

        // 界面加载时添加绘制图层
        //addCustomElementsDemo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

}
