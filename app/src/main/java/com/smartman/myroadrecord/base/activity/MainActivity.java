package com.smartman.myroadrecord.base.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.githang.viewpagerindicator.IconPagerAdapter;
import com.githang.viewpagerindicator.IconTabPageIndicator;
import com.smartman.base.activity.BaseActivity;
import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.base.fragment.ViewPageFragment;
import com.smartman.myroadrecord.business.mcc.MccInfoMgmt;
import com.smartman.myroadrecord.business.mcc.bean.MccInfoReturnBean;
import com.smartman.myroadrecord.module.map.fragment.ProvinceListFragment;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private IconTabPageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        Button button = (Button)findViewById(R.id.btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DBUtil.initProvince();
//            }
//        });

    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mIndicator = (IconTabPageIndicator) findViewById(R.id.indicator);
        List<ViewPageFragment> fragments = initFragments();
        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
    }

    private List<ViewPageFragment> initFragments() {
        List<ViewPageFragment> fragments = new ArrayList<ViewPageFragment>();

        ProvinceListFragment userFragment = new ProvinceListFragment();
        userFragment.setTitle("时间神殿");
        userFragment.setIconId(R.drawable.tab_user_selector);
        fragments.add(userFragment);

        ViewPageFragment noteFragment = new ViewPageFragment();
        noteFragment.setTitle("探索世界");
        noteFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(noteFragment);

        ViewPageFragment contactFragment = new ViewPageFragment();
        contactFragment.setTitle("附近");
        contactFragment.setIconId(R.drawable.tab_user_selector);
        fragments.add(contactFragment);

        ViewPageFragment recordFragment = new ViewPageFragment();
        recordFragment.setTitle("我的");
        recordFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(recordFragment);

        return fragments;
    }

    class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        private List<ViewPageFragment> mFragments;

        public FragmentAdapter(List<ViewPageFragment> fragments, FragmentManager fm) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            if(i==3)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MccInfoReturnBean bean = new MccInfoMgmt().getMccInfo("460");
                        LogUtil.d(bean.toString());
                    }
                }).start();
            }
            return mFragments.get(i);
        }

        @Override
        public int getIconResId(int index) {
            return mFragments.get(index).getIconId();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).getTitle();
        }
    }

}
