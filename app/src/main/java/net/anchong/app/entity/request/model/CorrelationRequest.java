package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 推荐商品请求
 * Created by baishixin on 16/7/21.
 */
public class CorrelationRequest extends ParamModel implements Serializable {


    /**
     * gid : 货品ID
     * page : 分页
     */

    private String gid;
    private String page;

    public CorrelationRequest() {
    }

    public CorrelationRequest(String gid, String page) {
        this.gid = gid;
        this.page = page;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
