package com.bwei.taobao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwei.taobao.R;

import com.bwei.taobao.bean.MovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoviceWebActivity extends Activity {
    private List<MovieBean.RetBean.ListBean> list;
    @BindView(R.id.web_view02)
    WebView webView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movice_web);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        //加载
        webView02.loadUrl(url);
        //设置
        webView02.setWebViewClient(new WebViewClient());
        WebSettings settings=webView02.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setSupportZoom(true);

    }
}
