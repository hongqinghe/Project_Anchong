package net.anchong.app.entity.request.model;

/**
 * Created by baishixin on 16/5/12.
 */
public class ShopCarNumberParam extends ParamModel {


    /**
     * cart_id : 购物车那条数据的id(商品查看里的cart_id)
     * goods_num : 商品现在的数量
     */

    private String cart_id;
    private String goods_num;

    public ShopCarNumberParam() {
    }

    public ShopCarNumberParam(String cart_id, String goods_num) {
        this.cart_id = cart_id;
        this.goods_num = goods_num;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }
}
