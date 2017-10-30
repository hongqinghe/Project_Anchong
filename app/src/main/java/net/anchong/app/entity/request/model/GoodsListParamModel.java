package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 根据三级分类ID获取商品列表的请求Param
 * Created by baishixin on 16/4/22.
 */
public class GoodsListParamModel extends ParamModel implements Serializable {
    /**
     * cid : 三级分类名
     * search : 搜索内容
     * page : 分页页码，从1开始
     */

    private String cid;
    private String search;
    private String page;

    public GoodsListParamModel() {
    }

    public GoodsListParamModel(String cid, String search, String page) {
        this.cid = cid;
        this.search = search;
        this.page = page;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
