package com.smartman.myroadrecord.module.map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.database.DBUtil;

import org.xutils.DbManager;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chaohao.zhou
 * @Description:
 * @date 2016/2/2 14:53
 * @copyright TCL-MIE
 */
public class TestActivity extends Activity {

    private final static String TAG = "TestActivity";

    private List<Route> routes;

    private DbManager mDbManager;

    private static Location location0 = new Location(0, 0, "广州");
    private static Location location1 = new Location(50, 50, "上海");
    private static Location location2 = new Location(-50, -50, "沙发");
    private static Location location3 = new Location(50, -50, "地板");
    private static Location location4 = new Location(-50, 50, "天台");

    private List<Location> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mDbManager = x.getDb(DBUtil.daoConfig);
    }

    public void onButtonClick(View view) throws Exception {
        switch (view.getId()) {
            case R.id.button1:
                Route route = new Route();
                route.addLocation(location0);
                mDbManager.save(route);
                break;
            case R.id.button2:
                List<Route> route1 = mDbManager.findAll(Route.class);
                for (Route temp : route1) {
                    Log.d(TAG, "-->::" + temp.toString());
                }
                break;
            case R.id.button3:
                Log.d(TAG, "-->::" + locations.toString());
                break;
            case R.id.button4:
                mDbManager.dropTable(Route.class);
                break;
            default:
                break;
        }
    }



}
