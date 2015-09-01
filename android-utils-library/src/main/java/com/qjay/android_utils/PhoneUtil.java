package com.qjay.android_utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;


/**
 * Created by 仇杰 on 2015/6/26.
 * Description:
 */
public class PhoneUtil {

    /**
     * @return 手机制造商
     */
    public static String getManufacturer(){
        return Build.MANUFACTURER;
    }

    /**
     * @return 手机型号
     */
    public static String getModel(){
        return Build.MODEL;
    }

    /**
     * @return 设备ID
     */
    public static String getDeviceId(Context context){
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTm.getDeviceId();
    }

    /**
     * @return 设备系统版本
     */
    public static String getOsVersion(){
        return Build.VERSION.RELEASE;
    }



}
