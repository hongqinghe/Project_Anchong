package net.anchong.app.http.builder;


import net.anchong.app.http.request.RequestCall;

import java.util.Map;

/**
 * Created by Starry Jerry on 2016/6/19.
 */
public abstract class OkHttpRequestBuilder {
    protected String url;
    protected Map<String, String> headers;
    protected Map<String, String> params;

    public abstract OkHttpRequestBuilder url(String url);

    public abstract OkHttpRequestBuilder addParams(Map<String, String> params);

    public abstract OkHttpRequestBuilder addParams(String key, String val);

    public abstract OkHttpRequestBuilder addHeader(Map<String, String> headers);

    public abstract OkHttpRequestBuilder addHeader(String key, String val);

    public abstract RequestCall build();


}
