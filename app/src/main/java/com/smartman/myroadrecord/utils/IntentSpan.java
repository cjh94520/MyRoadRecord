package com.smartman.myroadrecord.utils;

import android.content.Context;
import android.content.Intent;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class IntentSpan extends ClickableSpan {
    public enum Type {
        Action,
        email,
        phone
    }

    private Context mContext;
    private Type mType;
    private String mUri;

    public IntentSpan(Context context, Type type, String uri) {
        mContext = context;
        mType = type;
        mUri = uri;
    }

    @Override
    public void onClick(View widget) {
        if (mType == Type.Action) {
            Intent intent = new Intent();
            intent.setAction(mUri);
            mContext.startActivity(intent);
        }
    }
}
