package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 展示商品详情时的请求参数
 * Created by baishixin on 16/4/25.
 */
public class GoodsInfoParamModel extends ParamModel implements Serializable {


    /**
     * gid : 货品ID
     * goods_id : 商品ID(商品列表会传过来)
     */

    private String gid;
    private String goods_id;

    public GoodsInfoParamModel() {
    }

    public GoodsInfoParamModel(String gid, String goods_id) {
        this.gid = gid;
        this.goods_id = goods_id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}

