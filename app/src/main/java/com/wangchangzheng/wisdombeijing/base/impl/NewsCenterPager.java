package com.wangchangzheng.wisdombeijing.base.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wangchangzheng.wisdombeijing.Activitys.MainActivity;
import com.wangchangzheng.wisdombeijing.base.BaseMenuDetailPager;
import com.wangchangzheng.wisdombeijing.base.BasePager;
import com.wangchangzheng.wisdombeijing.base.impl.menu.InteractMenuDetailPager;
import com.wangchangzheng.wisdombeijing.base.impl.menu.NewsMenuDetailPager;
import com.wangchangzheng.wisdombeijing.base.impl.menu.PhotoMenuDetailPager;
import com.wangchangzheng.wisdombeijing.base.impl.menu.TopicMenuDetailPager;
import com.wangchangzheng.wisdombeijing.domain.NewsMenu;
import com.wangchangzheng.wisdombeijing.fragment.LeftMenuFragment;
import com.wangchangzheng.wisdombeijing.global.GlobalConstans;
import com.wangchangzheng.wisdombeijing.utils.CacheUtils;

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
//        TextView view=new TextView(mActivity);
//        view.setText("新闻");
//        view.setTextColor(Color.RED);
//        view.setTextSize(22);
//        view.setGravity(Gravity.CENTER);
//
//        flContent.addView(view);

        tvTitle.setText("新闻");

        btnMenu.setVisibility(View.VISIBLE);

        String cache= CacheUtils.getCache(GlobalConstans.CATEGORY_URL,mActivity);

        if (!TextUtils.isEmpty(cache)){
            processData(cache);
        }
        getDataFromServer();
    }

    private void getDataFromServer() {
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, GlobalConstans.CATEGORY_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("wcz", "服务器返回的数据是: "+response);
//                processData(response);
//
//                CacheUtils.setCache(GlobalConstans.CATEGORY_URL,response,mActivity);
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("wcz", "数据加载失败，请检查代码的正确性");
//            }
//        });
//        Volley.newRequestQueue().add(stringRequest);

        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalConstans.CATEGORY_URL, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result=responseInfo.result;

                processData(result);

                CacheUtils.setCache(GlobalConstans.CATEGORY_URL,result,mActivity);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                Toast.makeText(mActivity,s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processData(String json) {
        Gson gson=new Gson();
        mNewsData=gson.fromJson(json,NewsMenu.class);

        MainActivity mainUI= (MainActivity) mActivity;
        LeftMenuFragment fragment=mainUI.getLeftMenuFragement();

        fragment.setMenuData(mNewsData.data);

        mMenuDetailPagers =new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new PhotoMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));

        setCurrentDetailPager(0);
    }


    public void setCurrentDetailPager(int position) {
        BaseMenuDetailPager pager=mMenuDetailPagers.get(position);
        View view=pager.mRootView;

        flContent.removeAllViews();

        flContent.addView(view);

        pager.initData();

        tvTitle.setText(mNewsData.data.get(position).title);
    }
}
