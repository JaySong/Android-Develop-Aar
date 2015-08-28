package com.qjay.http;

import java.io.File;
import java.io.IOException;

/**
 * OkHttp请求帮助类
 * Created by JaySeng on 2015/8/27.
 */
public class OkHttpHelper {

    public void doPost(String url,OkHttpManager.StringCallback callback,String[] filesKeys,File[] files,OkHttpManager.Params...params) throws IOException {

        OkHttpManager.postAsyn(url,callback,files,filesKeys,params);
    }
}