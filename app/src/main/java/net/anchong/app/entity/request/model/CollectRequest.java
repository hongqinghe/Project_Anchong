package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 商品店铺收藏请求
 * Created by baishixin on 16/6/29.
 */
public class CollectRequest extends ParamModel implements Serializable {


    /**
     * coll_id : 要收藏的ID
     * coll_type :  1(货品收藏，第一个参数传货品id: gid)2(商铺收藏，第一个参数传商铺id: sid)(后期待扩展，收藏全部一个接口)
     */

    private String coll_id;
    private String coll_type;

    public CollectRequest() {
    }

    public CollectRequest(String coll_id, String coll_type) {
        this.coll_id = coll_id;
        this.coll_type = coll_type;
    }

    public String getColl_id() {
        return coll_id;
    }

    public void setColl_id(String coll_id) {
        this.coll_id = coll_id;
    }

    public String getColl_type() {
        return coll_type;
    }

    public void setColl_type(String coll_type) {
        this.coll_type = coll_type;
    }
}
