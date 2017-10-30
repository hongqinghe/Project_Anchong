package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 商品添加到购物车是请求的参数
 * Created by baishixin on 16/4/26.
 */
public class AddCarParamModel extends ParamModel implements Serializable {


    /**
     * goods_name : 商品名称
     * goods_id : 商品ID
     * goods_num : 加入购物车的商品数量
     * goods_price : 购买价格
     * goods_type : 商品类别，也就是商品的规格
     * img : 该商品的第一张图片
     * gid : 货品ID
     * sid : 商铺ID
     * sname : 商铺名称
     */

    private String goods_name;
    private String goods_id;
    private String goods_num;
    private String goods_price;
    private String goods_type;
    private String img;
    private String gid;
    private String sid;
    private String sname;

    public AddCarParamModel() {
    }

    public AddCarParamModel(String goods_name, String goods_id, String goods_num, String goods_price, String goods_type, String img, String gid, String sid, String sname) {
        this.goods_name = goods_name;
        this.goods_id = goods_id;
        this.goods_num = goods_num;
        this.goods_price = goods_price;
        this.goods_type = goods_type;
        this.img = img;
        this.gid = gid;
        this.sid = sid;
        this.sname = sname;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
