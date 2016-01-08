package com.smartman.myroadrecord.module.account.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartman.base.activity.BaseActivity;
import com.smartman.myroadrecord.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by jiahui.chen on 2016/1/7.
 */
@ContentView(R.layout.activity_userdetail)
public class UserDetailActivity extends BaseActivity {
    @ViewInject(R.id.about)
    private TextView aboutTextView;
    @ViewInject(R.id.lay_img)
    private RelativeLayout imgLayout;
    @ViewInject(R.id.lay_name)
    private LinearLayout nameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置action bar
        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.setting);

        //设置点击效果
        initClickEffect();
    }

    private void initClickEffect()
    {
        TypedArray a = this.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        aboutTextView.setBackground(a.getDrawable(0));
        TypedArray b = this.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        imgLayout.setBackground(b.getDrawable(0));
        TypedArray c = this.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        nameLayout.setBackground(c.getDrawable(0));
    }
}
