package com.qjay.android_utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.List;

/**
 * 应用工具类
 * Created by JaySeng on 2015/8/23.
 */
public final class AppUtil {
    private static final String TAG = "AppUtil";

    private AppUtil() {
        throw new AssertionError("此类是工具类，禁止创建对象，请绕道");
    }
    /**
     * 获取版本号
     * @param context 当前上下文
     * @return 当前应用的版本号，如果获取失败，则返回 -1
     */
    public static int getVersionCode(Context context){
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        int code = -1;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            code =  packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "没有找到此应用的版本号");
        }
        return code;
    }
    /**
     * 获取版本名称
     * @param context 当前上下文
     * @return 当前应用的版本名称，如果获取失败，则返回 null
     */
    public static String getVersionName(Context context){
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        String name = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            name =  packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "没有找到此应用的版本名称");
        }
        return name;
    }

    /**
     * 获取应用的图标
     * @param context 当前上下文
     * @return 当前应用的图标 Drawable 对象
     */
    public static Drawable getApplicationIcon(Context context){
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        Drawable applicationIcon = null;
        try {
            applicationIcon = packageManager.getApplicationIcon(context.getApplicationContext().getPackageName());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "没有找到此应用包名");
        }
        return applicationIcon;
    }
    public static List<ApplicationInfo> getInstalledApplications(Context context){
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<ApplicationInfo> installedApplications =
                packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        return installedApplications;
    }
}
