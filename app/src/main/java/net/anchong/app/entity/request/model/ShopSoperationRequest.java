package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * Created by baishixin on 16/5/25.
 */
public class ShopSoperationRequest extends ParamModel implements Serializable{


    /**
     * order_id : 订单ID
     * order_num : 订单编号
     * action : 2(手动发货)3(无需物流)6(关闭交易)
     * logistcsnum : 订单编号(手动发货时才有)
     * company : 物流公司名称(手动发货时才有)
     */

    private String order_id;
    private String order_num;
    private String action;
    private String logistcsnum;
    private String company;

    public ShopSoperationRequest() {
    }

    public ShopSoperationRequest(String order_id, String order_num, String action, String logistcsnum, String company) {
        this.order_id = order_id;
        this.order_num = order_num;
        this.action = action;
        this.logistcsnum = logistcsnum;
        this.company = company;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLogistcsnum() {
        return logistcsnum;
    }

    public void setLogistcsnum(String logistcsnum) {
        this.logistcsnum = logistcsnum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
