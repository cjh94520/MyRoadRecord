package com.smartman.myroadrecord.module.account.activity;

import android.os.Bundle;

import com.smartman.base.activity.BaseActivity;
import com.smartman.myroadrecord.R;

import org.xutils.view.annotation.ContentView;

/**
 * Created by jiahui.chen on 2016/1/7.
 */
@ContentView(R.layout.activity_userdetail)
public class UserDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置action bar
        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.setting);
    }
}
