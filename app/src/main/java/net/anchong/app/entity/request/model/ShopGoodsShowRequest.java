package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 店铺商品管理请求
 * Created by baishixin on 16/5/24.
 */
public class ShopGoodsShowRequest extends ParamModel implements Serializable {

    /**
     * added : 1出售中2仓库中
     * sid : 商铺id
     */

    private String added;
    private String sid;

    public ShopGoodsShowRequest() {
    }

    public ShopGoodsShowRequest(String added, String sid) {
        this.added = added;
        this.sid = sid;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
