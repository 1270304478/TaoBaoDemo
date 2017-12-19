package com.bwei.taobao.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/6 16:09
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
 private List<Fragment> list;
    private FragmentManager fm;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
        this.fm = fm;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
