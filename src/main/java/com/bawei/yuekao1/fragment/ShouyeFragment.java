package com.bawei.yuekao1.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.bawei.yuekao1.R;
import com.bawei.yuekao1.adapter.MyFragmentAdapter;
import com.bawei.yuekao1.base.BaseFragment;
import com.bawei.yuekao1.bean.Goods;
import com.bawei.yuekao1.bean.JsonBean;
import com.bawei.yuekao1.util.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/4/2 15:43,周文博
 * Description:
 */
public class ShouyeFragment  extends BaseFragment {
    private TabLayout tabLayout;
    private ImageView pindao;
    private ViewPager viewPager1;
    private List<Fragment> fragments;
    private  List<Goods.ResultEntity> list;
    private String dateUrl="http://47.94.132.125:8080/zixunapi";
    private String TitleUrl="/categories";


    @Override
    protected int initLayout() {

        return R.layout.fragment_shouye;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        fragments.add(new Fragment6());
        fragments.add(new Fragment7());
        GetDate();
    }

    private void GetDate() {
        HttpUtils.httpAsynTask(dateUrl + TitleUrl, new HttpUtils.CallBack() {
            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                Goods fromJson = gson.fromJson(s, Goods.class);
                List<Goods.ResultEntity> result = fromJson.getResult();
                list.addAll(result);
                MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(),fragments,list);
                viewPager1.setAdapter(adapter);
                viewPager1.setOffscreenPageLimit(fragments.size());
                tabLayout.setupWithViewPager(viewPager1);
            }
        });
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tablayout);
        pindao = bindView(R.id.pindao);
        viewPager1 = bindView(R.id.viewpager1);
        fragments = new ArrayList<>();
        list = new ArrayList<>();
    }
}
