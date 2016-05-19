package com.wangchangzheng.wisdombeijing.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wangchangzheng on 16/5/19.
 */
public class NoScroolViewPager extends ViewPager {
    public NoScroolViewPager(Context context) {
        super(context);
    }

    public NoScroolViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;//重写此方法 触摸啥都不做 从而实现对滑动事件的禁用
    }
}
