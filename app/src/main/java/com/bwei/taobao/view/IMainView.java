package com.bwei.taobao.view;


import com.bwei.taobao.bean.ShopBean;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/11 9:31
 */
public interface IMainView {
    public void onSuccess(ShopBean bean);
    public void onFailure(Exception e);
}
