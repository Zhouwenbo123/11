package com.bawei.yuekao1.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bawei.yuekao1.bean.Goods;
import com.bawei.yuekao1.bean.JsonBean;

import java.util.List;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/4/2 20:39,周文博
 * Description:
 */
public class MyFragmentAdapter  extends FragmentPagerAdapter {

    private List<Fragment> list;
    private List<Goods.ResultEntity> item;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list, List<Goods.ResultEntity> item) {
        super(fm);
        this.list = list;
        this.item = item;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return item.get(0).getChildren().get(position).getTitle();
    }

    @Override
    public int getCount() {
        return item.get(0).getChildren().size();
    }
}
