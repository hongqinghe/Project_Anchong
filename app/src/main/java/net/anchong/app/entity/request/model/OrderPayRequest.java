package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 订单支付请求
 * Created by baishixin on 16/7/11.
 */
public class OrderPayRequest extends ParamModel implements Serializable {


    /**
     * order_id : 订单ID
     * body : 订单查看里面传过来的
     * total_price : 订单总价
     */

    private String order_id;
    private String body;
    private String total_price;

    public OrderPayRequest() {
    }

    public OrderPayRequest(String order_id, String body, String total_price) {
        this.order_id = order_id;
        this.body = body;
        this.total_price = total_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
