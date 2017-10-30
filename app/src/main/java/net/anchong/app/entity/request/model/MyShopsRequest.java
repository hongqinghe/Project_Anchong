package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * Created by baishixin on 16/6/29.
 */
public class MyShopsRequest extends ParamModel implements Serializable {


    /**
     * sid : 商铺ID
     */

    private String sid;

    public MyShopsRequest() {
    }

    public MyShopsRequest(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
