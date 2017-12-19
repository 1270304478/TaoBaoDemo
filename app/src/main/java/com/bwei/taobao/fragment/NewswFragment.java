package com.bwei.taobao.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwei.taobao.R;
import com.bwei.taobao.adpater.MyNewswAdapter;
import com.bwei.taobao.bean.DataDataBean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 子类Fragment
 */
public class NewswFragment extends Fragment{
   private PullToRefreshListView refreshListView;
  private  String parm;
    private List<DataDataBean.ResultsBean> list4 = new ArrayList<>();

    private int page_num=1;
    private MyNewswAdapter adapter01;

    @Nullable
    @Override
    //视图
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.news_fragment,container,false);
       refreshListView= (PullToRefreshListView) view.findViewById(R.id.refresh_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();

       parm=bundle.getString("name","Android");
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        getData();
        ILoadingLayout startLabels = refreshListView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在拉");
        startLabels.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = refreshListView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                getRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page_num++;
                getData();
            }
        });


    }

    private void getRefresh() {
        AsyncTask<Void,Void,String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String path = "http://gank.io/api/data/"+ URLEncoder.encode(parm,"utf-8")+"/10/1";
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();

                        String json = streamToString(inputStream,"utf-8");
                        Log.i("-----",json);
                        return json;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String json) {
                Gson gson = new Gson();
                Log.i("你好",json);
                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                if (dataDataBean != null && dataDataBean.getResults()!=null){

                    list4.addAll(dataDataBean.getResults());
                    setAdapter();
                    refreshListView.onRefreshComplete();
                }
            }
        };
        asyncTask.execute();
    }

    private void setAdapter() {
        if (adapter01==null){

            adapter01 = new MyNewswAdapter(getActivity(),list4);
            refreshListView.setAdapter(adapter01);
        }else
        {
            adapter01.notifyDataSetChanged();
        }

    }

    private void getData() {
        AsyncTask<Void,Void,String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String path = "http://gank.io/api/data/"+ URLEncoder.encode(parm,"utf-8")+"/10/"+page_num;
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();

                        String json = streamToString(inputStream,"utf-8");
                        Log.i("-----",json);
                        return json;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String json) {
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);
                if (dataDataBean != null && dataDataBean.getResults()!=null){
                    list4.addAll(dataDataBean.getResults());
                    setAdapter();
                    refreshListView.onRefreshComplete();
                }
            }
        };
        asyncTask.execute();
    }
   // 字节转字符串
    private String streamToString(InputStream inputStream,String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s=null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null){
                builder.append(s);
            }
            Log.i("++++",bufferedReader.toString());
            bufferedReader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
}
