package com.smartman.myroadrecord.ui;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by jiahui.chen on 2015/12/28.
 */
public class TimeCount extends CountDownTimer {
    private TextView timeView;

    public TimeCount(long millisInFuture, long countDownInterval, TextView view) {
        super(millisInFuture, countDownInterval);
        timeView = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeView.setEnabled(false);
        timeView.setText(millisUntilFinished / 1000 + "秒后重发");
    }

    @Override
    public void onFinish() {
        timeView.setEnabled(true);
        timeView.setText("发送验证码");
    }
}
