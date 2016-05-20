package com.wangchangzheng.wisdombeijing.utils;

import android.content.Context;

/**
 * Created by wangchangzheng on 16/5/20.
 */
public class CacheUtils {
    public static void setCache(String url, String json, Context ctx){
        SharePreferenceUtils.setString(ctx,url,json);
    }


    public static String getCache(String url,Context ctx){
        return SharePreferenceUtils.getString(ctx,url,null);
    }
}
