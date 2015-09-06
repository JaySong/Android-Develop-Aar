package com.qjay.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.widget.ImageView;

import com.qjay.android_utils.ImageUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

/**
 * okhttp请求网络管理类 使用单例设计模式
 * 【方法名带Asyn的是异步请求，其余是同步请求，也就是阻塞式方法】
 * Created by JaySeng on 2015/8/27.
 */
public class OkHttpManager {
    /**
     * 当前管理类实例
     */
    private static OkHttpManager mInstance;
    private OkHttpClient mOkHttpClient;
    private android.os.Handler mDelivery;

    private OkHttpManager() {
        mOkHttpClient = new OkHttpClient();
        mDelivery = new android.os.Handler(Looper.getMainLooper());
    }

    /**
     * 取消网络请求
     */
    public static void cancelRequest() {
        getInstance().cancel();
    }

    /**
     * 取消网络请求
     */
    private void cancel() {
        mOkHttpClient.cancel(-1);
    }

    /**
     * @return 返回当前类实例对象
     */
    public static OkHttpManager getInstance() {
        if (mInstance == null) {
            /*此处加锁，防止多线程同时访问*/
            synchronized (OkHttpManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 同步的Get请求
     *
     * @param url 请求url地址
     * @return Response
     */
    private Response _getAsyn(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
        return execute;
    }

    /**
     * 同步的Get请求
     *
     * @param url 请求url地址
     * @return 字符串
     */
    private String _getSynString(String url) throws IOException {
        Response execute = _getAsyn(url);
        return execute.body().string();
    }

    /**
     * 异步的get请求
     *
     * @param url      请求url地址
     * @param callback 回调
     */
    private void _getAsyn(String url, final StringCallback callback) {
        final Request request = new Request.Builder()
                .url(url).tag(-1)
                .build();
        deliveryResult(callback, request);
    }

    /**
     * 同步的Post请求
     *
     * @param url
     * @param params post的参数
     * @return
     */
    private Response _post(String url, Params... params) throws IOException {
        Request request = buildPostRequest(url, params);
        Response response = mOkHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步的Post请求
     *
     * @param url
     * @param params post的参数
     * @return 字符串
     */
    private String _postSynString(String url, Params... params) throws IOException {
        Response response = _post(url, params);
        return response.body().string();
    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param callback
     * @param params
     */
    private void _postAsyn(String url, final StringCallback callback, Params... params) throws IOException{
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param callback
     * @param params
     */
    private void _postAsyn(String url, final StringCallback callback, Map<String, String> params)  throws IOException{
        Params[] paramsArr = map2Params(params);
        Request request = buildPostRequest(url, paramsArr);
        deliveryResult(callback, request);
    }


    /**
     * 同步基于post的文件上传
     * @param params
     * @return
     */
    private Response _post(String url, File[] files, String[] fileKeys, Params... params) throws IOException {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        return mOkHttpClient.newCall(request).execute();
    }
    /**
     * 同步基于post的文件上传
     * @return
     */
    private Response _post(String url, File file, String fileKey) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, null);
        return mOkHttpClient.newCall(request).execute();
    }
    /**
     * 同步基于post的文件上传
     * @return
     */
    private Response _post(String url, File file, String fileKey, Params... params) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params);
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 异步基于post的文件上传
     * @param url
     * @param callback
     * @param files
     * @param fileKeys
     * @throws IOException
     */
    private void _postAsyn(String url, StringCallback callback, File[] files, String[] fileKeys, Params... params) throws IOException {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步基于post的文件上传，带参数上传
     *
     * @param url 请求url
     * @param callback
     * @param files
     * @param params
     */
    private void _postAsyn(String url, final StringCallback callback,Map<String,File> files, Map<String, String> params)  throws IOException{
        File[] file = null;
        String[] fileKey = null;

        if (files == null) {
            file = new File[0];
            fileKey = new String[0];
        }else{
            int size = files.size();
            file = new File[size];
            fileKey = new String[size];
            Set<Map.Entry<String, File>> entries = files.entrySet();
            int i = 0;
            for (Map.Entry<String, File> entry : entries) {
                fileKey[i] = entry.getKey();
                file[i] = entry.getValue();
                i++;
            }
        }
        Request request = buildMultipartFormRequest(url, file, fileKey, map2Params(params));
        deliveryResult(callback, request);
    }

    /**
     * 异步基于post的文件上传，单文件不带参数上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @throws IOException
     */
    private void _postAsyn(String url, StringCallback callback, File file, String fileKey) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, null);
        deliveryResult(callback, request);
    }

    /**
     * 异步基于post的文件上传，单文件且携带其他form参数上传
     *
     * @param url 请求url
     * @param callback 回调
     * @param file 文件
     * @param fileKey 文件key
     * @param params 参数列表
     * @throws IOException
     */
    private void _postAsyn(String url, StringCallback callback, File file, String fileKey, Params... params) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步下载文件
     *
     * @param url 请求url
     * @param destFileDir 本地存储的目录路径
     * @param callback 回调
     */
    private void _downloadAsyn(final String url, final String destFileDir, final StringCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Response response) {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(destFileDir, getFileName(url));
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    //如果下载文件成功，第一个参数为文件的绝对路径
                    sendSuccessStringCallback(file.getAbsolutePath(), callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } finally {
                    try {
                        if (is != null) is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null) fos.close();
                    } catch (IOException e) {
                    }
                }

            }
        });
    }

    private String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    /**
     * 加载图片
     *
     * @param view ImageView
     * @param url 图片网络地址
     * @throws IOException
     */
    private void _displayImage(final ImageView view, final String url, final int errorResId) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                setErrorResId(view, errorResId);
            }

            @Override
            public void onResponse(Response response) {
                InputStream is = null;
                try {
                    is = response.body().byteStream();
                    ImageUtil.ImageSize actualImageSize = ImageUtil.getImageSize(is);
                    ImageUtil.ImageSize imageViewSize = ImageUtil.getImageViewSize(view);
                    int inSampleSize = ImageUtil.calculateInSampleSize(actualImageSize, imageViewSize);
                    try {
                        is.reset();
                    } catch (IOException e) {
                        response = _getAsyn(url);
                        is = response.body().byteStream();
                    }

                    BitmapFactory.Options ops = new BitmapFactory.Options();
                    ops.inJustDecodeBounds = false;
                    ops.inSampleSize = inSampleSize;
                    final Bitmap bm = BitmapFactory.decodeStream(is, null, ops);
                    mDelivery.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bm);
                        }
                    });
                } catch (Exception e) {
                    setErrorResId(view, errorResId);

                } finally {
                    if (is != null) try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    private void setErrorResId(final ImageView view, final int errorResId) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                view.setImageResource(errorResId);
            }
        });
    }


    /*************对外公布的方法************/


    /**
     *
     * @param url 请求url
     * @return
     * @throws IOException
     */
    public static Response getAsyn(String url) throws IOException {
        return getInstance()._getAsyn(url);
    }

    public static String getAsynString(String url) throws IOException {
        return getInstance()._getSynString(url);
    }

    public static void getAsyn(String url, StringCallback callback) {
        getInstance()._getAsyn(url, callback);
    }

    /**
     * 同步post请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static Response post(String url, Params... params) throws IOException {
        return getInstance()._post(url, params);
    }

    public static String postSynString(String url, Params... params) throws IOException {
        return getInstance()._postSynString(url, params);
    }


    public static Response post(String url, File[] files, String[] fileKeys, Params... params) throws IOException {
        return getInstance()._post(url, files, fileKeys, params);
    }

    public static Response post(String url, File file, String fileKey) throws IOException {
        return getInstance()._post(url, file, fileKey);
    }

    public static Response post(String url, File file, String fileKey, Params... params) throws IOException {
        return getInstance()._post(url, file, fileKey, params);
    }
    public static void postAsyn(String url, final StringCallback callback, Params... params) throws IOException{
        getInstance()._postAsyn(url, callback, params);
    }

    public static void postAsyn(String url, final StringCallback callback, Map<String, String> params) throws IOException{
        getInstance()._postAsyn(url, callback, params);
    }
    public static void postAsyn(String url, StringCallback callback, File[] files, String[] fileKeys, Params... params) throws IOException {
        getInstance()._postAsyn(url, callback, files, fileKeys, params);
    }
    public static void postAsyn(String url, StringCallback callback, Map<String,File> files, Map<String, String> params) throws IOException {
        getInstance()._postAsyn(url, callback, files,params);
    }
    public static void postAsyn(String url, StringCallback callback, File file, String fileKey) throws IOException {
        getInstance()._postAsyn(url, callback, file, fileKey);
    }
    public static void postAsyn(String url, StringCallback callback, File file, String fileKey, Params... params) throws IOException {
        getInstance()._postAsyn(url, callback, file, fileKey, params);
    }
    public static void displayImage(final ImageView view, String url, int errorResId) throws IOException {
        getInstance()._displayImage(view, url, errorResId);
    }
    public static void displayImage(final ImageView view, String url) {
        getInstance()._displayImage(view, url, -1);
    }

    public static void downloadAsyn(String url, String destDir, StringCallback callback) {
        getInstance()._downloadAsyn(url, destDir, callback);
    }

    /****************************/
    private Request buildMultipartFormRequest(String url, File[] files,
                                              String[] fileKeys, Params[] params) {
        params = validateParam(params);

        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);

        for (Params param : params) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                    RequestBody.create(null, param.value));
        }
        if (files != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //TODO 根据文件名设置contentType
                builder.addPart(Headers.of("Content-Disposition",
                                "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""),
                        fileBody);
            }
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    private Params[] validateParam(Params[] params) {
        if (params == null)
            return new Params[0];
        else return params;
    }

    private Params[] map2Params(Map<String, String> params) {
        if (params == null) return new Params[0];
        int size = params.size();
        Params[] res = new Params[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Params(entry.getKey(), entry.getValue());
        }
        return res;
    }

    private void deliveryResult(final StringCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(final Response response) {
                try {
                    final String string = response.body().string();
                    sendSuccessStringCallback(string, callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                }

            }
        });
    }

    private void sendFailedStringCallback(final Request request, final IOException e, final StringCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onFailure(request, e);
            }
        });
    }

    private void sendSuccessStringCallback(final String string, final StringCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onResponse(string);
            }
        });
    }

    private Request buildPostRequest(String url, Params[] params) {
        if (params == null) {
            params = new Params[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Params param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url).tag(-1)
                .post(requestBody)
                .build();
    }


    public interface StringCallback {
        void onFailure(Request request, IOException e);

        void onResponse(String response);
    }

    public static class Params {
        public Params() {
        }

        public Params(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }

}
