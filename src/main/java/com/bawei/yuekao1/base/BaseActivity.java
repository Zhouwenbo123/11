package com.bawei.yuekao1.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/4/2 14:58,周文博
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(initLayout());//布局
        initView();
        initData();
        initListener();

    }

    //设置监听
    protected abstract void initListener();

    //操作数据
    protected abstract void initData();

    //获取控件
    protected abstract void initView();

    //获取布局
    protected abstract int initLayout();
}
