package net.anchong.app.entity.request.model;

/**
 * 请求商品二三级分类
 * Created by baishixin on 16/4/21.
 */
public class GoodsTwoThreeParam extends ParamModel {


    /**
     * cat_id : 一级分类名
     */

    private String cat_id;

    public GoodsTwoThreeParam(String cat_id) {
        this.cat_id = cat_id;
    }

    public GoodsTwoThreeParam() {

    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }
}
