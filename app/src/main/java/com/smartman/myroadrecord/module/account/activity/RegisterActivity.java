package com.smartman.myroadrecord.module.account.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.smartman.base.activity.BaseActivity;
import com.smartman.base.utils.IntentSpan;
import com.smartman.base.utils.ResourceUtil;
import com.smartman.base.utils.TimeCount;
import com.smartman.base.utils.ValidUtil;
import com.smartman.myroadrecord.R;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import cn.my7g.qjlink.sdk.QJLinkManager;
import cn.my7g.qjlink.sdk.http.OnLoadDataListener;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.phone)
    private EditText phoneText;
    @ViewInject(R.id.code)
    private EditText codeText;
    @ViewInject(R.id.password)
    private EditText pwdText;
    @ViewInject(R.id.request_code)
    private Button requestCodeButton;
    @ViewInject(R.id.complete)
    private Button completeButton;
    @ViewInject(R.id.register_tip)
    private TextView tipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setNavigationBarTintEnabled(true);
            mTintManager.setTintColor(0xF00099CC);
           // mTintManager.setTintDrawable(UIElementsHelper.getGeneralActionBarBackground(this));

        }

        //设置action bar
        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.register);

        //初始化用户协议
        initAgreement();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initAgreement() {
        String agreement = ResourceUtil.getString(R.string.register_tip);
        int index = agreement.indexOf("用户使用协议");
        SpannableStringBuilder builder = new SpannableStringBuilder(agreement);
        IntentSpan intentSpan = new IntentSpan(this, IntentSpan.Type.Action, "intent.action.agreement");
        builder.setSpan(intentSpan, index, index + 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tipView.setText(builder);
        tipView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //获取验证码事件
    @Event(value = R.id.request_code, type = View.OnClickListener.class)
    private void requestCodeClick(View view) {
        String phone = phoneText.getText().toString();
        if (!ValidUtil.isValidMobilePhone(phone)) {
            Toast.makeText(RegisterActivity.this, ResourceUtil.getString(R.string.invalid_phone), Toast.LENGTH_SHORT).show();
            return;
        }

        //
        // 插入服务器判断手机号是否已经注册
        //
        TimeCount timeCount = new TimeCount(60000, 1000, requestCodeButton);
        timeCount.start();
        QJLinkManager.getInstance(RegisterActivity.this).requestPassword(phone, new OnLoadDataListener() {
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


    //注册事件
    @Event(value = R.id.complete, type = View.OnClickListener.class)
    private void completeRegisterClick(View view) {
        String phone = phoneText.getText().toString();
        String code = codeText.getText().toString();
        QJLinkManager.getInstance(RegisterActivity.this).requestLogin(phone, code, new OnLoadDataListener() {
            @Override
            public void onSuccess(String s) {
                LogUtil.d(s);
                Intent intent = new Intent(RegisterActivity.this,AccessActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(String s) {
                LogUtil.d(s);
            }
        });
    }

}
