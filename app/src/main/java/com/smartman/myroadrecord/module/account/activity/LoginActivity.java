package com.smartman.myroadrecord.module.account.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.smartman.base.activity.BaseActivity;
import com.smartman.base.task.TaskException;
import com.smartman.base.task.WorkTask;
import com.smartman.base.utils.ResourceUtil;
import com.smartman.base.utils.ToastUtil;
import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.business.account.accountMgmt;
import com.smartman.myroadrecord.business.account.bean.AccountBean;
import com.smartman.myroadrecord.business.account.bean.AccountReturnBean;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by jiahui.chen on 2015/12/28.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.user_id)
    private EditText idText;
    @ViewInject(R.id.user_pwd)
    private EditText pwdText;
    @ViewInject(R.id.send_login)
    private Button loginButton;
    @ViewInject(R.id.lay_register)
    private LinearLayout registerLayout;
    @ViewInject(R.id.lay_forget_pwd)
    private LinearLayout forgetPwdLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置action bar
        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.login);
    }

    @Event(value = R.id.send_login, type = View.OnClickListener.class)
    private void gotoLoginClick(View view) {
        String id = idText.getText().toString();
        String pwd = pwdText.getText().toString();
        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pwd)) {
            new LoginTask().execute();
        } else {
            ToastUtil.showMessage(ResourceUtil.getString(R.string.empty_login));
        }
    }

    @Event(value = R.id.lay_register, type = View.OnClickListener.class)
    private void gotoRegisterActivityClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.lay_forget_pwd, type = View.OnClickListener.class)
    private void gotoRePasswordActivityClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RePasswordActivity.class);
        startActivity(intent);
    }

    class LoginTask extends WorkTask<Void, Void, AccountReturnBean> {
        @Override
        protected void onPrepare() {
            super.onPrepare();
            loginButton.setEnabled(false);
        }

        @Override
        public AccountReturnBean workInBackground(Void... params) throws TaskException {
            AccountBean bean = new AccountBean();
            bean.id = idText.getText().toString();
            bean.password = pwdText.getText().toString();
            return new accountMgmt().login(bean);
        }

        @Override
        protected void onSuccess(AccountReturnBean s) {
            super.onSuccess(s);
            if (s.code == -1) {
                ToastUtil.showMessage(ResourceUtil.getString(R.string.wrong_login));
            } else {
                finish();
                //刷新UserFragment
            }
        }

        @Override
        protected void onFailure(TaskException exception) {
            super.onFailure(exception);
            LogUtil.d(exception.getMessage());
            ToastUtil.showMessage(exception.getMessage());
        }

        @Override
        protected void onFinished() {
            super.onFinished();
            loginButton.setEnabled(true);
        }
    }
}
