package com.smartman.myroadrecord.module.account.activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smartman.base.activity.BaseActivity;
import com.smartman.base.task.TaskException;
import com.smartman.base.task.WorkTask;
import com.smartman.base.ui.CircleImageView;
import com.smartman.base.utils.DialogUtil;
import com.smartman.base.utils.PrefsUtil;
import com.smartman.base.utils.ResourceUtil;
import com.smartman.base.utils.ToastUtil;
import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.base.activity.MainActivity;
import com.smartman.myroadrecord.business.account.accountMgmt;
import com.smartman.myroadrecord.module.account.event.UserEvent;
import com.smartman.myroadrecord.module.account.param.UserConst;
import com.smartman.myroadrecord.module.account.util.UserUtil;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileNotFoundException;

import de.greenrobot.event.EventBus;

@ContentView(R.layout.activity_access)
public class AccessActivity extends BaseActivity {
    @ViewInject(R.id.access_btn)
    private Button accessBtn;
    @ViewInject(R.id.access_img)
    private CircleImageView imgView;
    @ViewInject(R.id.access_name)
    private EditText nameText;

    private static final int CHANGE_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(value = R.id.access_img, type = DialogInterface.OnClickListener.class)
    private void imgViewClick(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CHANGE_IMG);
    }

    @Event(value = R.id.access_btn, type = DialogInterface.OnClickListener.class)
    private void accessClick(View view) {
        Intent intent = new Intent(AccessActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        PrefsUtil.savePrefString(UserConst.USER_NAME, nameText.getText().toString());
        // 发布事件，在后台线程发的事件
        EventBus.getDefault().post(new UserEvent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHANGE_IMG) {
                Uri uri = data.getData();
                LogUtil.d(uri.getPath());
                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    //压缩大约有30倍
                    Bitmap target = ThumbnailUtils.extractThumbnail(bmp, imgView.getWidth(), imgView.getHeight(), ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                    bmp.recycle();
                    File file = UserUtil.saveBitmap2file(target);
                    new uploadImgTask().execute(file.getPath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    class uploadImgTask extends WorkTask<String, Void, Boolean> {
        String mFilePath = null;

        @Override
        protected void onPrepare() {
            super.onPrepare();
            DialogUtil.showProgressDialog(AccessActivity.this, ResourceUtil.getString(R.string.uploadingImg), false);
        }

        @Override
        public Boolean workInBackground(String... params) throws TaskException {
            mFilePath = params[0];
            return new accountMgmt().uploadImg(params[0]);
        }

        @Override
        protected void onSuccess(Boolean s) {
            super.onSuccess(s);
            Bitmap bmp = BitmapFactory.decodeFile(mFilePath);
            imgView.setImageBitmap(bmp);
        }

        @Override
        protected void onFailure(TaskException exception) {
            super.onFailure(exception);
            ToastUtil.showMessage(R.string.upload_fail);
            LogUtil.d(exception.getMessage());
        }

        @Override
        protected void onFinished() {
            super.onFinished();
            DialogUtil.dismissNormalProgressDialog();
        }
    }
}