package com.wangchangzheng.wisdombeijing.Activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.wangchangzheng.wisdombeijing.R;
import com.wangchangzheng.wisdombeijing.fragment.ContentFragement;
import com.wangchangzheng.wisdombeijing.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity{
    private static final String TAG_LEFT_MENU="TAG_LEFT_MENU";
    private static final String TAG_CONTENT="TAG_CONTENT";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu=getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触控
        slidingMenu.setBehindOffset(200);//屏幕预留200像素宽度
        initFragment();
    }

    private void initFragment() {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);
        ft.replace(R.id.fl_main,new ContentFragement(),TAG_CONTENT);

        ft.commit();


    }

    //获取侧边栏fragment对象
    public LeftMenuFragment getLeftMenuFragement(){
        FragmentManager fm=getSupportFragmentManager();
        LeftMenuFragment fragement= (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
        return fragement;
    }

    //获取Fragement对象
    public ContentFragement getContentFragment() {
        FragmentManager fm=getSupportFragmentManager();
        ContentFragement fragement= (ContentFragement) fm.findFragmentByTag(TAG_CONTENT);
        return fragement;
    }
}
