package com.smartman.myroadrecord.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smartman.myroadrecord.R;

/**
 * Created by jiahui.chen on 2015/12/30.
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextView;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.id_textview);
    }
}