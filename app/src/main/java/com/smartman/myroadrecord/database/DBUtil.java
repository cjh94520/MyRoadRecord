package com.smartman.myroadrecord.database;

import com.smartman.base.utils.AssetUtil;
import com.smartman.myroadrecord.database.entity.Province;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class DBUtil {
    public static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("data")
            //没有效果啊改名字.setDbDir(new File("/aaaaaaaa"))
            .setDbVersion(1)
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // db.addColumn(...);
                    // db.dropTable(...);
                    // ...
                }
            });

    //安排放在Splash初始化加载
    public static void initProvince() {
        DbManager db = x.getDb(daoConfig);
        String data = AssetUtil.getFromAssets("province.txt");
        String[] list = data.split(";");
        List<Province> provinceList = new ArrayList<>();
        Province province;
        for (String s : list) {
            province = new Province();
            province.provinceName = s.split(",")[0];
            province.area = Integer.valueOf(s.split(",")[1]);
            provinceList.add(province);
        }
        try {
            db.save(provinceList);
        } catch (DbException e) {
            e.printStackTrace();
        }
        LogUtil.d(data);
    }

}
