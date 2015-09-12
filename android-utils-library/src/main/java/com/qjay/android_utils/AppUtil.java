package com.qjay.android_utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.List;

/**
 * 应用工具类,用于获取应用信息
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

    /**
     * 判断是否在后台运行
     * @param context 上下文
     * @return 返回结果
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 像素转换成屏幕密度
     * @param context 上下文
     * @param pxValue 像素 float 类型
     * @return 返回密度 int类型
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 屏幕密度转换成像素;
     * @param context 上下文
     * @param dipValue 密度 float 类型
     * @return 返回像素  int类型
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
