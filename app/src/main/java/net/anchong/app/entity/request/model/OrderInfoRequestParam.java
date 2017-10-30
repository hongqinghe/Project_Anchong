package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 请求用户订单时的参数
 * Created by baishixin on 16/5/17.
 */
public class OrderInfoRequestParam extends ParamModel implements Serializable{


    /**
     * state : 0为全部1为待支付2为代发货3为待收货4为退款
     * page : 分页页码，从1开始
     */

    private String state;
    private String page;

    public OrderInfoRequestParam() {
    }

    public OrderInfoRequestParam(String state, String page) {
        this.state = state;
        this.page = page;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
