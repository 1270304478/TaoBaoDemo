package com.bwei.taobao.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bwei.taobao.R;
import java.util.ArrayList;
import java.util.List;
/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/6 15:54
 */
public class Fragment02 extends Fragment{
    private ViewPager vp01;
    private TabLayout tabLayout1;
    private List<String> list01;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment02,container,false);
        tabLayout1 = (TabLayout) view.findViewById(R.id.tab_layout);
        vp01=(ViewPager) view.findViewById(R.id.vp01);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list01 = new ArrayList<>();
        list01.add("福利");
        list01.add("Android");
        list01.add("Ios");
        list01.add("休息视频");
        list01.add("拓展视频");
        list01.add("前端");
        list01.add("all");
        vp01.setOffscreenPageLimit(list01.size());
        vp01.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list01.get(position);
            }
            @Override
            public Fragment getItem(int position) {
                NewswFragment newswFragment=new NewswFragment();
                Bundle bundle=new Bundle();
                bundle.putString("name", list01.get(position));
                newswFragment.setArguments(bundle);
                return newswFragment;
            }
            @Override
            public int getCount() {
                return list01.size();
            }
        });
        tabLayout1.setupWithViewPager(vp01);
    }
}
