package com.smartman.myroadrecord.module.map.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.base.adapter.MyRecyclerViewAdapter;
import com.smartman.myroadrecord.base.fragment.ViewPageFragment;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by jiahui.chen on 2015/12/30.
 */
@ContentView(R.layout.province_list)
public class ProvinceListFragment extends ViewPageFragment implements SwipeRefreshLayout.OnRefreshListener, MyRecyclerViewAdapter.OnItemClickListener {
    @ViewInject(R.id.id_swiperefreshlayout)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewInject(R.id.id_recyclerview)
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        configRecyclerView();

        // 刷新时，指示器旋转后变化的颜色
        //mSwipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    private void configRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity());
        mRecyclerViewAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);


        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(position==0)
        {
            View v = view.findViewById(R.id.province_shadow);
            LogUtil.d("宽度:"+String.valueOf(v.getWidth()));
            LogUtil.d("高度:"+String.valueOf(v.getHeight()));
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onRefresh() {
        // 刷新时模拟数据的变化
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                int temp = (int) (Math.random() * 10);
                mRecyclerViewAdapter.mDatas.add(0, "new" + temp);
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
