package com.wangchangzheng.wisdombeijing.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wangchangzheng.wisdombeijing.base.BaseMenuDetailPager;
import com.wangchangzheng.wisdombeijing.base.BasePager;
import com.wangchangzheng.wisdombeijing.domain.NewsMenu;

import java.util.ArrayList;

/**
 * Created by wangchangzheng on 16/5/19.
 */
public class NewsCenterPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;//菜单详情页集合
    private NewsMenu mNewsData;//分类信息网络数据
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    public void initData(){
        TextView view=new TextView(mActivity);
        view.setText("新闻");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        tvTitle.setText("新闻");

        btnMenu.setVisibility(View.VISIBLE);
    }

    public void setCurrentDetailPager(int position) {

    }
}
