package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 店铺货品操作请求
 * Created by baishixin on 16/5/24.
 */
public class GoodsActionRequest extends ParamModel implements Serializable {


    /**
     * action : 操作1(货品上下架)2(货品删除操作)
     * gid : 货品id
     * added : 当操作不为1时该字段为空(1为上架，2为下架)
     */

    private String action;
    private String gid;
    private String added;

    public GoodsActionRequest() {
    }

    public GoodsActionRequest(String action, String gid, String added) {
        this.action = action;
        this.gid = gid;
        this.added = added;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }
}
