package com.wangchangzheng.wisdombeijing.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wangchangzheng.wisdombeijing.Activitys.MainActivity;
import com.wangchangzheng.wisdombeijing.R;
import com.wangchangzheng.wisdombeijing.base.BasePager;
import com.wangchangzheng.wisdombeijing.base.impl.GovAffairsPager;
import com.wangchangzheng.wisdombeijing.base.impl.HomePager;
import com.wangchangzheng.wisdombeijing.base.impl.NewsCenterPager;
import com.wangchangzheng.wisdombeijing.base.impl.SettingPager;
import com.wangchangzheng.wisdombeijing.base.impl.SmartServicePager;
import com.wangchangzheng.wisdombeijing.view.NoScroolViewPager;

import java.util.ArrayList;

/**
 * Created by wangchangzheng on 16/5/19.
 */
public class ContentFragement extends BaseFragment {
    private NoScroolViewPager mViewPage;
    private RadioGroup rgGroup;
    private ArrayList<BasePager> mPagers;
    @Override
    public void initData() {
        mPagers=new ArrayList<BasePager>();
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsCenterPager(mActivity));
        mPagers.add(new SmartServicePager(mActivity));
        mPagers.add(new GovAffairsPager(mActivity));
        mPagers.add(new SettingPager(mActivity));

        mViewPage.setAdapter(new ContentAdapter());

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        mViewPage.setCurrentItem(0,false);//设置为不可滑动动画
                        break;
                    case R.id.rb_news:
                        mViewPage.setCurrentItem(1,false);
                        break;
                    case R.id.rb_smart:
                        mViewPage.setCurrentItem(2,false);
                        break;
                    case R.id.rb_gov:
                        mViewPage.setCurrentItem(3,false);
                        break;
                    case R.id.rb_setting:
                        mViewPage.setCurrentItem(4,false);
                        break;
                    default:
                        break;

                }
            }
        });

        mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BasePager pager=mPagers.get(position);
                pager.initData();

                if (position==1){
                    setSlidingMenuEnable(true);
                }else {
                    setSlidingMenuEnable(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPagers.get(0).initData();
        setSlidingMenuEnable(false);
    }

    /**
     * 开启或禁用侧边栏
     * @param enable
     */
    private void setSlidingMenuEnable(boolean enable) {
        MainActivity mainUI= (MainActivity) mActivity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

    }

    @Override
    public View initView() {
        View view=View.inflate(mActivity, R.layout.fragment_content,null);
        mViewPage= (NoScroolViewPager) view.findViewById(R.id.vp_content);
        rgGroup= (RadioGroup) view.findViewById(R.id.rg_group);

        return view;
    }

    private class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager=mPagers.get(position);
            View view=pager.mRootView;

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    //获取新闻中心页面
    public NewsCenterPager getNewsCenterPager(){
        NewsCenterPager pager= (NewsCenterPager) mPagers.get(1);
        return pager;
    }
}
