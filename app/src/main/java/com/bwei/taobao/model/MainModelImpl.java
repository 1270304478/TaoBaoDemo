package com.bwei.taobao.model;
import com.bwei.taobao.bean.ShopBean;
import com.bwei.taobao.utils.IApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/11 9:30
 */
public class MainModelImpl {
    public void getData(final ModelCallBack callBack){
        Call<ShopBean> call= IApplication.iGetDataBase.get("99");
        call.enqueue(new Callback<ShopBean>() {
            @Override
            public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                ShopBean bean=response.body();
                callBack.onSuccess(bean);
            }
            @Override
            public void onFailure(Call<ShopBean> call, Throwable t) {
                callBack.onFailure(new Exception(""));
            }
        });
    }
}
