package com.bwei.taobao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwei.taobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends Activity {

    @BindView(R.id.web_view)
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        //加载
        webView.loadUrl(url);
        //设置
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
    }
}
