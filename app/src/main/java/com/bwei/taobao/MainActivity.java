package com.bwei.taobao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bwei.taobao.activity.OneActivity;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //实现页面跳转
                startActivity(new Intent(getApplicationContext(), OneActivity.class));
                setResult(2);
                finish();
                return false;
            }
            //表示延迟3秒发送任务
        }).sendEmptyMessageDelayed(0,3000);
    }

}
