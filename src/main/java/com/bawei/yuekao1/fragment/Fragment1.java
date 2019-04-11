package com.bawei.yuekao1.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.yuekao1.R;
import com.bawei.yuekao1.WebActivity;
import com.bawei.yuekao1.adapter.MyBaseAdapter;
import com.bawei.yuekao1.base.BaseFragment;
import com.bawei.yuekao1.bean.JsonBean;
import com.bawei.yuekao1.sqlite.SqliteHelper;
import com.bawei.yuekao1.util.HttpUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/4/2 20:17,周文博
 * Description:
 */
public class Fragment1  extends BaseFragment {
    private String listUrl="http://47.94.132.125:8080/zixunapi/fengjing";
    private String webUrl="http://47.94.132.125:8080/zixunapi/";
    private PullToRefreshListView listView;
    private List<String> imagelist;
    private Banner banner;
    private ArrayList<JsonBean.DataBean.NewsBean> newsbean;
    private SQLiteDatabase database;
    private MyBaseAdapter adapter;


    @Override
    protected void initListener() {

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HttpUtils.isNetworkConnected(getActivity())){
                    HttpUtils.httpAsynTask(listUrl, new HttpUtils.CallBack() {
                        @Override
                        public void getData(String s) {
                            //设置适配器
                            newsbean.clear();
                            Gson gson = new Gson();
                            JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                            newsbean.addAll(jsonBean.data.getNews());
                            adapter.notifyDataSetChanged();
                            listView.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    listView.onRefreshComplete();
                                }
                            }, 1000);

                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "没网", Toast.LENGTH_LONG).show();
                }
                //停止刷新
                listView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HttpUtils.isNetworkConnected(getActivity())){
                    HttpUtils.httpAsynTask(listUrl, new HttpUtils.CallBack() {
                        @Override
                        public void getData(String s) {
                            ArrayList<JsonBean.DataBean.NewsBean> listload = getArrayData(s);
                            newsbean.addAll(listload);
                            adapter.notifyDataSetChanged();
                            //设置适配器
                          //  adapter = new MyBaseAdapter(getActivity(),newsbean);
                           // listView.setAdapter(adapter);
                            listView.onRefreshComplete();
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "没网", Toast.LENGTH_LONG).show();
                }
                listView.onRefreshComplete();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url",webUrl+newsbean.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        if (HttpUtils.isNetworkConnected(getActivity())){
            //有网的时候加载数据
            HttpUtils.httpAsynTask(listUrl, new HttpUtils.CallBack() {
                @Override
                public void getData(String s) {
                    newsbean = getArrayData(s);
                    //设置适配器
                    adapter = new MyBaseAdapter(getActivity(),newsbean);
                    listView.setAdapter(adapter);
                    //添加到数据库
                    Cursor query = database.query("person", null, null, null, null, null, null);

                    if (!query.moveToFirst()){
                        ContentValues values =new ContentValues();
                        values.put("title",s);
                        database.insert("person",null,values);
                    }
                }
            });
        }else{
            //没网络查询数据库
            Cursor query = database.query("person", null, null, null, null, null, null);
            if (query.moveToFirst()){
                String title = query.getString(query.getColumnIndex("title"));
                newsbean = getArrayData(title);

                //设置适配器
                adapter = new MyBaseAdapter(getActivity(),newsbean);
                listView.setAdapter(adapter);
            }
        }
        banner.setImages(imagelist);
        banner.setDelayTime(1000);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.start();
    }

    @Override
    protected int initLayout() {

        return R.layout.fragment1;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.listview);
        banner = bindView(R.id.banner);
        imagelist = new ArrayList<>();
        imagelist.add("http://47.94.132.125:8080/zixunapi/image/banner1.jpeg");
        imagelist.add("http://47.94.132.125:8080/zixunapi/image/banner2.jpeg");
        imagelist.add("http://47.94.132.125:8080/zixunapi/image/banner3.jpeg");
        imagelist.add("http://47.94.132.125:8080/zixunapi/image/banner4.jpeg");
        imagelist.add("http://47.94.132.125:8080/zixunapi/image/banner5.jpeg");
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setPullToRefreshOverScrollEnabled(true);
        newsbean = new ArrayList<>();
        //创建数据库对象
        SqliteHelper helper = new SqliteHelper(getActivity());
        database = helper.getReadableDatabase();


    }
    public ArrayList<JsonBean.DataBean.NewsBean> getArrayData(String s) {
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
        List<JsonBean.DataBean.NewsBean> news = jsonBean.getData().getNews();
        ArrayList<JsonBean.DataBean.NewsBean> ones = new ArrayList<>();
        ones.addAll(news);
        return ones;
    }

}
