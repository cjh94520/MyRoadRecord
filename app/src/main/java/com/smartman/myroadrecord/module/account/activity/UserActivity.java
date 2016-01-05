package com.smartman.myroadrecord.module.account.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.smartman.myroadrecord.R;
import com.smartman.base.blur.fragments.JniBlurBitMapFragment;
import com.smartman.base.blur.fragments.RSBlurFragment;
import com.smartman.base.animation.viewpager.ZoomOutPageTransformer;

import java.util.ArrayList;

public class UserActivity extends FragmentActivity {

    private CustomPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        pagerAdapter =
                new CustomPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    public class CustomPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
            // This should sdk >= 4.4
            fragments.add(Fragment.instantiate(UserActivity.this, RSBlurFragment.class.getName()));
            fragments.add(Fragment.instantiate(UserActivity.this, JniBlurBitMapFragment.class.getName()));
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).toString();
        }
    }
}

