package com.smartman.myroadrecord.module.map;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.db.converter.ColumnConverter;
import org.xutils.db.sqlite.ColumnDbType;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author chaohao.zhou
 * @Description:
 * @date 2016/2/15 11:07
 * @copyright TCL-MIE
 */
public class BorderColumnConverter implements ColumnConverter<Border> {

    private Type mType = new TypeToken<Border>(){}.getType();
    private Gson mGson = new Gson();

    @Override
    public Border getFieldValue(Cursor cursor, int index) {
        byte[] bytes = cursor.getBlob(index);
        String jsonString = new String(bytes);
        return mGson.fromJson(jsonString, mType);
    }

    @Override
    public Object fieldValue2DbValue(Border fieldValue) {
        String jsonString = mGson.toJson(fieldValue);
        return jsonString.getBytes();
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.BLOB;
    }
}
