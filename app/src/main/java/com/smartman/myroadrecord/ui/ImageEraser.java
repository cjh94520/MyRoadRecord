package com.smartman.myroadrecord.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.smartman.myroadrecord.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageEraser extends View{

	private RectF 	 mImgDesRect;

	private Bitmap	 mImgBitmap = null;
	private Bitmap   mEraseMaskBitmap = null;

	private Path	 mErasePath = null;
	private Paint	 mErasePaint = null;
	private Canvas	 mCanvas = null;
	private Canvas testCanvas;

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	public ImageEraser(Context context) {
		super(context);
		//decodeBitmap();
//		mImgDesRect = new RectF(0, 0 , getWidth(), getHeight()); //front image position
//		initBmpMask();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mImgDesRect = new RectF(0, 0 , getWidth(), getHeight()); //front image position
		decodeBitmap();
		initBmpMask();
	}

	//decode bitmap
	private boolean decodeBitmap(){
		Resources res = this.getContext().getResources();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, R.drawable.test_img, options);

		if (options.outHeight > 0) {
			int width = 1000;
			int height = 1000;

			if ((width < options.outWidth) || (height < options.outHeight)) {
				int ratio = (options.outWidth > options.outHeight) ? (options.outWidth / width)
						: (options.outHeight / height);
				options.inSampleSize = ratio + 1;
			}

			options.inJustDecodeBounds = false;

			try {
				int w = (int) mImgDesRect.width();
				int h = (int) mImgDesRect.height();
				//mEraseMaskBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
				mImgBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
				//mImgBitmap = BitmapFactory.decodeResource(res, R.drawable.test_img, options);
				testCanvas = new Canvas(mImgBitmap);
				testCanvas.drawARGB(200, 255, 255, 255);
				if (mImgBitmap == null) {
					return false;
				} else {
					return true;
				}
			} catch (Exception e) {
			} finally {
			}
		} else {
			return false;
		}
		return false;
	}

	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		//canvas.drawARGB(195, 255, 255, 255);
		if(mImgBitmap != null)
		{
			Paint paint = new Paint();
//			paint.setFilterBitmap(false);

			int sc = canvas.saveLayer(mImgDesRect.left, mImgDesRect.top, mImgDesRect.right, mImgDesRect.bottom, null,
					Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
							| Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
							| Canvas.FULL_COLOR_LAYER_SAVE_FLAG
							| Canvas.CLIP_TO_LAYER_SAVE_FLAG);
			//  canvas.drawRect(mImgDesRect.left, mImgDesRect.top, mImgDesRect.right, mImgDesRect.top + mImgDesRect.bottom, paint);
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
			canvas.drawBitmap(mImgBitmap, new Rect(0, 0, mImgBitmap.getWidth(), mImgBitmap.getHeight()), mImgDesRect, null);
			canvas.drawBitmap(mEraseMaskBitmap, new Rect(0, 0, mEraseMaskBitmap.getWidth(), mEraseMaskBitmap.getHeight()), mImgDesRect, paint);
			paint.setXfermode(null);
			canvas.restoreToCount(sc);
		}

	}

	private Bitmap combineImgWithMask(){
		if(mImgBitmap != null && mEraseMaskBitmap != null)
		{
			Paint paint = new Paint();
//			paint.setFilterBitmap(false);
			Bitmap mCombinedBmp = Bitmap.createBitmap((int)mImgDesRect.width(), (int)mImgDesRect.height(), Bitmap.Config.ARGB_8888);
			mCombinedBmp.eraseColor(Color.TRANSPARENT);
			Canvas canvas = new Canvas(mCombinedBmp);
			int sc = canvas.saveLayer(0, 0, mImgDesRect.width(), mImgDesRect.height(), null,
					Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
							| Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
							| Canvas.FULL_COLOR_LAYER_SAVE_FLAG
							| Canvas.CLIP_TO_LAYER_SAVE_FLAG);
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
			canvas.drawBitmap(mImgBitmap, new Rect(0, 0, mImgBitmap.getWidth(), mImgBitmap.getHeight()), new RectF(0, 0, mImgDesRect.width(),mImgDesRect.height()), null);
			canvas.drawBitmap(mEraseMaskBitmap, new Rect(0, 0, mEraseMaskBitmap.getWidth(), mEraseMaskBitmap.getHeight()), new RectF(0, 0, mImgDesRect.width(),mImgDesRect.height()), paint);
			paint.setXfermode(null);
			canvas.restoreToCount(sc);
			canvas.save(Canvas.ALL_SAVE_FLAG);
			canvas.restore();
			return mCombinedBmp;
		}
		return null;
	}

	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		float x = event.getX() - mImgDesRect.left;
		float y = event.getY() - mImgDesRect.top;
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				mErasePath.reset();
				mErasePath.moveTo(x, y);
				mX = x;
				mY = y;
				invalidate();
				break;
			case MotionEvent.ACTION_MOVE:
				float dx = Math.abs(x - mX);
				float dy = Math.abs(y - mY);
				if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
					mErasePath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
					mX = x;
					mY = y;
					// commit the path to offscreen
					mCanvas.drawPath(mErasePath, mErasePaint);
				}
				invalidate(); //refresh
				break;
			case MotionEvent.ACTION_UP:
				mErasePath.lineTo(mX, mY);
				mCanvas.drawPath(mErasePath, mErasePaint);
				mErasePath.reset();
				invalidate();
				break;
		}
		return true;
	}

	private void initBmpMask()
	{
		if(mImgDesRect != null)
		{
			int w = (int) mImgDesRect.width();
			int h = (int) mImgDesRect.height();
			if(mEraseMaskBitmap == null)
			{
				mEraseMaskBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
				mEraseMaskBitmap.eraseColor(Color.TRANSPARENT);
			}
		}

		if(mEraseMaskBitmap != null)
			mCanvas = new Canvas(mEraseMaskBitmap);
	}

	private void initErasePaint(){
		mErasePaint = new Paint();
		mErasePaint.setAntiAlias(true);
		mErasePaint.setDither(true);
		mErasePaint.setColor(0xFF000000);
		mErasePaint.setStyle(Paint.Style.STROKE);
		mErasePaint.setStrokeJoin(Paint.Join.ROUND);
		mErasePaint.setStrokeCap(Paint.Cap.ROUND);
		mErasePaint.setStrokeWidth(40);
	}

	public void prepare(){
		mErasePath = new Path();
		initErasePaint();
	}

	public boolean apply(){
		if(mEraseMaskBitmap != null)
		{
			Bitmap newBmp = combineImgWithMask();
			if(newBmp != null)
			{
				return saveBitmap(newBmp);
			}
		}
		return false;
	}


	/*
	 * switch erase mode
	 * true - erase; false - unerase
	 * */
	public void switchEraseMode(boolean bErase){
		if(mErasePaint == null)
			initErasePaint();

		if(bErase)
			mErasePaint.setXfermode(null);
		else
			mErasePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
	}

	public void uninit(){
		if(mImgBitmap != null)
		{
			mImgBitmap.recycle();
			mImgBitmap = null;
		}
		if(mEraseMaskBitmap != null)
		{
			mEraseMaskBitmap.recycle();
			mEraseMaskBitmap = null;
		}
	}

	public boolean saveBitmap(Bitmap bitmap)
	{
		File file = new File("/sdcard/out.png");
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {
				out.flush();
				out.close();
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
