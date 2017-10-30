package net.anchong.app.http.request;


import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Starry Jerry on 2016/6/19.
 */
public class RequestCall {
    private OkHttpRequest okHttpRequest;
    private Request request;
    private Call call;


    public RequestCall(OkHttpRequest request) {
        this.okHttpRequest = request;
    }


    public Call generateCall(CommonCallback callback) {
        request = generateRequest(callback);
        call = HttpManager.getInstance().getOkHttpClient().newCall(request);
        return call;
    }

    private Request generateRequest(CommonCallback callback) {
        return okHttpRequest.generateRequest(callback);
    }

    /**
     * 执行请求
     *
     * @param clazz    要解析的对象Class
     * @param callback 回调对象
     * @param <T>      对象的泛型
     */
    public <T> void execute(Class<T> clazz, CommonCallback<T> callback) {
        generateCall(callback);
        if (callback != null) {
            callback.onBefore(request);
        }
        HttpManager.getInstance().execute(this, clazz, callback);
    }

    public Call getCall() {
        return call;
    }

    public Request getRequest() {
        return request;
    }

    public OkHttpRequest getOkHttpRequest() {
        return okHttpRequest;
    }

    public Response execute() throws IOException {
        generateCall(null);
        return call.execute();
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }


}
