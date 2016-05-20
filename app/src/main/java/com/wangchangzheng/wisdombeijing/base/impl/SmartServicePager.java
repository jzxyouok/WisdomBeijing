package com.wangchangzheng.wisdombeijing.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wangchangzheng.wisdombeijing.base.BasePager;

/**
 * Created by wangchangzheng on 16/5/19.
 */
public class SmartServicePager extends BasePager {
    public SmartServicePager(Activity activity) {
        super(activity);
    }

    public void initData(){
        TextView view=new TextView(mActivity);
        view.setText("智慧服务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        tvTitle.setText("生活");

        btnMenu.setVisibility(View.GONE);
    }
}
