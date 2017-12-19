package com.bwei.taobao.model;



import com.bwei.taobao.bean.ShopBean;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/11 9:33
 */
public interface ModelCallBack {
    public void onSuccess(ShopBean bean);
    public void onFailure(Exception e);
}
