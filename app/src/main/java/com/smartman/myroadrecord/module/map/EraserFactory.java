package com.smartman.myroadrecord.module.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;

import com.amap.api.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author chaohao.zhou
 * @Description: 模糊图片、路径图片生成器
 * @date 2016/1/18 13:56
 * @copyright TCL-MIE
 */
public final class EraserFactory {

    private final static String TAG = EraserFactory.class.getSimpleName();

    // 生成模糊图片的长和宽
    private final int mBitmapSize = 256;

    // 模糊的颜色
    private final int mColor = Color.argb(200, 255, 255, 255);

    // 路径的宽度 用数组来存储zoom值为3~19的路径的宽度
    private final int[] mPathWidths = {
            40, //0
            40, //1
            5, //2
            5, //3
            5, //4
            5, //5
            5, //6
            5, //7
            5, //8
            5, //9
            5, //10
            5, //11
            8, //12
            8, //13
            11, //14 可以再减1~2 现在还可以
            13, //15
            15, //16
            20, //17
            30, //18
            40 //19
    };

    // 绘制路径的画笔
    private Paint mPathPaint;

    private final static EraserFactory mInstance = new EraserFactory();

    private EraserFactory() {
        initPathPaint();
    }

    public static EraserFactory getInstance() {
        return mInstance;
    }

    /**
     * 初始化路径画笔，使用DST_OUT模式
     */
    private void initPathPaint() {
        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setDither(true);
        mPathPaint.setColor(0xFFFFFFFF);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeJoin(Paint.Join.ROUND);
        mPathPaint.setStrokeCap(Paint.Cap.ROUND);
        mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    // TODO 看是否需要使用缓存缓存好画好的没有修改的图片。
    // TODO 只有单个点的情况
    // TODO 路线组合添加bound 的字段，用于判断时候在bound里面
    public byte[] getEraserBytes(int x, int y, int z) {
        // 路径
        Path path = new Path();
        boolean hasReset = true;
        boolean ignoreBound = false;

        // 设定路径宽度
        mPathPaint.setStrokeWidth(mPathWidths[z]);

        // 绘制模糊图片
        Bitmap bitmap = Bitmap.createBitmap(mBitmapSize, mBitmapSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(mColor);

        // 获取边界值，获取到的边界值是会比Tile大，用于点在另外一个Tile，但可能会画在该Tile的情况。
        double northwestLat = Converter.tiley2lat(y - (mPathWidths[z] >> 1) / 256.0, z);
        double northwestLng = Converter.tilex2lng(x - (mPathWidths[z] >> 1) / 256.0, z);
        double southeastLat = Converter.tiley2lat(y + 1 + (mPathWidths[z] >> 1) / 256.0, z);
        double southeastLng = Converter.tilex2lng(x + 1 + (mPathWidths[z] >> 1) / 256.0, z);

        // 绘制路线
        for (int i = 0; i < TestData.XLB708LatLngs.size(); i++) {
            LatLng latLng = TestData.XLB708LatLngs.get(i);
            // 计算点所在的TileX 和 TileY
            double tileX = Converter.lng2tilex(latLng.longitude, z);
            double tileY = Converter.lat2tiley(latLng.latitude, z);

            // 计算点在EraserBitmap的位置
            double widthScale = tileX - x;
            double heightScale = tileY - y;

            if (i == 0) {
                path.moveTo((float) (mBitmapSize * widthScale), (float) (mBitmapSize * heightScale));
            } else {
                path.lineTo((float) (mBitmapSize * widthScale), (float) (mBitmapSize * heightScale));
            }

//            if (northwestLat > latLng.latitude && latLng.latitude > southeastLat && northwestLng < latLng.longitude
//                    && latLng.longitude < southeastLng) { // 点在边界内
//
//                ignoreBound = true;
//
//                // 计算点所在的TileX 和 TileY
//                double tileX = Converter.lng2tilex(latLng.longitude, z);
//                double tileY = Converter.lat2tiley(latLng.latitude, z);
//
//                // 计算点在EraserBitmap的位置
//                double widthScale = tileX - x;
//                double heightScale = tileY - y;
//
//                if (hasReset) {
//                    hasReset = false;
//                    if (i != 0) {
//                        LatLng latLng_pre = TestData.XLB708LatLngs.get(i - 1);
//
//                        // 计算点所在的TileX 和 TileY
//                        double tileX_pre = Converter.lng2tilex(latLng_pre.longitude, z);
//                        double tileY_pre = Converter.lat2tiley(latLng_pre.latitude, z);
//
//                        // 计算点在EraserBitmap的位置
//                        double widthScale_pre = tileX_pre - x;
//                        double heightScale_pre = tileY_pre - y;
//
//                        path.moveTo((float) (mBitmapSize * widthScale_pre), (float) (mBitmapSize * heightScale_pre));
//                        path.lineTo((float) (mBitmapSize * widthScale), (float) (mBitmapSize * heightScale));
//                    } else {
//                        path.moveTo((float) (mBitmapSize * widthScale), (float) (mBitmapSize * heightScale));
//                    }
//                } else {
//                    path.lineTo((float) (mBitmapSize * widthScale), (float) (mBitmapSize * heightScale));
//                }
//            } else if (ignoreBound) {
//                // 计算点所在的TileX 和 TileY
//                double tileX = Converter.lng2tilex(latLng.longitude, z);
//                double tileY = Converter.lat2tiley(latLng.latitude, z);
//
//                // 计算点在EraserBitmap的位置
//                double widthScale = tileX - x;
//                double heightScale = tileY - y;
//
//                path.lineTo((float) (mBitmapSize * widthScale), (float) (mBitmapSize * heightScale));
//                canvas.drawPath(path, mPathPaint);
//                path.reset();
//                hasReset = true;
//                ignoreBound = false;
//            }
        }
        canvas.drawPath(path, mPathPaint);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] eraserBytes = outputStream.toByteArray();
        bitmap.recycle();
        try {
            outputStream.flush();
        } catch (IOException e) {
            Log.d(TAG, "-->::eee " + e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    Log.d(TAG, "-->::eee " + e);
                }
            }
        }
        return eraserBytes;
    }
}
