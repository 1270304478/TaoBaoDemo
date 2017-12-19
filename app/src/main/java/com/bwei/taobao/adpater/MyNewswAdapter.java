package com.bwei.taobao.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bwei.taobao.utils.ImageloaderUtil;
import com.bwei.taobao.R;
import com.bwei.taobao.bean.DataDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


public class MyNewswAdapter extends BaseAdapter{
    Context context;
    List<DataDataBean.ResultsBean> list4;


    public MyNewswAdapter(Context context, List<DataDataBean.ResultsBean> list4) {
    this.context=context;
        this.list4=list4;
    }

    @Override
    public int getCount() {
        return list4.size();
    }

    @Override
    public Object getItem(int i) {
        return list4.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.item_list,null);
            holder = new ViewHolder();
            holder.image = (ImageView)view.findViewById(R.id.image);
            holder.title = (TextView)view.findViewById(R.id.title);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        holder.title.setText(list4.get(i).getDesc());
        if (list4.get(i).getImages()!=null){
            ImageLoader.getInstance().displayImage(list4.get(i).getImages().get(0),holder.image, ImageloaderUtil.getDefaultOption());

        }

        return view;
    }
    class ViewHolder{
        ImageView image;
        TextView title;
    }
}
