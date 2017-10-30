package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 配套商品请求
 * Created by baishixin on 16/7/21.
 */
public class SpportingGoodsRequest extends ParamModel implements Serializable {


    /**
     * goods_id : 商品品ID
     */

    private String goods_id;

    public SpportingGoodsRequest() {
    }

    public SpportingGoodsRequest(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}
