package com.smartman.myroadrecord.base.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.githang.viewpagerindicator.IconPagerAdapter;
import com.githang.viewpagerindicator.IconTabPageIndicator;
import com.smartman.base.activity.BaseActivity;
import com.smartman.base.animation.viewpager.ZoomOutPageTransformer;
import com.smartman.base.utils.ResourceUtil;
import com.smartman.myroadrecord.R;
import com.smartman.myroadrecord.base.fragment.ViewPageFragment;
import com.smartman.myroadrecord.module.account.fragment.UserFragment;
import com.smartman.myroadrecord.module.map.fragment.ProvinceListFragment;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private IconTabPageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mIndicator.setViewPager(mViewPager);
    }

    private List<ViewPageFragment> initFragments() {
        List<ViewPageFragment> fragments = new ArrayList<ViewPageFragment>();

        ViewPageFragment userFragment = new ViewPageFragment();
        userFragment.setTitle(ResourceUtil.getString(R.string.time_record));
        userFragment.setIconId(R.drawable.tab_user_selector);
        fragments.add(userFragment);

        ViewPageFragment noteFragment = new ProvinceListFragment();
        noteFragment.setTitle(ResourceUtil.getString(R.string.explore_world));
        noteFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(noteFragment);

        ViewPageFragment contactFragment = new ViewPageFragment();
        contactFragment.setTitle(ResourceUtil.getString(R.string.nearby));
        contactFragment.setIconId(R.drawable.tab_user_selector);
        fragments.add(contactFragment);

        ViewPageFragment recordFragment = new UserFragment();
        recordFragment.setTitle(ResourceUtil.getString(R.string.me));
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
