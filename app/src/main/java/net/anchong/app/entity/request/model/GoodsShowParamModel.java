package net.anchong.app.entity.request.model;

/**
 * 根据标签选择商品时请求参数。
 * Created by baishixin on 16/5/9.
 */
public class GoodsShowParamModel extends ParamModel{


    /**
     * goods_id : 商品ID（商品详情里面那个）
     * value : 用户选择的规格以空格分隔(如: 红色32)
     */

    private String goods_id;
    private String value;

    public GoodsShowParamModel() {
    }

    public GoodsShowParamModel(String goods_id, String value) {
        this.goods_id = goods_id;
        this.value = value;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
