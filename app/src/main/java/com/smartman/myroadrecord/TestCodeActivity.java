package com.smartman.myroadrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.my7g.qjlink.sdk.QJLinkManager;
import cn.my7g.qjlink.sdk.http.OnLoadDataListener;

/**
 * Created by jiahui.chen on 2015/12/24.
 */

public class TestCodeActivity extends AppCompatActivity {
    private static final String TAG = TestCodeActivity.class.getSimpleName();
    private String phoneNumber = "18688553035";
    Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(false);
                QJLinkManager.getInstance(TestCodeActivity.this).requestPassword(phoneNumber, new OnLoadDataListener() {
                    @Override
                    public void onSuccess(String s) {
                        btn.setEnabled(true);
                        Log.i(TAG, s);
                    }

                    @Override
                    public void onError(String s) {
                        btn.setEnabled(true);
                        Log.i(TAG, s);
                    }
                });
            }
        });



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
