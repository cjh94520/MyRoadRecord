package com.smartman.myroadrecord.module.map;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smartman.myroadrecord.module.map.Location;

import org.xutils.db.converter.ColumnConverter;
import org.xutils.db.sqlite.ColumnDbType;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author chaohao.zhou
 * @Description: ArrayList 数据库列类型转换器
 * @date 2016/2/2 17:35
 * @copyright TCL-MIE
 */
public class LocationArrayListColumnConverter implements ColumnConverter<ArrayList<Location>> {

    private Type mType = new TypeToken<ArrayList<Location>>(){}.getType();
    private Gson mGson = new Gson();

    @Override
    public ArrayList<Location> getFieldValue(Cursor cursor, int index) {
        byte[] bytes = cursor.getBlob(index);
        String jsonString = new String(bytes);
        return mGson.fromJson(jsonString, mType);
    }

    @Override
    public Object fieldValue2DbValue(ArrayList<Location> fieldValue) {
        String jsonString = mGson.toJson(fieldValue);
        return jsonString.getBytes();
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.BLOB;
    }
}
