package com.bawei.yuekao1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.bawei.yuekao1.base.BaseActivity;

public class WebActivity extends BaseActivity {
    private WebView webView;


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent it = getIntent();
        String url = it.getStringExtra("url");
        Log.i("ababa",url);

        webView.loadUrl(url);
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.webview);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_web;
    }
}
