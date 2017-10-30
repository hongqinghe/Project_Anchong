package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 商机查看请求Model
 * Created by baishixin on 16/4/18.
 */
public class BusinessInfoParamModel extends ParamModel implements Serializable {

    /**
     * type : 类型(数字，数据表中的数据)
     * tag : 规定的检索标签
     * search : 手动搜索
     * page : 当前页数
     */

    private String type;
    private String tag;
    private String search;
    private String page;

    public BusinessInfoParamModel() {
    }

    public BusinessInfoParamModel(String type, String tag, String search, String page) {
        this.type = type;
        this.tag = tag;
        this.search = search;
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
