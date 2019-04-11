package com.bawei.yuekao1.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.net.ConnectivityManagerCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.security.auth.callback.Callback;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/4/2 19:37,周文博
 * Description:
 */
public class HttpUtils {
    public  static  boolean isNetworkConnected(Context context){
        if (context!=null){
            //获取连接管理器
           ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
           //获取网络状态
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null){
                //判断网络是否可用
                return  networkInfo.isAvailable();
            }
        }
        return  false;
    }
    public  static  void  httpAsynTask(String strUrl, final CallBack callback){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return  httpGet(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                callback.getData(s);
            }
        }.execute(strUrl);
    }
    //接口
    public  interface  CallBack{
        void  getData(String s);
    }
    public static  String httpGet(String strUrl){
        try {
            URL url = new URL(strUrl);
            //获取HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置为get
            connection.setRequestMethod("GET");
            //设置连接主机超时时间
            connection.setConnectTimeout(5000);
            //设置从主机读取数据超时
            connection.setReadTimeout(5000);
            //得到数据
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //拼接数据
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null){
                builder.append(str);
            }
            //关闭连接
            connection.disconnect();
            //返回数据
            return  builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

}
