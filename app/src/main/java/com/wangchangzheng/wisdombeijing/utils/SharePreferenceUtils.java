package com.wangchangzheng.wisdombeijing.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * sharepreferences的文件存储读取的操作
 * Created by wangchangzheng on 16/5/19.
 */
public class SharePreferenceUtils {
    private static final String spFileName="config";
    //获取配置参数
    public static boolean getBoolean(Context ctx,String key,boolean defValue){
        SharedPreferences sp=ctx.getSharedPreferences(spFileName,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    public static void setBoolean(Context ctx,String key,boolean value){
        SharedPreferences sp=ctx.getSharedPreferences(spFileName,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static void setString(Context ctx,String key,String value){
        SharedPreferences sp=ctx.getSharedPreferences(spFileName,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }


    public static String getString(Context ctx,String key,String defValue){
        SharedPreferences sp=ctx.getSharedPreferences(spFileName,Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    public static int getInt(Context ctx, String key, int value){
        SharedPreferences sp=ctx.getSharedPreferences(spFileName,Context.MODE_PRIVATE);
        return sp.getInt(key,value);
    }

    public static void setInt(Context ctx,String key,int defvalue){
        SharedPreferences sp=ctx.getSharedPreferences(spFileName,Context.MODE_PRIVATE);
        sp.edit().putInt(key,defvalue).commit();
    }






}
