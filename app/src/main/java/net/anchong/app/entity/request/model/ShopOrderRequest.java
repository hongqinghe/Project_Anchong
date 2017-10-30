package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 商家订单请求
 * Created by baishixin on 16/5/24.
 */
public class ShopOrderRequest extends ParamModel implements Serializable {


    /**
     * state : 0为全部1为待支付2为代发货3为退款
     * sid : 店铺id
     */

    private String state;
    private String sid;

    public ShopOrderRequest() {
    }

    public ShopOrderRequest(String state, String sid) {
        this.state = state;
        this.sid = sid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
