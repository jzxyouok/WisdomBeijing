package com.wangchangzheng.wisdombeijing.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wangchangzheng.wisdombeijing.R;
import com.wangchangzheng.wisdombeijing.utils.SharePreferenceUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {
    private ViewPager mViewPager;
    private LinearLayout llContainer;
    private ImageView ivRedPoint;
    private Button btnStart;

    private ArrayList<ImageView> mImageViewList;//图片集合
    //引导界面图片的id数组
    private int[] mImageIds=new int[]{R.mipmap.home_bg_01,R.mipmap.home_bg_02,R.mipmap.home_bg_03,R.mipmap.home_bg_04};

    //小红点的距离
    private int mPointDis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();
        initData();

        mViewPager.setAdapter(new GuideAdapter());

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               int leftMargin= (int) ((mPointDis*positionOffset)+position*mPointDis);
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
                params.leftMargin=leftMargin;

                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position==mImageViewList.size()-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else {
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                mPointDis=llContainer.getChildAt(1).getLeft()-llContainer.getChildAt(0).getLeft();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreferenceUtils.setBoolean(getApplicationContext(),"is_first_enter",false);

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }



    private void init() {
        mViewPager= (ViewPager) findViewById(R.id.vp_guide);
        llContainer= (LinearLayout) findViewById(R.id.ll_container);
        ivRedPoint= (ImageView) findViewById(R.id.iv_red_point);
        btnStart= (Button) findViewById(R.id.btn_start);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mImageViewList=new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view=new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);

            //初始化小点
            ImageView point=new ImageView(this);
            point.setImageResource(R.drawable.shape_point_gray);

            //初始化布局参数，宽高包裹内容，父控件是谁，就是谁申明的布局参数
            LinearLayout.LayoutParams parms=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i>0){
                parms.leftMargin=10;
            }

            point.setLayoutParams(parms);//设置布局参数

            llContainer.addView(point);
        }
    }

    private class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        //初始化item布局

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view=mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



}
