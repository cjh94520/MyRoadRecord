package com.smartman.myroadrecord.module.account.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartman.base.activity.BaseActivity;
import com.smartman.base.task.TaskException;
import com.smartman.base.task.WorkTask;
import com.smartman.base.utils.ResourceUtil;
import com.smartman.base.utils.TimeCount;
import com.smartman.base.utils.ToastUtil;
import com.smartman.base.utils.ValidUtil;
import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.business.account.accountMgmt;
import com.smartman.myroadrecord.business.account.bean.AccountBean;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import cn.my7g.qjlink.sdk.QJLinkManager;
import cn.my7g.qjlink.sdk.http.OnLoadDataListener;

@ContentView(R.layout.activity_re_password)
public class RePasswordActivity extends BaseActivity {
    @ViewInject(R.id.phone)
    private EditText phoneText;
    @ViewInject(R.id.code)
    private EditText codeText;
    @ViewInject(R.id.password)
    private EditText pwdText;
    @ViewInject(R.id.re_password)
    private EditText rePwdText;
    @ViewInject(R.id.request_code)
    private Button requestCodeButton;
    @ViewInject(R.id.complete)
    private Button completeButton;

    private static final int EXPIRED_CODE = 0x1001;
    private static final int WRONG_CODE = 0x1002;
    private static final int RIGHT_CODE = 0x1003;

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EXPIRED_CODE:
                    ToastUtil.showMessage(R.string.expired_cord);
                    break;
                case WRONG_CODE:
                    ToastUtil.showMessage(R.string.wrong_cord);
                    break;
                case RIGHT_CODE:
                    completeButton.setEnabled(false);
                    new CheckPhoneTask().execute();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.update_pwd);
    }

    //获取验证码事件
    @Event(value = R.id.request_code, type = View.OnClickListener.class)
    private void requestCodeClick(View view) {
        String phone = phoneText.getText().toString();
        if (!ValidUtil.isValidMobilePhone(phone)) {
            Toast.makeText(RePasswordActivity.this, ResourceUtil.getString(R.string.invalid_phone), Toast.LENGTH_SHORT).show();
            return;
        }
        TimeCount timeCount = new TimeCount(60000, 1000, requestCodeButton);
        timeCount.start();
        QJLinkManager.getInstance(RePasswordActivity.this).requestPassword(phone, new OnLoadDataListener() {
            @Override
            public void onSuccess(String s) {
                requestCodeButton.setEnabled(true);
                LogUtil.d(s);
            }

            @Override
            public void onError(String s) {
                requestCodeButton.setEnabled(true);
                LogUtil.d(s);
            }
        });
    }

    //修改密码事件
    @Event(value = R.id.complete, type = View.OnClickListener.class)
    private void completeRegisterClick(View view) {
        final String phone = phoneText.getText().toString();
        String code = codeText.getText().toString();
        String password = pwdText.getText().toString();
        String rePassword = rePwdText.getText().toString();
        if (!ValidUtil.isMatchPassword(password)) {
            ToastUtil.showMessage(R.string.password_wrong);
            return;
        }
        if (!password.equals(rePassword)) {
            ToastUtil.showMessage(R.string.different_pwd);
            return;
        }
        QJLinkManager.getInstance(RePasswordActivity.this).requestLogin(phone, code, new OnLoadDataListener() {
            @Override
            public void onSuccess(String s) {
                LogUtil.d(s);
                Message msg = Message.obtain();
                if (s.equals("{\"code\":1,\"msg\":\"验证码已过期\"}")) {
                    msg.what = EXPIRED_CODE;
                } else if (s.equals("{\"code\":1,\"msg\":\"验证码错误\"}")) {
                    msg.what = WRONG_CODE;
                } else {
                    msg.what = RIGHT_CODE;
                }
                myHandler.sendMessage(msg);
            }

            @Override
            public void onError(String s) {
                LogUtil.d(s);
                ToastUtil.showMessage(s);
            }
        });
    }

    class CheckPhoneTask extends WorkTask<Void, Void, Boolean> {
        @Override
        public Boolean workInBackground(Void... params) throws TaskException {
            String phone = phoneText.getText().toString();
            return new accountMgmt().checkPhoneInfo(phone);
        }

        @Override
        protected void onSuccess(Boolean s) {
            super.onSuccess(s);
            LogUtil.d(String.valueOf(s));
            //返回true代表该手机号码已经存在
            if (s) {
                new UpdatePwdTask().execute();
            } else {
                ToastUtil.showMessage(ResourceUtil.getString(R.string.empty_phone));
            }
        }

        @Override
        protected void onFailure(TaskException exception) {
            super.onFailure(exception);
            LogUtil.d(exception.getMessage());
            completeButton.setEnabled(true);
        }
    }

    class UpdatePwdTask extends WorkTask<Void, Void, Boolean> {
        @Override
        public Boolean workInBackground(Void... params) throws TaskException {
            AccountBean bean = new AccountBean();
            bean.id = phoneText.getText().toString();
            bean.password = pwdText.getText().toString();
            return new accountMgmt().updatePwd(bean);
        }

        @Override
        protected void onSuccess(Boolean s) {
            super.onSuccess(s);
            if (s) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RePasswordActivity.this);
                builder.setMessage(R.string.pwd_succeed_update)
                        .setPositiveButton("自动登录",null)
                        .setNeutralButton("返回", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            } else {
                ToastUtil.showMessage(R.string.pwd_fail_update);
            }
        }

        @Override
        protected void onFailure(TaskException exception) {
            super.onFailure(exception);
            LogUtil.d(exception.getMessage());
            ToastUtil.showMessage(R.string.pwd_fail_update);
        }

        @Override
        protected void onFinished() {
            super.onFinished();
            completeButton.setEnabled(true);
        }
    }

}
