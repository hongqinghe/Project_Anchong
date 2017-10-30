package net.anchong.app.http.request;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Starry Jerry on 2016/6/19.
 */
public class GetRequest extends OkHttpRequest {
    public GetRequest(String url, Map<String, String> params, Map<String, String> headers) {
        super(url, params, headers);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.get().build();
    }


}
