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
public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }

    public void initData(){
        TextView view=new TextView(mActivity);
        view.setText("首页");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        tvTitle.setText("智慧北京");

        btnMenu.setVisibility(View.GONE);
    }

}
