package com.smartman.myroadrecord.module.map;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.smartman.base.activity.BaseActivity;
import com.smartman.myroadrecord.R;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO 获取数据的方式。。线程 + AsyncTask 看你的记性吧。。
public class MapActivity extends BaseActivity implements AMapLocationListener {
    private static final String TAG = MapActivity.class.getSimpleName();
    // 地图相关
    private MapView mMapView;
    private AMap mAMap;
    private TileProvider tileProvider;
    private TileOverlayOptions overlayOptions;
    private TileOverlay tileOverlay;

    // 定位相关
    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;

    private final int mEraserSize = 256; // 模糊Tile的长和宽
    private EraserFactory mEraserFactory;

    GestureDetector gestureDetector;

    // 临时数据
    private int zoom = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // 初始化地图
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 必须要写
        mAMap = mMapView.getMap();
        mAMap.setMapType(AMap.MAP_TYPE_SATELLITE); // 设置成卫星地图
        // 初始化定位相关
        initLocation();

        // TODO 临时定位，之后需要使用定位来确定初始位置。或许需要使用线程在进应用的时候就进行定位
        mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TestData.XLLatLon1, zoom));

        gestureDetector = new GestureDetector(this, new CustomGestureDetector());

        mAMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {

            @Override
            public void onTouch(MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
            }
        });

        mEraserFactory = EraserFactory.getInstance();
        tileProvider = new EraserTileProvider();

        // TODO 缓存大小看是否需要改变，因为这个缓存中大多缓存都是没用的，只有有路径的Tile才是有用的
        // TODO 通过remove 和 add tileOverlay 来实现刷新界面。
        overlayOptions = new TileOverlayOptions().tileProvider(tileProvider).memoryCacheEnabled(true).memCacheSize(10 * 1024);
        tileOverlay = mAMap.addTileOverlay(overlayOptions);
    }

    private void initLocation() {
        mAMap.setMyLocationEnabled(true);
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE); // 设为单纯定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy); // 高精度模式
//        mLocationOption.setGpsFirst(true); // GPS优先
        mLocationOption.setNeedAddress(false); // 不需要地址信息，省流量
        mLocationOption.setInterval(20000);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) { // 主线程。。。。
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                Log.d(TAG, "-->::LocationType = " + aMapLocation.getLocationType());
                Log.d(TAG, "-->::Latitude = " + aMapLocation.getLatitude());
                Log.d(TAG, "-->::Longitude = " + aMapLocation.getLongitude());
                SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                Log.d(TAG, "-->::Time = " + df.format(date));
            } else {
                Log.d(TAG, "-->::eee" + "\nErrorCode = " + aMapLocation.getErrorCode() + "\nErrorInfo = " + aMapLocation.getErrorInfo());
            }
        }

    }

    /**
     * 模糊Tile提供者
     */
    private class EraserTileProvider implements TileProvider {

        @Override
        public Tile getTile(int x, int y, int z) {
            if (x >= 0 && y >= 0) {
//                String[] tileX = Double.toString(Converter.lng2tilex(TestData.XLLatLon1.longitude, z)).split("\\.");
//                String[] tileY = Double.toString(Converter.lat2tiley(TestData.XLLatLon1.latitude, z)).split("\\.");
//
//                if (x == Integer.parseInt(tileX[0]) && y == Integer.parseInt(tileY[0])) {
////                    return new Tile(mEraserSize, mEraserSize, mEraserFactory.getEraserBytes(Double.parseDouble("0." + tileX[1]), Double.parseDouble("0." + tileY[1])));
                    return new Tile(mEraserSize, mEraserSize, mEraserFactory.getEraserBytes(x, y, z));
//                }
            }
            return null;
        }

        @Override
        public int getTileWidth() {
            return mEraserSize;
        }

        @Override
        public int getTileHeight() {
            return mEraserSize;
        }
    }

    /**
     * 自定义手势观察者，由于缓解高德地图Tile由于某些特定手势加载不出来的问题
     */
    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            LatLng centerLatLng = mAMap.getCameraPosition().target;
            mAMap.animateCamera(CameraUpdateFactory.changeLatLng(centerLatLng));
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            LatLng centerLatLng = mAMap.getCameraPosition().target;
            mAMap.animateCamera(CameraUpdateFactory.changeLatLng(centerLatLng));
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            LatLng centerLatLng = mAMap.getCameraPosition().target;
            mAMap.animateCamera(CameraUpdateFactory.changeLatLng(centerLatLng));
            return super.onDoubleTapEvent(e);
        }
    }
}
