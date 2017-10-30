package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 商机单个查看
 * Created by baishixin on 16/7/15.
 */
public class BusinessIndexRequest extends ParamModel implements Serializable {


    /**
     * bid : 商机ID
     */

    private String bid;

    public BusinessIndexRequest() {
    }

    public BusinessIndexRequest(String bid) {
        this.bid = bid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
