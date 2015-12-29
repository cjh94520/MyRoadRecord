package com.smartman.myroadrecord.module.account.activity;

import android.os.Bundle;

import com.smartman.myroadrecord.R;
import com.smartman.base.activity.BaseActivity;

import org.xutils.view.annotation.ContentView;

/**
 * Created by jiahui.chen on 2015/12/28.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置action bar
        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.login);
    }
}
