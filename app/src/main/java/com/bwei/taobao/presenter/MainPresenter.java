package com.bwei.taobao.presenter;


import com.bwei.taobao.bean.ShopBean;
import com.bwei.taobao.model.MainModelImpl;
import com.bwei.taobao.model.ModelCallBack;
import com.bwei.taobao.view.IMainView;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/11 9:50
 */
public class MainPresenter {
    private IMainView iView ;
    private MainModelImpl mainModel ;
    public MainPresenter(IMainView view){
        this.iView = view;
        this.mainModel = new MainModelImpl();

    }


    public void get(){

        mainModel.getData(new ModelCallBack() {



            @Override
            public void onSuccess(ShopBean bean) {
                if(iView != null){
                    iView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if(iView != null){
                    iView.onFailure(e);
                }
            }
        });
    }

}
