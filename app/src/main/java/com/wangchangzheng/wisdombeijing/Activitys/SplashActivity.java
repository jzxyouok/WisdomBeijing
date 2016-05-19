package com.wangchangzheng.wisdombeijing.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.wangchangzheng.wisdombeijing.R;
import com.wangchangzheng.wisdombeijing.utils.SharePreferenceUtils;

public class SplashActivity extends Activity {
    private ImageView head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    /**
     * 应用开屏后的初始界面动画展示，注意的是：旋转跟缩放两个动画是执行完的时间是2S，
     * 故设置透明度的变化时长为2S
     */
    private void initView() {

        head = (ImageView) findViewById(R.id.iv_head);
        //旋转动画
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);//保持动画结束状态

        //缩放动画
        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);//保持动画结束界面

        //渐变动画
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        //动画集合
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animation);
        set.addAnimation(animScale);
        set.addAnimation(animAlpha);
        //开启动画
        head.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //当动画巡展结束的时候，做出判断
                boolean isFirstEnter= SharePreferenceUtils.getBoolean(SplashActivity.this,"is_first_enter",true);
                Intent intent;
                if (isFirstEnter){
                    intent=new Intent(getApplicationContext(),GuideActivity.class);
                }else {
                    intent=new Intent(getApplicationContext(),MainActivity.class);
                }
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
