package com.example.ht.kotlinproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ht.kotlinproject.adapter.ViewPagerAdapter;

public class KotlinMainActivity extends AppCompatActivity {

    private TabLayout root_tabLayout;
    private ViewPager tableLayout_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin_main);
        initView();


    }


    private void initView() {
        Toolbar toolbar_home = (Toolbar) findViewById(R.id.toolbar_home);
        root_tabLayout = (TabLayout) findViewById(R.id.root_tabLayout);
        tableLayout_viewpager = (ViewPager) findViewById(R.id.tableLayout_viewpager);

//        root_tabLayout.addTab(root_tabLayout.newTab().setText("Tab 1"));
//        root_tabLayout.addTab(root_tabLayout.newTab().setText("Tab 2"));
//        root_tabLayout.addTab(root_tabLayout.newTab().setText("Tab 3"));
        root_tabLayout.setTabMode(TabLayout.MODE_FIXED); // 设置模式， 填充整个屏，不可左右滚动
        ViewPagerAdapter tabAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        tableLayout_viewpager.setAdapter(tabAdapter);
        root_tabLayout.setupWithViewPager(tableLayout_viewpager);

//        for (int i = 0; i < root_tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tabAt = root_tabLayout.getTabAt(i);
//            assert tabAt != null;
//            tabAt.setCustomView(tabAdapter.getTabView(i + 8));
//        }
        root_tabLayout.getTabAt(0).setCustomView(tabAdapter.getTabView(5));
        root_tabLayout.getTabAt(1).setCustomView(tabAdapter.getTabView(9));
        root_tabLayout.getTabAt(2).setCustomView(tabAdapter.getTabView(10));
        //  tabLayout item  点击事件
        root_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
