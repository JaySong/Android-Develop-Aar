package com.qjay.android_utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * Created by JaySeng on 2014/3/29.
 */
public final class MD5Util {
    private MD5Util() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static String getMD5(String str,byte[] bytes){
        return md5Encrypt(str == null ? bytes : str.getBytes());
    }

    public static String getMD5(String content){
        return getMD5(content,null);
    }
    public static String getMD5(byte[] content){
        return getMD5(null,content);
    }

    /***
     * MD5加密
     * @param bytes 加密数据
     * @return 返回加密后的数据
     */
    private static String md5Encrypt(byte[] bytes){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md5 == null) {
            return "";
        }
        md5.update(bytes);
        byte[] m = md5.digest();//加密
        return new String(m);
    }
}
