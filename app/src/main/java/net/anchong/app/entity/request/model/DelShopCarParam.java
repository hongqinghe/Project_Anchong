package net.anchong.app.entity.request.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/5/20.
 */
public class DelShopCarParam extends ParamModel implements Serializable {


    private List<Integer> cart_id;

    public DelShopCarParam() {
    }

    public DelShopCarParam(List<Integer> cart_id) {
        this.cart_id = cart_id;
    }

    public List<Integer> getCart_id() {
        return cart_id;
    }

    public void setCart_id(List<Integer> cart_id) {
        this.cart_id = cart_id;
    }
}
