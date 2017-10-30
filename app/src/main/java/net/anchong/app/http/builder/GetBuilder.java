package net.anchong.app.http.builder;


import net.anchong.app.http.request.GetRequest;
import net.anchong.app.http.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Starry Jerry on 2016/6/19.
 */
public class GetBuilder extends OkHttpRequestBuilder {
    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }

        return new GetRequest(url, params, headers).build();
    }

    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public GetBuilder url(String url) {
        this.url = url;
        return this;
    }


    @Override
    public GetBuilder addParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public GetBuilder addHeader(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
}
