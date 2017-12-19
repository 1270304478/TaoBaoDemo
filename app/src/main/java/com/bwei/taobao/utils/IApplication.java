package com.bwei.taobao.utils;

import android.app.Application;

import com.bwei.taobao.retrofit.IGetDataBase;
import com.facebook.drawee.backends.pipeline.Fresco;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Dash
 * @date 2017/9/12
 * @description:
 */
public class IApplication extends Application {

    public static IGetDataBase iGetDataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        ImageloaderUtil.initConfig(this);
        Fresco.initialize(this);


        Fresco.initialize(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iGetDataBase = retrofit.create(IGetDataBase.class);
    }
}