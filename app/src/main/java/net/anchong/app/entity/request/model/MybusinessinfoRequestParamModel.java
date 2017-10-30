package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 请求个人商机查看时的参数Model
 * Created by baishixin on 16/4/11.
 */
public class MybusinessinfoRequestParamModel extends ParamModel implements Serializable {

    /**
     * type : 类型(可以为空)
     * search : 检索
     * page : 当前页数
     */

    private String type;
    private String search;
    private String page;

    public MybusinessinfoRequestParamModel() {
    }

    public MybusinessinfoRequestParamModel(String type, String search, String page) {
        this.type = type;
        this.search = search;
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
