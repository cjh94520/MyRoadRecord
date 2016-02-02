package com.smartman.myroadrecord.module.account.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartman.base.blur.util.FastBlur;
import com.smartman.base.ui.CircleImageView;
import com.smartman.base.utils.ImageUtil;
import com.smartman.base.utils.PrefsUtil;
import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.base.fragment.ViewPageFragment;
import com.smartman.myroadrecord.module.account.activity.LoginActivity;
import com.smartman.myroadrecord.module.account.activity.UserDetailActivity;
import com.smartman.myroadrecord.module.account.event.UserEvent;
import com.smartman.myroadrecord.module.account.param.UserConst;
import com.smartman.myroadrecord.module.account.util.UserUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import de.greenrobot.event.EventBus;

@ContentView(R.layout.activity_userinfo)
public class UserFragment extends ViewPageFragment {
    @ViewInject(R.id.img_blur)
    private ImageView BlurImg;
    @ViewInject(R.id.img_user)
    private CircleImageView Userimg;
    @ViewInject(R.id.user_setting)
    private ImageView settingImgView;
    @ViewInject(R.id.user_name)
    private TextView nameView;
    @ViewInject(R.id.tologin)
    private Button toLoginBtn;
    @ViewInject(R.id.left_line_bottom)
    private View leftLine;
    @ViewInject(R.id.right_line_bottom)
    private View rightLine;

    private static LruCache cache = new LruCache((int) (Runtime.getRuntime().maxMemory() / 3));

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        initImg();
        //初始化足迹的数据
        initData();
        //高斯模糊
        //applyBlur();
    }

    private void initImg() {
        Boolean isLogined = PrefsUtil.loadPrefBoolean(UserConst.USER_LOGINED, false);
        if (!isLogined) {
            //默认头像
            Userimg.setImageResource(R.drawable.girl);
        } else {
            ImageUtil.displayImage(Userimg, UserUtil.getUserImgPath());
        }
    }

    private void initData() {
        Boolean isLogined = PrefsUtil.loadPrefBoolean(UserConst.USER_LOGINED, false);
        if (!isLogined) {
            toLoginBtn.setVisibility(View.VISIBLE);
            settingImgView.setVisibility(View.GONE);
            leftLine.setVisibility(View.GONE);
            rightLine.setVisibility(View.GONE);
        } else
        //已经登陆了需要刷新数据
        {
            toLoginBtn.setVisibility(View.GONE);
            settingImgView.setVisibility(View.VISIBLE);
            leftLine.setVisibility(View.VISIBLE);
            rightLine.setVisibility(View.VISIBLE);
            String name = PrefsUtil.loadPrefString(UserConst.USER_NAME, "未登录");
            nameView.setText(name);
        }
    }

    private void applyBlur() {
        BlurImg.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                BlurImg.getViewTreeObserver().removeOnPreDrawListener(this);
                Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
                //按照指定宽高压缩，设置ThumbnailUtils.OPTIONS_RECYCLE_INPUT回收原图
                Bitmap target = ThumbnailUtils.extractThumbnail(source, BlurImg.getWidth(), BlurImg.getHeight(), ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                source.recycle();
                blur(target, BlurImg);
                return true;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void blur(Bitmap target, View view) {
        Object o;
        if ((o = cache.get("userImg")) == null) {
            float scaleFactor = 8;
            float radius = 20;//
            Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth() / scaleFactor),
                    (int) (view.getMeasuredHeight() / scaleFactor), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(overlay);
            canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
            canvas.scale(1 / scaleFactor, 1 / scaleFactor);
            Paint paint = new Paint();
            paint.setFlags(Paint.FILTER_BITMAP_FLAG);
            canvas.drawBitmap(target, 0, 0, paint);
            overlay = FastBlur.doBlurJniBitMap(overlay, (int) radius, true);
            view.setBackground(new BitmapDrawable(getResources(), overlay));
            cache.put("userImg", overlay);
        } else {
            view.setBackground(new BitmapDrawable(getResources(), (Bitmap) o));
        }
    }

    @Event(value = R.id.user_setting, type = View.OnClickListener.class)
    private void gotoSettingClick(View view) {
        if (getActivity() == null) return;
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        startActivity(intent);
    }


    @Event(value = R.id.tologin, type = View.OnClickListener.class)
    private void gotoLoginClick(View view) {
        if (getActivity() == null) return;
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(UserEvent event) {
        initData();
    }
}
