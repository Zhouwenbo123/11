package com.bawei.yuekao1.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/4/2 15:04,周文博
 * Description:
 */
public abstract class BaseFragment  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(initLayout(),container,false);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    //监听
    protected abstract void initListener();

    //操作数据
    protected abstract void initData();
    //绑定视图
    protected abstract int initLayout();
    //获取控件
    protected abstract void initView();

    protected  <T extends  View > T bindView(int resId){
        return getView().findViewById(resId);
    }


}
