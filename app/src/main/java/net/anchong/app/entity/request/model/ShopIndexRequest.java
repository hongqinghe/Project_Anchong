package net.anchong.app.entity.request.model;

/**
 * Created by baishixin on 16/5/31.
 */
public class ShopIndexRequest extends ParamModel {


    /**
     * sid : 商铺ID
     * action : 0(全部)1(销量)2(新品)3(价格从高到低)4(价格从低到高)
     * cid :
     * page : 1(分页)
     */

    private String sid;
    private String action;
    private String cid;
    private String page;

    public ShopIndexRequest() {
    }

    public ShopIndexRequest(String sid, String action, String cid, String page) {
        this.sid = sid;
        this.action = action;
        this.cid = cid;
        this.page = page;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
