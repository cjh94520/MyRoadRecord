package com.smartman.myroadrecord.module.account.activity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.smartman.base.activity.BaseActivity;
import com.smartman.base.ui.CircleImageView;
import com.smartman.base.utils.SnackbarUtil;
import com.smartman.base.utils.ToastUtil;
import com.smartman.myroadrecord.R;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.FileNotFoundException;

/**
 * Created by jiahui.chen on 2016/1/7.
 */
@ContentView(R.layout.activity_userdetail)
public class UserDetailActivity extends BaseActivity {
    @ViewInject(R.id.lay_img)
    private RelativeLayout imgLayout;
    @ViewInject(R.id.img)
    private CircleImageView imgView;
    @ViewInject(R.id.lay_name)
    private RelativeLayout nameLayout;
    @ViewInject(R.id.name)
    private TextView nameView;
    @ViewInject(R.id.lay_about)
    private LinearLayout aboutLayout;
    @ViewInject(R.id.switch_record)
    private Switch recordSwitch;


    private static final int CHANGE_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置action bar
        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.setting);

        //设置点击效果
        initClickEffect();
        //switch事件
        initCheckChange();
    }

    private void initClickEffect() {
        TypedArray a = this.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        aboutLayout.setBackground(a.getDrawable(0));
        TypedArray b = this.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        imgLayout.setBackground(b.getDrawable(0));
        TypedArray c = this.obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        nameLayout.setBackground(c.getDrawable(0));
    }

    private void initCheckChange() {
        recordSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showMessage("开启中");
                } else {
                    SnackbarUtil.show(buttonView, "已关闭", Snackbar.LENGTH_SHORT);
                }
            }
        });
    }

    //更换用户头像事件
    @Event(value = R.id.lay_img, type = View.OnClickListener.class)
    private void changeImgClick(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CHANGE_IMG);
    }

    //更换用户昵称事件
    @Event(value = R.id.lay_about, type = View.OnClickListener.class)
    private void gotoAboutActivityClick(View view) {
        Intent intent = new Intent(UserDetailActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    //更换用户昵称事件
    @Event(value = R.id.lay_name, type = View.OnClickListener.class)
    private void changeNameClick(View view) {
        final EditText editText;
        new AlertDialog.Builder(this)
                .setTitle("更换的名字")
                .setView(editText = new EditText(this))
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameView.setText(editText.getText());
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHANGE_IMG) {
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    LogUtil.d("字节数：" + String.valueOf(bmp.getByteCount() / 1024.0 / 1024));
                    Bitmap target = ThumbnailUtils.extractThumbnail(bmp, imgView.getWidth(), imgView.getHeight(), ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                    LogUtil.d("字节数：" + String.valueOf(target.getByteCount() / 1024.0 / 1024));
                    bmp.recycle();
                    imgView.setImageBitmap(target);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

    }


}
