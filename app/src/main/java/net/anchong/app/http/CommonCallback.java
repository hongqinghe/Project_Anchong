package net.anchong.app.http;


import okhttp3.Call;
import okhttp3.Request;

/**
 * 网络请求回调类
 * Created by Starry Jerry on 2016/6/19 05:01:14..
 *
 * @param <T> 解析的对象
 */
public abstract class CommonCallback<T> {

    /**
     * @param response 返回的对象
     * @param obj      可扩展参数
     */
    public abstract void onSuccess(T response, Object... obj);

    /**
     * @param call
     * @param e
     */
    public abstract void onError(Call call, Exception e);

    /**
     * @param request
     */
    public void onBefore(Request request) {
    }

    /**
     * @param
     */
    public void onAfter() {
    }

    /**
     * @param progress
     */
    public void inProgress(float progress) {

    }


}