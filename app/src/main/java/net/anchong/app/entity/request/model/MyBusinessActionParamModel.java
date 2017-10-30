package net.anchong.app.entity.request.model;

/**
 * 个人商机操作，包括更新时间和删除商机
 * action的值为1 更新时间（类似于置顶的操作）
 * action的值为2 删除当前商机
 * Created by baishixin on 16/4/14.
 */
public class MyBusinessActionParamModel extends ParamModel {


    /**
     * action : 1(更新)2(删除)
     * bid : 当前操作的商机ID
     */

    private String action;
    private String bid;

    public MyBusinessActionParamModel() {
    }

    public MyBusinessActionParamModel(String action, String bid) {
        this.action = action;
        this.bid = bid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
