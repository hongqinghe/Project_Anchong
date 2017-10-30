package net.anchong.app.entity.request.model;

/**
 * 商品分类界面所有商品请求
 * Created by baishixin on 16/7/4.
 */
public class GoodsAllRequest extends ParamModel {


    /**
     * other_id : 一级分类ID
     * page : 分页页码，从1开始
     */

    private String other_id;
    private String page;

    public GoodsAllRequest() {
    }

    public GoodsAllRequest(String other_id, String page) {
        this.other_id = other_id;
        this.page = page;
    }

    public String getOther_id() {
        return other_id;
    }

    public void setOther_id(String other_id) {
        this.other_id = other_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
