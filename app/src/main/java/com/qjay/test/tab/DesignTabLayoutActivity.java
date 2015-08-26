package com.qjay.test.tab;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.qjay.test.R;

/**
 *
 * Created by JaySeng on 2015/8/23.
 */
public class DesignTabLayoutActivity extends FragmentActivity {

    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_tablayout);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.getTabAt(0).setText("fdsfdsf");
//        tabLayout.setAlpha(0);
        tabLayout.addTab(buildTab(tabLayout));
        tabLayout.addTab(buildTab(tabLayout));
        tabLayout.addTab(buildTab(tabLayout));
        tabLayout.setTabTextColors(Color.RED, Color.BLUE);

        tabLayout.setSelectedTabIndicatorColor(new ColorDrawable().getColor());
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedTabPosition = tabLayout.getSelectedTabPosition();
                viewPager.setCurrentItem(selectedTabPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    @NonNull
    public void set(){

    }

    private TabLayout.Tab buildTab(TabLayout tabLayout) {
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText("q r ");
        return tab;
    }

}
