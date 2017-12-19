package com.bwei.taobao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwei.taobao.MainActivity;
import com.bwei.taobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuCeActivity extends Activity {
    @BindView(R.id.but_zhuce)
    Button butZhuce;
    @BindView(R.id.dj)
    Button dj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        ButterKnife.bind(this);
        dj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhuCeActivity.this, LoginActivity.class));
                 finish();
            }
        });
    }

    @OnClick(R.id.but_zhuce)
    public void onViewClicked() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }



}

