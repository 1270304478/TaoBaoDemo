package com.bwei.taobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bwei.taobao.R;
import com.bwei.taobao.activity.WebActivity;
import com.bwei.taobao.adpater.ImagePager;
import com.bwei.taobao.adpater.MyAdapter;
import com.bwei.taobao.adpater.MyGridAdapter;
import com.bwei.taobao.bean.Bean;
import com.bwei.taobao.utils.LoggingInterceptor;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/6 15:54
 */
public class Fragment01 extends Fragment {
    @BindView(R.id.image01)
    ImageView image01;
    private List<String> listT = new ArrayList<>();
    private List<Integer> imgT = new ArrayList<>();
    private ArrayList<Bean.TuijianBean.ListBeanX> list1 = new ArrayList<>();
    @BindView(R.id.gv_fragment)
    GridView gvFragment;
    @BindView(R.id.gridview)
    GridView gridview;
    private List<String> list;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    Unbinder unbinder;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem + 1);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };
    private List<ImageView> images;
    private List<Bean.TuijianBean.ListBeanX> tuilist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (tuilist != null) {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", tuilist.get(i).getDetailUrl());
                    startActivity(intent);
                }
            }
        });
        listT.add("京东超市");
        listT.add("全球购");
        listT.add("京东服饰");
        listT.add("京东生鲜");
        listT.add("京东到家");
        listT.add("充值缴费");
        listT.add("领京豆");
        listT.add("领券");
        listT.add("赚钱");
        listT.add("PLUS会员");
        imgT.add(R.mipmap.a1);
        imgT.add(R.mipmap.a2);
        imgT.add(R.mipmap.a3);
        imgT.add(R.mipmap.a4);
        imgT.add(R.mipmap.a5);
        imgT.add(R.mipmap.a6);
        imgT.add(R.mipmap.a7);
        imgT.add(R.mipmap.a8);
        imgT.add(R.mipmap.a9);
        imgT.add(R.mipmap.a10);
        //数据.....装的是path路径
        list = new ArrayList<>();
        list.add("https://m.360buyimg.com/n0/jfs/t6130/97/1370670410/180682/1109582a/593276b1Nd81fe723.jpg!q70.jpg");
        list.add("https://m.360buyimg.com/n0/jfs/t5698/110/2617517836/202970/c9388feb/593276b7Nbd94ef1f.jpg!q70.jpg");
        list.add("https://m.360buyimg.com/n0/jfs/t5815/178/2614671118/51656/7f52d137/593276c7N107b725a.jpg!q70.jpg");
        list.add("https://m.360buyimg.com/n0/jfs/t5878/60/2557817477/30873/4502b606/593276caN5a7d6357.jpg!q70.jpg");

        //初始化小圆点
        initDoc();
        MyGridAdapter myGridAdapter = new MyGridAdapter(listT, imgT, getActivity());
        gvFragment.setAdapter(myGridAdapter);
        //设置适配器
        ImagePager imagePager = new ImagePager(getActivity(), list, handler);
        //
        viewPager.setAdapter(imagePager);

        //设置ViewPager初始展示的位置
        viewPager.setCurrentItem(list.size() * 100000);
        //发送延时消息
        handler.sendEmptyMessageDelayed(0, 2000);

        //viewPager页面改变的监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //如果选中了这一页,,,当前小圆点红色,,,否则绿色
                for (int i = 0; i < images.size(); i++) {
                    if (i == position % images.size()) {
                        images.get(i).setImageResource(R.drawable.doc_select);
                    } else {
                        images.get(i).setImageResource(R.drawable.doc_select_no);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        Request request = new Request.Builder().url("https://www.zhaoapi.cn/ad/getAd").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Bean bean = new Gson().fromJson(s, Bean.class);
                tuilist = bean.getTuijian().getList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gridview.setAdapter(new MyAdapter(tuilist, getActivity()));
                    }
                });
            }
        });
    }
    /**
     * 初始化小圆点
     */
    private void initDoc() {
        //首先需要一个集合记录这些小圆点的图片,,,,当页面切换的时候,可以从集合中取出imageView进行显示图片的设置
        images = new ArrayList<>();
        linearLayout.removeAllViews();//清空/移除所有的view
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(getActivity());

            if (i == 0) {//显示第一页,,,红的
                imageView.setImageResource(R.drawable.doc_select);
            } else {//绿的
                imageView.setImageResource(R.drawable.doc_select_no);
            }
            //添加到集合
            images.add(imageView);
            //加入到线性布局中显示
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(5, 0, 5, 0);
            linearLayout.addView(imageView, params);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
       handler.removeMessages(0);
    }
    @OnClick(R.id.image01)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        getActivity().startActivity(intent);
    }
}
