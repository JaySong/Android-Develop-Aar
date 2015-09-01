package com.qjay.android_utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.Set;

/**
 *
 * Created by Q.Jay on 2015/8/8 0008.
 */
public final class SharedPreUtil {
    private SharedPreUtil(){}
    private final static String PREFERENCE_NAME = "config";

    private static SharedPreferences instance;

//    static{
//        instance = BaseApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
//    }

    public static SharedPreferences getInstance(Context context,String fileName){
        if(instance == null){
            instance = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return  instance;
    }

    public static boolean putString(@NonNull String key,@NonNull String value) {

        return instance.edit().putString(key, value).commit();
    }
    public static boolean putBoolean(@NonNull String key, boolean value) {
        return instance.edit().putBoolean(key, value).commit();
    }
    public static boolean putFloat(@NonNull String key,float value) {
        return instance.edit().putFloat(key, value).commit();
    }
    public static boolean putInt(@NonNull String key,int value){
        return instance.edit().putInt(key, value).commit();
    }
    public static boolean putLong(@NonNull String key,long value){
        return instance.edit().putLong(key, value).commit();
    }
    public static boolean putStringSet(@NonNull String key,@NonNull Set<String> value){
        return instance.edit().putStringSet(key, value).commit();
    }

    public static String getString(@NonNull String key){
        return instance.getString(key, null);
    }
    public static boolean getBoolean(@NonNull String key){
        return instance.getBoolean(key, false);
    }
    public static float getFloat(@NonNull String key){
        return instance.getFloat(key, -1);
    }
    public static int getInt(@NonNull String key) {
        return instance.getInt(key, -1);
    }
    public static long getLong(@NonNull String key) {
        return instance.getLong(key, -1);
    }
    public static Set<String> getStringSet(@NonNull String key) {
        return instance.getStringSet(key, null);
    }
    public static Map<String, ?> getAll(@NonNull String key) {
        return instance.getAll();
    }

    public static void removeKey(@NonNull String key) {
        instance.edit().remove(key).apply();
    }
}
