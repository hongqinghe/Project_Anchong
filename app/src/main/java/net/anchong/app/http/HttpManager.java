package net.anchong.app.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.http.builder.GetBuilder;
import net.anchong.app.http.builder.PostFormBuilder;
import net.anchong.app.http.domain.ResponseResult;
import net.anchong.app.http.request.RequestCall;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * HTTP请求管理者
 * Created by Starry Jerry on 2016/6/19 05:01:14.
 * TODO 未完待续
 */
public class HttpManager {

    private static Handler mDelivery;

    private static OkHttpClient mOkHttpClient;

    private static HttpManager mInstance;

    private HttpManager() {
        mDelivery = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newBuilder().connectTimeout(30000, TimeUnit.MILLISECONDS);//超时时间30秒
    }

    public static HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager();
                }
            }
        }
        return mInstance;
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static Handler getDelivery() {
        return mDelivery;
    }

    /**
     * @param url      接口url
     * @param clazz    要解析的对象Class
     * @param callback 回调对象
     * @param <T>      对象的泛型
     */
    public <T> void post(String url, Map<String, String> params, Class<T> clazz, CommonCallback<T> callback) {
        RequestCall requestCall = new PostFormBuilder().url(url).addParams(params).build();
        requestCall.execute(clazz, callback);
    }

    /**
     * @param url 接口url
     * @return
     */
    public PostFormBuilder post(String url) {
        return new PostFormBuilder().url(url);
    }

    /**
     * @param url      接口url
     * @param clazz    要解析的对象Class
     * @param callback 回调对象
     * @param <T>      对象的泛型
     */
    public <T> void get(String url, Map<String, String> params, Class<T> clazz, CommonCallback<T> callback) {
        RequestCall requestCall = new GetBuilder().url(url).addParams(params).build();
        requestCall.execute(clazz, callback);
    }

    /**
     * @param url 接口url
     * @return
     */
    public GetBuilder get(String url) {
        return new GetBuilder().url(url);
    }

    /**
     * @param requestCall
     * @param clazz       要解析的对象Class
     * @param callback    回调对象
     * @param <T>         对象的泛型
     */
    public <T> void execute(RequestCall requestCall, final Class<T> clazz, final CommonCallback callback) {
        requestCall.getCall().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailCallback(call, e, callback);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                String json;
                if (response.code() == 200) {
                    try {
                        json = response.body().string();
                        Logger.i("HttpManager:" + json);//TODO 打印报文
//                        Log.i("HttpManager", json);
                        T t = new Gson().fromJson(json, clazz);//解析json对象
                        if (t instanceof ResponseResult) {//模型基类
                            sendSuccessCallback(t, callback);
                            return;
                        }
                    } catch (Exception e) {
                        sendFailCallback(call, e, callback);
                    }

                }
                try {
                    Logger.i(response.code() + "");
                    json = response.body().string();
                    sendFailCallback(call, new RuntimeException(json), callback);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void sendFailCallback(final Call call, final Exception e, final CommonCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e);
                callback.onAfter();
            }
        });
    }

    public <T> void sendSuccessCallback(final T object, final CommonCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(object);
                callback.onAfter();
            }
        });
    }
}
