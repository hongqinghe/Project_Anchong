package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 热门招标项目请求
 * Created by baishixin on 16/6/23.
 */
public class HotProjectRequest extends ParamModel implements Serializable {


    /**
     * page : 当前页数
     */

    private String page;

    public HotProjectRequest() {
    }

    public HotProjectRequest(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
