package com.bwei.taobao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.taobao.R;
import com.bwei.taobao.adpater.ShopAdapter;
import com.bwei.taobao.bean.ShopBean;
import com.bwei.taobao.presenter.MainPresenter;
import com.bwei.taobao.view.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/6 15:54
 */


public class Fragment04 extends Fragment implements IMainView {


    @BindView(R.id.third_recyclerview)
    RecyclerView thirdRecyclerview;
    @BindView(R.id.third_allselect)
    CheckBox thirdAllselect;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    TextView thirdSubmit;
    @BindView(R.id.third_pay_linear)
    LinearLayout thirdPayLinear;
    Unbinder unbinder;
    private MainPresenter presenter;
    private ShopAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment04, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MainPresenter(this);
        presenter.get();
        adapter = new ShopAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        thirdRecyclerview.setLayoutManager(manager);
        thirdRecyclerview.setAdapter(adapter);


        adapter.setListener(new ShopAdapter.UpdateUiListener(){
            @Override
            public void setTotal(String total, String num,boolean allCheck) {

                thirdAllselect.setChecked(allCheck);
                thirdTotalnum.setText(num);
                thirdTotalprice.setText(total);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(ShopBean bean) {
        adapter.add(bean);
    }

    @Override
    public void onFailure(Exception e) {

    }
    @OnClick(R.id.third_allselect)
    public void onViewClicked() {
        adapter.selectAll(thirdAllselect.isChecked());
    }
}
