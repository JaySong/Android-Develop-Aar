package com.qjay.android_utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log日志工具类
 * Created by Q.Jay on 2015/6/9 0009.
 */
public class LogUtil {

//    private static boolean DEBUG = BuildConfig.DEBUG;
    private static boolean DEBUG = true;

    private LogUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    public static void d(String TAG, String method, String msg) {
        if (DEBUG) {
            Log.d(TAG, "[" + method + "]" + msg);
        }
    }

    public static void d(String TAG, String msg){
        if (DEBUG) {
            Log.d(TAG, "[" + getFileLineMethod() + "]" + msg);
        }
    }

    public static void d(String msg){
        if (DEBUG) {
            Log.d(_FILE_(), "[" + getLineMethod() + "]" + msg);
        }
    }
    public static void i(String TAG, String method, String msg) {
        if (DEBUG) {
            Log.i(TAG, "[" + method + "]" + msg);
        }
    }

    public static void i(String TAG, String msg){
        if (DEBUG) {
            Log.i(TAG, "[" + getFileLineMethod() + "]" + msg);
        }
    }

    public static void i(String msg){
        if (DEBUG) {
            Log.i(_FILE_(), "[" + getLineMethod() + "]" + msg);
        }
    }

    public static void e(String msg){
        if (DEBUG) {
            Log.e(_FILE_(), getLineMethod() + msg);
        }
    }

    public static void e(String TAG, String msg){
        if (DEBUG) {
            Log.e(TAG, getLineMethod() + msg);
        }
    }
    public static void e(String TAG, String msg,Throwable tr){
        if (DEBUG) {
            Log.e(TAG, getLineMethod() + msg, tr);
        }
    }
    private static String getFileLineMethod() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
        StringBuilder sb = new StringBuilder("[")
                .append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ")
                .append(traceElement.getMethodName()).append("]");
        return sb.toString();
    }

    private static String getLineMethod() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
        StringBuilder sb = new StringBuilder("[")
                .append(traceElement.getLineNumber()).append(" | ")
                .append(traceElement.getMethodName()).append("]");
        return sb.toString();
    }

    private static String _FILE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
        return traceElement.getFileName();
    }

    private static String _FUNC_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getMethodName();
    }

    private static int _LINE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getLineNumber();
    }

    private static String _TIME_() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(now);
    }
}
