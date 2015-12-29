package com.smartman.myroadrecord.account.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.activity.BaseActivity;
import com.smartman.myroadrecord.utils.AssetUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_agreement)
public class AgreementActivity extends BaseActivity {
    @ViewInject(R.id.context)
    private TextView contextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载协议
        initAgreement();
    }

    private void initAgreement()
    {
        String context = AssetUtil.getFromAssets("agreement.txt");
        contextView.setText(context);
    }
}
