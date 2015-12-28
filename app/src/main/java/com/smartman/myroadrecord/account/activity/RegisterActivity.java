package com.smartman.myroadrecord.account.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.CheckBox;
import android.widget.EditText;

import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.activity.BaseActivity;

public class RegisterActivity extends BaseActivity {

    private CheckBox mShowPassword;
    private EditText mPassword;
    private FloatingActionButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //设置action bar
        this.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        this.getMDActionBar().setTitle(R.string.register);

//        mPassword = (EditText)findViewById(R.id.et_input_pwd);
//        mShowPassword = (CheckBox) findViewById(R.id.chk_show_pwd);
//        mShowPassword.setOnCheckedChangeListener(
//                new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        switch (buttonView.getId()) {
//                            case R.id.chk_show_pwd:
//                                if (isChecked) {
//                                    //设置EditText文本为显示
//                                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                                } else {
//                                    //设置EditText文本为隐藏
//                                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                                }
//                                break;
//                        }
//                    }
//                }
//        );
//
//        registerButton = (FloatingActionButton)findViewById(R.id.register);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(RegisterActivity.this,"已经点击了",Toast.LENGTH_LONG).show();
//            }
//        });

    }

}
