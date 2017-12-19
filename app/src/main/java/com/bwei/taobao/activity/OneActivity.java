package com.bwei.taobao.activity;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.bwei.taobao.R;
import com.bwei.taobao.adpater.ViewPagerAdapter;
import com.bwei.taobao.fragment.Fragment01;
import com.bwei.taobao.fragment.Fragment02;
import com.bwei.taobao.fragment.Fragment03;
import com.bwei.taobao.fragment.Fragment04;
import com.bwei.taobao.fragment.Fragment05;
import com.hjm.bottomtabbar.BottomTabBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneActivity extends FragmentActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.button_tab_bar)
    BottomTabBar buttonTabBar;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        buttonTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(8)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页", R.drawable.agr, Fragment01.class)
                .addTabItem("发现", R.drawable.ago, Fragment02.class)
                .addTabItem("分类", R.drawable.agm, Fragment03.class)
                .addTabItem("购物车",R.drawable.agk, Fragment04.class)
                .addTabItem("我的", R.drawable.agk, Fragment05.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }
}
