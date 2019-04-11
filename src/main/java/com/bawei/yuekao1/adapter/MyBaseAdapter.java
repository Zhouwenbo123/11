package com.bawei.yuekao1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.yuekao1.R;
import com.bawei.yuekao1.bean.JsonBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/4/3 10:30,周文博
 * Description:
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<JsonBean.DataBean.NewsBean> list;
    private String imjk = "http://47.94.132.125:8080/zixunapi/";

    public MyBaseAdapter(Context context, List<JsonBean.DataBean.NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int tyoe = getItemViewType(position);
        switch (tyoe){
            case 0:
                ViewHolder holder;
                if (convertView == null){
                    convertView = View.inflate(context, R.layout.list_item,null);
                    holder = new ViewHolder();
                    holder.imageView = convertView.findViewById(R.id.imageview);
                    holder.text1 = convertView.findViewById(R.id.text_name);
                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.text1.setText(list.get(position).getTitle());
                //设置图片
                Glide.with(context).load(imjk+list.get(position).getImageUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.imageView);

                break;
            case  1:
                ViewHolder1 holder1;
                if (convertView == null){
                    convertView = View.inflate(context, R.layout.list_item1,null);
                    holder1 = new ViewHolder1();
                    holder1.imageView1 = convertView.findViewById(R.id.imageview1);
                    holder1.text2 = convertView.findViewById(R.id.text_name1);
                    convertView.setTag(holder1);
                }else{
                    holder1 = (ViewHolder1) convertView.getTag();
                }
                holder1.text2.setText(list.get(position).getTitle());
                //设置图片
                Log.i("123", "getView: "+list.get(position).getImageUrl());
                Glide.with(context).load(imjk+list.get(position).getImageUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder1.imageView1);
                break;
            case  2:
                ViewHolder2 holder2;
                if (convertView == null){
                    convertView = View.inflate(context, R.layout.list_item2,null);
                    holder2 = new ViewHolder2();
                    holder2.text3 = convertView.findViewById(R.id.text_name2);
                    convertView.setTag(holder2);
                }else{
                    holder2 = (ViewHolder2) convertView.getTag();
                }
                holder2.text3.setText(list.get(position).getTitle());
                break;

        }

        return convertView;
    }
    class  ViewHolder{
        private ImageView imageView;
        private TextView text1;
    }
    class  ViewHolder1{
        private ImageView imageView1;
        private TextView text2;
    }
    class  ViewHolder2{
        private TextView text3;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
