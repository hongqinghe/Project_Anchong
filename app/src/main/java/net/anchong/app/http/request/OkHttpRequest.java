package net.anchong.app.http.request;


import net.anchong.app.http.CommonCallback;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Starry Jerry on 2016/6/19.
 */
public abstract class OkHttpRequest {

    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;


    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.params = params;
        this.headers = headers;

        if (url == null) {
            throw new IllegalArgumentException("url can not be null.");
        }
    }


    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final CommonCallback callback) {
        return requestBody;
    }

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public RequestCall build() {
        return new RequestCall(this);
    }


    public Request generateRequest(CommonCallback callback) {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(builder, requestBody);
    }


    private void prepareBuilder() {
        builder.url(url);
        appendHeaders();
    }


    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    @Override
    public String toString() {
        return "OkHttpRequest{" +
                "url='" + url + '\'' +
                ", params=" + params +
                ", headers=" + headers +
                '}';
    }
}
