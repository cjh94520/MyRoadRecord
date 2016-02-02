package com.smartman.myroadrecord.module.account.activity;

import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartman.base.activity.BaseActivity;
import com.smartman.myroadrecord.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity {
    @ViewInject(R.id.about)
    private TextView aboutView;
    @ViewInject(R.id.lay_update)
    private LinearLayout updateLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置点击效果
        initClickEffect();
        //获取版本号
        initAppInfo();
    }

    private void initClickEffect() {
        TypedArray a = this.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        updateLayout.setBackground(a.getDrawable(0));
    }

    private void initAppInfo() {
        aboutView.setText("Version:" + getAppInfo());
    }

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, PackageManager.GET_ACTIVITIES).versionName;
//            int versionCode = this.getPackageManager()
//                    .getPackageInfo(pkName, 0).versionCode;
            return versionName;
        } catch (Exception e) {
        }
        return null;
    }
}
