package com.bwei.taobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.taobao.R;
import com.bwei.taobao.activity.LoginActivity;
import com.bwei.taobao.activity.MoviceWebActivity;
import com.bwei.taobao.activity.WebActivity;
import com.bwei.taobao.adpater.MyMovieAdapter;
import com.bwei.taobao.bean.MovieBean;
import com.bwei.taobao.utils.LoggingInterceptor;
import com.google.gson.Gson;

import java.io.IOException;
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

public class Fragment05 extends Fragment {

    @BindView(R.id.gridview02)
    GridView gridview02;
    Unbinder unbinder;
    @BindView(R.id.text_login)
    TextView textLogin;
    private List<MovieBean.RetBean.ListBean> list;
    Handler handler = new Handler();
    /**
     * fragment与activity产生关联是  回调这个方法
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment05, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridview02.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list!=null){
                    Intent intent = new Intent(getActivity(), MoviceWebActivity.class);
                    intent.putExtra("url", list.get(i).getShareURL());
                    startActivity(intent);
                }
            }
        });

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        Request request = new Request.Builder().url("http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                MovieBean bean = new Gson().fromJson(s, MovieBean.class);
                list = bean.getRet().getList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gridview02.setAdapter(new MyMovieAdapter(list, getActivity()));


                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.text_login)
    public void onViewClicked() {
        Toast.makeText(getActivity(), "跳转", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}

