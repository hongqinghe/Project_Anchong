package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 商机首页更多最新招标项目请求
 * Created by baishixin on 16/6/27.
 */
public class RecentProjectRequest extends ParamModel implements Serializable {


    /**
     * tag : 填那个ad_name传的某某市(商机首页那个更多的话就为空)
     * page : 当前页数
     */

    private String tag;
    private String page;

    public RecentProjectRequest() {
    }

    public RecentProjectRequest(String tag, String page) {
        this.tag = tag;
        this.page = page;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
