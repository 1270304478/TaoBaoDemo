package com.bwei.taobao.adpater;

import android.content.Context;
import android.net.Uri;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.taobao.R;
import com.bwei.taobao.bean.Bean;


import java.util.List;


/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/11/9 21:57
 */
public class MyAdapter extends BaseAdapter {
    List<Bean.TuijianBean.ListBeanX> tuilist;
    Context context;
    public MyAdapter(List<Bean.TuijianBean.ListBeanX> tuilist, Context context) {
        this.tuilist = tuilist;
        this.context = context;
    }


    @Override
    public int getCount() {
        return tuilist.size();
    }

    @Override
    public Object getItem(int i) {
        return tuilist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.fragment_list_item, null);
            holder = new ViewHolder();

            holder.imageview = view.findViewById(R.id.imageview);
            holder.textview = view.findViewById(R.id.textview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        String[] spli = tuilist.get(i).getImages().split("\\|");

        Uri uri= Uri.parse(spli[0]);
        holder.imageview.setImageURI(uri);

        holder.textview.setText(tuilist.get(i).getTitle());
        return view;
    }

    public class ViewHolder {
        ImageView imageview;
        TextView textview;
    }
}
