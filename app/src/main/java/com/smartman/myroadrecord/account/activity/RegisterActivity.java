package com.smartman.myroadrecord.account.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.smartman.myroadrecord.R;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox mShowPassword;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPassword = (EditText)findViewById(R.id.et_input_pwd);
        mShowPassword = (CheckBox) findViewById(R.id.chk_show_pwd);
        mShowPassword.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switch (buttonView.getId()) {
                            case R.id.chk_show_pwd:
                                if (isChecked) {
                                    //设置EditText文本为显示
                                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                } else {
                                    //设置EditText文本为隐藏
                                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                }
                                break;
                        }
                    }
                }
        );

    }

}
