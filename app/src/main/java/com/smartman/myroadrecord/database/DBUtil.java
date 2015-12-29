package com.smartman.myroadrecord.database;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class DBUtil {
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("test")
            .setDbDir(new File("/sdcard"))
            .setDbVersion(1)
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // db.addColumn(...);
                    // db.dropTable(...);
                    // ...
                }
            });

}
