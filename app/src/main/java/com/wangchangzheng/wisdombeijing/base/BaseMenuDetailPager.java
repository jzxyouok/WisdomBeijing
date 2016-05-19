package com.wangchangzheng.wisdombeijing.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by wangchangzheng on 16/5/20.
 */
public abstract class BaseMenuDetailPager {
    public Activity mActivity;
    public View mRootView;

    public BaseMenuDetailPager(Activity activity){
        mActivity=activity;
        mRootView=initView();
    }

    public abstract View initView();

    public abstract void initData();
}
