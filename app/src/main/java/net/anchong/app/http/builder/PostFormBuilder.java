package net.anchong.app.http.builder;


import net.anchong.app.http.request.PostFormRequest;
import net.anchong.app.http.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Starry Jerry on 2016/6/19.
 */
public class PostFormBuilder extends OkHttpRequestBuilder {
    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build() {
        return new PostFormRequest(url, params, headers, files).build();
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    @Override
    public PostFormBuilder url(String url) {
        this.url = url;
        return this;
    }


    @Override
    public PostFormBuilder addParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostFormBuilder addHeader(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }


    @Override
    public PostFormBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }


}
