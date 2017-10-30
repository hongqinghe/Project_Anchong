package net.anchong.app.entity.request.model;

/**
 * 商品属性请求
 * Created by baishixin on 16/4/25.
 */
public class GoodsParamRequestModel extends ParamModel {

    /**
     * goods_id : 商品ID（商品详情里面那个）
     */

    private String goods_id;

    public GoodsParamRequestModel(String goods_id) {
        this.goods_id = goods_id;
    }

    public GoodsParamRequestModel() {

    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}
