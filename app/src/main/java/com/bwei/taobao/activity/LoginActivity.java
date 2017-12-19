package com.bwei.taobao.activity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.taobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@SuppressLint("NewApi")
public class LoginActivity extends Activity {

    @BindView(R.id.but_logo)
    Button butLogo;
    @BindView(R.id.but_zhuce)
    Button butZhuce;

    @BindView(R.id.edit_phon)
    EditText editPhon;
    @BindView(R.id.edit_pws)
    EditText editPws;
    @BindView(R.id.guan)
    TextView guan;
    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.but_logo, R.id.but_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_logo:
                name = editPhon.getText().toString();
                password = editPws.getText().toString();
                if (name.length() <= 0 && password.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                } else if (name.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (password.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (name != null && password != null) {

                    // 获取存储的数据
                    SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                    String savename = sp.getString("username", "1270304478");
                    int savepassword = sp.getInt("password", 123456);

                    //判断用户名与密码是否和保存的数据一致，进行提醒或者登录
                    if (savename.equals(name) && savepassword == Integer.parseInt(password)) {

                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, OneActivity.class));
                        finish();
                        return;
                        //关闭当前界面

                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.but_zhuce:
                startActivity(new Intent(LoginActivity.this, ZhuCeActivity.class));

                break;
        }
    }
    @OnClick(R.id.guan)
    public void onViewClicked() {
        Toast.makeText(this, "关掉", Toast.LENGTH_SHORT).show();
        finish();
    }
}

