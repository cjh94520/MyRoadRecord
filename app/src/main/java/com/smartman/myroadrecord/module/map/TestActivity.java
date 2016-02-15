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
        initData();
    }

    public void onButtonClick(View view) throws Exception {
        switch (view.getId()) {
            case R.id.button1:
                initData();
                mDbManager.save(routes);
                break;
            case R.id.button2:
                routes = mDbManager.findAll(Route.class);
                for (Route temp : routes) {
                    Log.d(TAG, "-->::" + temp.toString());
                }
                break;
            case R.id.button3:
                Route route = routes.get(2);
                route.addLocation(location0);
                mDbManager.saveOrUpdate(routes);
                break;
            case R.id.button4:
                mDbManager.dropTable(Route.class);
                break;
            default:
                break;
        }
    }

    private void initData() {
        routes = new ArrayList<Route>();
        Route route = new Route();
        route.addLocation(location0);
        route.addLocation(location1);
        routes.add(route);
        route = new Route();
        route.addLocation(location2);
        route.addLocation(location3);
        routes.add(route);
        route = new Route();
        route.addLocation(location4);
        routes.add(route);
    }

}
